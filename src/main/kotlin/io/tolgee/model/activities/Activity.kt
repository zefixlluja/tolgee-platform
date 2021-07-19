package io.tolgee.model.activities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.util.ProxyUtils
import java.util.*
import javax.persistence.*

@Entity
@JsonIgnoreProperties(value = ["time"], allowGetters = true)
@EntityListeners(AuditingEntityListener::class)
class Activity {
  @Id
  @GenericGenerator(
    name = "sequenceGenerator",
    strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
    parameters = [
      Parameter(name = "sequence_name", value = "activity_sequence"),
      Parameter(name = "optimizer", value = "pooled"),
      Parameter(name = "initial_value", value = "1000000000"),
      Parameter(name = "increment_size", value = "100")
    ]
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "sequenceGenerator"
  )
  val id: Long = 0

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  @CreatedDate
  var time: Date? = null

  var userId: Long? = null
  var userName: String? = null

  @OneToOne(mappedBy = "activity")
  var projectActivity: ProjectActivity? = null

  override fun equals(other: Any?): Boolean {
    other ?: return false

    if (this === other) return true

    if (javaClass != ProxyUtils.getUserClass(other)) return false

    other as Activity

    return this.id == other.id
  }

  override fun hashCode(): Int {
    return 31
  }

  override fun toString() = "${this.javaClass.name}(id: $id)"
}
