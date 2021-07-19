package io.tolgee.model.views

import io.tolgee.model.activities.OperationType
import io.tolgee.model.activities.TranslationModification

interface TranslationActivityView {
  val activityId: Long
  val type: OperationType
  val modifications: List<TranslationModification>
}
