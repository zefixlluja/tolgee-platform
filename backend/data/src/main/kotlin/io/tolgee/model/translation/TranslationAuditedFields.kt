package io.tolgee.model.translation

import io.tolgee.constants.MtServiceType
import io.tolgee.model.StandardModel
import io.tolgee.model.UserAccount
import io.tolgee.model.enums.TranslationState
import org.hibernate.annotations.ColumnDefault
import javax.persistence.Column
import javax.persistence.Enumerated
import javax.persistence.ManyToOne
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class TranslationAuditedFields : StandardModel() {
  @Column(columnDefinition = "text")
  var text: String? = null

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

  @ManyToOne
  var author: UserAccount? = null
}
