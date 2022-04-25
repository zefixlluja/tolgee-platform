package io.tolgee.model.translation

import io.tolgee.constants.MtServiceType
import io.tolgee.model.Language
import io.tolgee.model.StandardAuditModel
import io.tolgee.model.enums.TranslationState
import io.tolgee.model.key.Key
import org.hibernate.annotations.ColumnDefault
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Enumerated
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
  @Column(columnDefinition = "text")
  var text: String? = null
) : StandardAuditModel() {
  @ManyToOne(optional = false)
  @field:NotNull
  lateinit var key: Key

  @ManyToOne
  lateinit var language: Language

  @Enumerated
  @ColumnDefault(value = "2")
  var state: TranslationState = TranslationState.TRANSLATED

  /**
   * Was translated automatically?
   */
  @ColumnDefault("false")
  var auto: Boolean = false

  /**
   * Which machine translation provider was used to translate this value?
   */
  @Enumerated
  var mtProvider: MtServiceType? = null

  @OneToMany(mappedBy = "translation")
  var comments: MutableList<TranslationComment> = mutableListOf()

  constructor(text: String? = null, key: Key, language: Language) : this(text) {
    this.key = key
    this.language = language
  }
}
