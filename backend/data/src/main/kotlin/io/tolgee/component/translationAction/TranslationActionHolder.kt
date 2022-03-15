package io.tolgee.component.translationAction

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST

@Component
@Scope(SCOPE_REQUEST)
class TranslationActionHolder {
  lateinit var actionType: TranslationActionOrigin
}
