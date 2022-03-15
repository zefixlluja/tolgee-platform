package io.tolgee.service

import io.tolgee.model.translation.Translation
import io.tolgee.model.translation.TranslationRevision
import io.tolgee.repository.translation.TranslationRevisionRepository
import org.springframework.stereotype.Service

@Service
class TranslationRevisionService(
  private val translationRevisionRepository: TranslationRevisionRepository
) {
  fun onTranslationCreated(translation: Translation) {
    val revision = getRevision(translation)
    translationRevisionRepository.save(revision)
  }

  fun onTranslationUpdated(translation: Translation) {
    val revision = getRevision(translation)
    translationRevisionRepository.save(revision)
  }

  fun onTranslationDeleted(translation: Translation) {
  }

  fun getRevision(translation: Translation): TranslationRevision {
    val revision = TranslationRevision()
    revision.translation = translation
    revision.text = translation.text
    revision.state = translation.state
    revision.auto = translation.auto
    revision.mtProvider = translation.mtProvider
    revision.author = translation.author
    return revision
  }
}
