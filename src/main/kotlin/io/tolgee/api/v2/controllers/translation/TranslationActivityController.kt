/*
 * Copyright (c) 2020. Tolgee
 */

package io.tolgee.api.v2.controllers.translation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.tags.Tags
import io.tolgee.api.v2.hateoas.translationActivity.TranslationActivityModel
import io.tolgee.api.v2.hateoas.translationActivity.TranslationActivityModelAssembler
import io.tolgee.controllers.IController
import io.tolgee.model.Permission
import io.tolgee.model.enums.ApiScope
import io.tolgee.model.views.TranslationActivityView
import io.tolgee.security.api_key_auth.AccessWithApiKey
import io.tolgee.security.project_auth.AccessWithProjectPermission
import io.tolgee.service.actions.TranslationActivityService
import org.springdoc.api.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.PagedModel
import org.springframework.web.bind.annotation.*

@Suppress("MVCPathVariableInspection", "SpringJavaInjectionPointsAutowiringInspection")
@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping(
  value = [
    "/v2/projects/{projectId:[0-9]+}/translations-activity",
  ]
)
@Tags(
  value = [
    Tag(name = "Translations", description = "Operations related to translations in project"),
  ]
)
class TranslationActivityController(
  private val translationActivityService: TranslationActivityService,
  private val pagedAssembler: PagedResourcesAssembler<TranslationActivityView>,
  private val translationActivityModelAssembler: TranslationActivityModelAssembler
) : IController {
  @GetMapping(value = ["/{keyId:[0-9]+}/{languageId:[0-9]+}"])
  @AccessWithApiKey([ApiScope.TRANSLATIONS_VIEW])
  @AccessWithProjectPermission(Permission.ProjectPermissionType.VIEW)
  @Operation(summary = "Returns translations in project")
  fun getTranslationActivity(
    @PathVariable keyId: Long,
    @PathVariable languageId: Long,
    @ParameterObject pageable: Pageable
  ): PagedModel<TranslationActivityModel> {
    val data = translationActivityService.getTranslationActivity(
      keyId = keyId,
      languageId = languageId,
      pageable = pageable
    )
    return pagedAssembler.toModel(data, translationActivityModelAssembler)
  }
}
