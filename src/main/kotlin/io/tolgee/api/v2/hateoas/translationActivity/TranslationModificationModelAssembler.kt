package io.tolgee.api.v2.hateoas.translationActivity

import io.tolgee.api.v2.controllers.translation.TranslationActivityController
import io.tolgee.model.activities.TranslationModification
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.stereotype.Component

@Component
class TranslationModificationModelAssembler(

) : RepresentationModelAssemblerSupport<TranslationModification, TranslationModificationModel>(
  TranslationActivityController::class.java, TranslationModificationModel::class.java
) {
  override fun toModel(entity: TranslationModification): TranslationModificationModel {
    return TranslationModificationModel(
      id = entity.id,
      field = entity.field,
      oldValue = entity.oldValue,
      newValue = entity.newValue
    )
  }
}
