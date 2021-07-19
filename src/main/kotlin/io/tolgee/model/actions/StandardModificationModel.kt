package io.tolgee.model.actions

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import javax.persistence.*

@MappedSuperclass
class StandardModificationModel<FieldAction: Any, FieldEnum: Enum<*>> {
  @Id
  @GenericGenerator(
    name = "sequenceGenerator",
    strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
    parameters = [
      Parameter(name = "sequence_name", value = "action_sequence"),
      Parameter(name = "optimizer", value = "pooled"),
      Parameter(name = "initial_value", value = "1000000000"),
      Parameter(name = "increment_size", value = "100")
    ]
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "sequenceGenerator"
  )
  var id: Long = 0

  @ManyToOne(fetch = FetchType.LAZY)
  lateinit var action: FieldAction

  @Enumerated(EnumType.STRING)
  lateinit var field: FieldEnum

  @Lob
  var oldValue: String? = null

  @Lob
  var newValue: String? = null
}
