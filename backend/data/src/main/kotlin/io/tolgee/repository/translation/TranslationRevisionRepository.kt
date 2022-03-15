package io.tolgee.repository.translation

import io.tolgee.model.translation.TranslationRevision
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TranslationRevisionRepository : JpaRepository<TranslationRevision, Long>
