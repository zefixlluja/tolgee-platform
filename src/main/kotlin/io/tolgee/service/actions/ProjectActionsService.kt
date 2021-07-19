package io.tolgee.service.actions

import io.tolgee.model.Project
import io.tolgee.model.actions.OperationType
import io.tolgee.model.actions.ProjectAction
import io.tolgee.model.actions.ProjectModification
import io.tolgee.model.enums.actions.ProjectMutableField
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Service
@Transactional
class ProjectActionsService(
  private val entityManager: EntityManager,
  private val actionsService: ActionsService,
) {
  fun onModification(type: OperationType, oldProject: Project, newProject: Project) {
    val action = actionsService.create(type)
    val projectAction = ProjectAction(action)
    projectAction.projectName = oldProject.name
    projectAction.projectId = oldProject.id
    val modifications = mutableListOf<ProjectModification>()

    ProjectMutableField.values().forEach { field ->
      val oldValue = field.getStringValue(oldProject)
      val newValue = field.getStringValue(newProject)
      if (oldValue != newValue) {
        val modification = ProjectModification().apply {
          this.action = projectAction
          this.field = field
          this.oldValue = oldValue.toString()
          this.newValue = newValue.toString()
        }
        modifications.add(modification)
      }
    }
    if (modifications.isNotEmpty()) {
      entityManager.persist(action)
      entityManager.persist(projectAction)
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
}
