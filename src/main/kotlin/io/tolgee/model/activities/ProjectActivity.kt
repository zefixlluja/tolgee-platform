package io.tolgee.model.activities

import javax.persistence.*

@Entity
class ProjectActivity(
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  @MapsId
  var activity: Activity = Activity()
): BaseEntityActivity() {
  @Id
  private val id: Long = 0

  @OneToOne(mappedBy = "projectActivity")
  var translationActivity: TranslationActivity? = null

  var projectName: String = ""
  var projectId: Long = 0
}
