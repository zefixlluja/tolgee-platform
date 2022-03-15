package io.tolgee.model

import javax.persistence.Entity
import javax.persistence.OneToOne

@Entity
class AutoTranslationConfig : StandardModel() {
  @OneToOne
  lateinit var project: Project

  var usingTm: Boolean = false

  var usingPrimaryMtService: Boolean = false
}
