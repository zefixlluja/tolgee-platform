package io.tolgee.service.actions

import io.tolgee.model.Project
import io.tolgee.model.activities.OperationType
import io.tolgee.model.activities.ProjectActivity
import io.tolgee.model.activities.ProjectModification
import io.tolgee.model.enums.actions.ProjectMutableField
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Service
@Transactional
class ProjectActivityService(
  private val entityManager: EntityManager,
  private val activityService: ActivityService,
) {
  fun onModification(oldProject: Project, newProject: Project) {
    val activity = activityService.create()
    val projectActivity = ProjectActivity(activity).apply {
      type = OperationType.MODIFICATION
    }
    projectActivity.projectName = oldProject.name
    projectActivity.projectId = oldProject.id
    val modifications = mutableListOf<ProjectModification>()

    ProjectMutableField.values().forEach { field ->
      val oldValue = field.getStringValue(oldProject)
      val newValue = field.getStringValue(newProject)
      if (oldValue != newValue) {
        val modification = ProjectModification().apply {
          this.activity = projectActivity
          this.field = field
          this.oldValue = oldValue.toString()
          this.newValue = newValue.toString()
        }
        modifications.add(modification)
      }
    }
    if (modifications.isNotEmpty()) {
      entityManager.persist(activity)
      entityManager.persist(projectActivity)
      modifications.forEach { entityManager.persist(it) }
    }
  }

  fun ProjectMutableField.getStringValue(project: Project): Any? {
    return this.valueParser?.let {
      it(project)
    } ?: let {
      this.property.get(project).toString()
    }
  }

  fun onCreation(project: Project) {
    val activity = activityService.create()
    val projectActivity = ProjectActivity(activity).apply {
      type = OperationType.CREATION
    }
    projectActivity.projectName = project.name
    projectActivity.projectId = project.id
    entityManager.persist(activity)
    entityManager.persist(projectActivity)
  }

  fun onDeletion(project: Project) {
    val activity = activityService.create()
    val projectActivity = ProjectActivity(activity).apply {
      type = OperationType.DELETION
    }
    projectActivity.projectName = project.name
    projectActivity.projectId = project.id
    entityManager.persist(activity)
    entityManager.persist(projectActivity)
  }

  fun onTransitiveOperation(project: Project): ProjectActivity {
    val activity = activityService.create()
    val projectActivity = ProjectActivity(activity).apply {
      type = OperationType.TRANSITIVE_OPERATION
    }
    projectActivity.projectName = project.name
    projectActivity.projectId = project.id
    entityManager.persist(activity)
    entityManager.persist(projectActivity)
    return projectActivity
  }
}
