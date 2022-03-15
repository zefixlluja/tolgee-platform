package io.tolgee.model.translation

import io.tolgee.model.StandardModel
import io.tolgee.model.UserAccount
import io.tolgee.model.enums.TranslationCommentState
import org.hibernate.validator.constraints.Length
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.validation.constraints.NotBlank

@Entity
class TranslationComment(
  @field:Length(max = 10000)
  @field:NotBlank
  @Column(columnDefinition = "text")
  var text: String = "",

  var state: TranslationCommentState = TranslationCommentState.NEEDS_RESOLUTION,

  @ManyToOne
  var translation: Translation
) : StandardModel() {
  @ManyToOne(optional = false)
  lateinit var author: UserAccount
}
