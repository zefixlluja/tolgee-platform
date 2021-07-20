package io.tolgee.model.views

import io.tolgee.model.activities.OperationType
import io.tolgee.model.activities.TranslationModification
import java.util.*

interface TranslationActivityView {
  val translationId: Long
  val activityId: Long
  val type: OperationType
  val modifications: List<TranslationModification>?
  val time: Date
}
