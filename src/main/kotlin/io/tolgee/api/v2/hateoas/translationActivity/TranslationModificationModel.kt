package io.tolgee.api.v2.hateoas.translationActivity

import io.swagger.v3.oas.annotations.media.Schema
import io.tolgee.api.v2.hateoas.organization.LanguageModel
import io.tolgee.api.v2.hateoas.user_account.UserAccountModel
import io.tolgee.model.Permission
import io.tolgee.model.activities.OperationType
import io.tolgee.model.enums.OrganizationRoleType
import io.tolgee.model.enums.actions.TranslationMutableField
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation

@Suppress("unused")
@Relation(collectionRelation = "modifications", itemRelation = "modification")
open class TranslationModificationModel(
  val id: Long,
  val field: TranslationMutableField,
  val oldValue: String?,
  val newValue: String?
 ) : RepresentationModel<TranslationModificationModel>()
