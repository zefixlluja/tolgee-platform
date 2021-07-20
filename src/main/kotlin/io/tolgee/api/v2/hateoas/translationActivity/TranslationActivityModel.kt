package io.tolgee.api.v2.hateoas.translationActivity

import io.tolgee.model.activities.OperationType
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation
import java.util.*

@Suppress("unused")
@Relation(collectionRelation = "activities", itemRelation = "activity")
open class TranslationActivityModel(
  val activityId: Long,
  val translationId: Long,
  val type: OperationType,
  val modifications: List<TranslationModificationModel>?,
  val time: Date
) : RepresentationModel<TranslationActivityModel>()
