package io.tolgee.model.activities

import javax.persistence.*

@Entity
@Table(indexes = [Index(columnList = "translationId", name = "Translation Id Index")])
class TranslationActivity : BaseEntityActivity() {
  @Id
  private val id: Long = 0

  @OneToOne
  @JoinColumn(name = "id")
  @MapsId
  lateinit var projectActivity: ProjectActivity

  @OneToMany(mappedBy = "activity")
  var modifications: MutableList<TranslationModification> = mutableListOf()

  var translationId: Long = 0
  var keyId: Long = 0
  var keyName: String = ""
  var languageId: Long = 0
  var languageName: String = ""
}
