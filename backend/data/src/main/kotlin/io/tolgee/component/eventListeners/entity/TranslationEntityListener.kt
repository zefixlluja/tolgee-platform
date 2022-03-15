package io.tolgee.component.eventListeners.entity

import io.tolgee.model.translation.Translation
import io.tolgee.service.TranslationRevisionService
import org.springframework.beans.factory.ObjectFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.transaction.annotation.Transactional
import javax.persistence.PrePersist
import javax.persistence.PreRemove
import javax.persistence.PreUpdate

@Configurable
class TranslationEntityListener {
  @Autowired
  lateinit var provider: ObjectFactory<TranslationRevisionService>

  @PrePersist
  @Transactional
  fun prePersist(translation: Translation) {
    translationRevisionService.onTranslationCreated(translation)
  }

  @PreUpdate
  @Transactional
  fun preUpdate(translation: Translation) {
    translationRevisionService.onTranslationUpdated(translation)
  }

  @PreRemove
  @Transactional
  fun preRemove(translation: Translation) {
    translationRevisionService.onTranslationDeleted(translation)
  }

  val translationRevisionService: TranslationRevisionService
    get() = provider.`object`
}
