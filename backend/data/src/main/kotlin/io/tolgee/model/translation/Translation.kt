package io.tolgee.model.translation

import io.tolgee.model.Language
import io.tolgee.model.key.Key
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.UniqueConstraint
import javax.validation.constraints.NotNull

@Entity
@Table(
  uniqueConstraints = [
    UniqueConstraint(
      columnNames = ["key_id", "language_id"],
      name = "translation_key_language"
    )
  ]
)
class Translation(
  override var text: String? = null
) : TranslationAuditedFields() {

  @ManyToOne(optional = false)
  @field:NotNull
  lateinit var key: Key

  @ManyToOne(optional = false)
  lateinit var language: Language

  @OneToMany(mappedBy = "translation")
  var comments: MutableList<TranslationComment> = mutableListOf()

  constructor(text: String? = null, key: Key, language: Language) : this(text) {
    this.key = key
    this.language = language
  }
}
