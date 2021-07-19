package io.tolgee.model.actions

import javax.persistence.*

@Entity
class TranslationAction(){
  @Id
  private val id: Long = 0

  @OneToOne
  @JoinColumn(name = "id")
  @MapsId
  var action: Action = Action()

  var keyName: String = ""
  var languageId: Long = 0
  var languageName: String = ""
  var languageTag: String = ""
  var translationText: String? = null
}
