package io.tolgee.service.actions

import io.tolgee.model.activities.OperationType
import io.tolgee.model.activities.TranslationActivity
import io.tolgee.model.activities.TranslationModification
import io.tolgee.model.enums.actions.TranslationMutableField
import io.tolgee.model.translation.Translation
import io.tolgee.model.views.TranslationActivityView
import io.tolgee.repository.activity.ActivityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Service
@Transactional
class TranslationActivityService(
  private val entityManager: EntityManager,
  private val projectActivityService: ProjectActivityService,
  private val activityRepository: ActivityRepository
) {

  fun getTranslationActivity(keyId: Long, languageId: Long): List<TranslationActivityView> {
    return activityRepository.getTranslationActivity(keyId, languageId)
  }

  fun onModification(oldTranslation: Translation, newTranslation: Translation) {
    oldTranslation.key?.project?.let { project ->
      val activity = TranslationActivity().apply { type = OperationType.MODIFICATION }
      val modifications = mutableListOf<TranslationModification>()
      TranslationMutableField.values().forEach { field ->
        val oldValue = field.property.get(oldTranslation).toString()
        val newValue = field.property.get(newTranslation).toString()
        if (oldValue != newValue) {
          val modification = TranslationModification().apply {
            this.activity = activity
            this.field = field
            this.oldValue = oldValue
            this.newValue = newValue
          }
          modifications.add(modification)
        }
      }
      if (modifications.isNotEmpty()) {
        val projectActivity = projectActivityService.onTransitiveOperation(project)
        activity.projectActivity = projectActivity
        activity.assign(oldTranslation)
        entityManager.persist(activity)
        modifications.forEach { entityManager.persist(it) }
      }
    }
  }

  fun onCreation(translation: Translation) {
    translation.key?.project?.let { project ->
      val activity = TranslationActivity().apply { type = OperationType.CREATION }
      activity.assign(translation)
      val projectActivity = projectActivityService.onTransitiveOperation(project)
      activity.projectActivity = projectActivity
      entityManager.persist(activity)
    }
  }

  fun onDeletion(translation: Translation) {
    translation.key?.project?.let { project ->
      val activity = TranslationActivity().apply { type = OperationType.DELETION }
      activity.assign(translation)
      val projectActivity = projectActivityService.onTransitiveOperation(project)
      activity.projectActivity = projectActivity
      entityManager.persist(activity)
    }
  }

  fun onTransitiveOperation(translation: Translation): TranslationActivity {
    val project = translation.key!!.project!!
    val activity = TranslationActivity().apply { type = OperationType.DELETION }
    activity.assign(translation)
    val projectActivity = projectActivityService.onTransitiveOperation(project)
    activity.projectActivity = projectActivity
    entityManager.persist(activity)
    return activity
  }

  fun TranslationActivity.assign(translation: Translation) {
    this.keyId = translation.key?.id ?: 0
    this.keyName = translation.key?.name ?: ""
    this.languageId = translation.language?.id ?: 0
    this.languageName = translation.language?.name ?: "Unknown"
  }
}
