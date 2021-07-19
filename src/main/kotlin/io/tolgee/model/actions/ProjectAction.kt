package io.tolgee.model.actions

import javax.persistence.*

@Entity
class ProjectAction(
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  @MapsId
  var action: Action = Action()
) {
  @Id
  private val id: Long = 0

  var projectName: String = ""
  var projectId: Long = 0
}
