package io.tolgee.service.actions

import io.tolgee.model.actions.Activity
import io.tolgee.model.actions.OperationType
import io.tolgee.security.AuthenticationFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Service
@Transactional
class ActionsService(
  private val entityManager: EntityManager,
  private val authenticationFacade: AuthenticationFacade
) {
  fun create(type: OperationType): Activity {
    return Activity().apply {
      this.type = type
      this.userId = authenticationFacade.userAccount.id
      this.userName = authenticationFacade.userAccount.name
    }
  }
}
