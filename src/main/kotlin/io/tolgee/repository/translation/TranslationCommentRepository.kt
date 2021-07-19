package io.tolgee.repository.translation

import io.tolgee.model.translation.Translation
import io.tolgee.model.translation.TranslationComment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface TranslationCommentRepository : JpaRepository<TranslationComment, Long> {
  @Query("select tc from TranslationComment tc where tc.translation = :translation")
  fun getPagedByTranslation(translation: Translation, pageable: Pageable): Page<TranslationComment>

  @Transactional
  fun deleteByTranslationIdIn(ids: Iterable<Long>)

  fun deleteByTranslation(translation: Translation)
}
