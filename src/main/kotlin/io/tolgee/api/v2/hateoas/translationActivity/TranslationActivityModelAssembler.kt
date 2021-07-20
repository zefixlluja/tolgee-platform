package io.tolgee.api.v2.hateoas.translationActivity

import io.tolgee.api.v2.controllers.translation.TranslationActivityController
import io.tolgee.model.views.TranslationActivityView
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.stereotype.Component

@Component
class TranslationActivityModelAssembler(
  val modificationModelAssembler: TranslationModificationModelAssembler
) : RepresentationModelAssemblerSupport<TranslationActivityView, TranslationActivityModel>(
  TranslationActivityController::class.java, TranslationActivityModel::class.java
) {
  override fun toModel(view: TranslationActivityView): TranslationActivityModel {
    return TranslationActivityModel(
      translationId = view.translationId,
      activityId = view.activityId,
      type = view.type,
      modifications = view.modifications?.map { modificationModelAssembler.toModel(it) },
      time = view.time
    )
  }
}
