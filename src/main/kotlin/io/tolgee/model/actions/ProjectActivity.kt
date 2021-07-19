package io.tolgee.model.actions

import javax.persistence.*

@Entity
class ProjectActivity(
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  @MapsId
  var activity: Activity = Activity()
) {
  @Id
  private val id: Long = 0

  var projectName: String = ""
  var projectId: Long = 0
}
