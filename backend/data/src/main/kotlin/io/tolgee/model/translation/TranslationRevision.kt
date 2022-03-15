package io.tolgee.model.translation

import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class TranslationRevision : TranslationAuditedFields() {
  @ManyToOne
  lateinit var translation: Translation
}
