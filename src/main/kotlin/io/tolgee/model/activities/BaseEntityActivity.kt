package io.tolgee.model.activities

import javax.persistence.MappedSuperclass

@MappedSuperclass
class BaseEntityActivity {
  var type: OperationType = OperationType.MODIFICATION
}
