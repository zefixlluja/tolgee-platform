package io.tolgee.model.actions

import javax.persistence.*

@Entity
class TranslationActivity(){
  @Id
  private val id: Long = 0

  @OneToOne
  @JoinColumn(name = "id")
  @MapsId
  var activity: Activity = Activity()

  var keyName: String = ""
  var languageId: Long = 0
  var languageName: String = ""
  var languageTag: String = ""
  var translationText: String? = null
}
