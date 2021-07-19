package io.tolgee.model.enums.actions

import io.tolgee.model.translation.Translation
import kotlin.reflect.KProperty1

enum class TranslationMutableField(val property: KProperty1<Translation, *>) {
  TEXT(Translation::text),
  STATE(Translation::state)
}
