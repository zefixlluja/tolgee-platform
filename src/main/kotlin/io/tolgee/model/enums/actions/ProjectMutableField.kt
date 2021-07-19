package io.tolgee.model.enums.actions

import io.tolgee.model.Project
import kotlin.reflect.KProperty1

enum class ProjectMutableField(val property: KProperty1<Project, *>, val valueParser: ((Any?) -> String)? = null) {
  NAME(Project::name),
  DESCRIPTION(Project::description),
  SLUG(Project::slug),
  BASE_LANGUAGE(Project::baseLanguage, { (it as Project).baseLanguage?.name ?: "Unknown" })
}
