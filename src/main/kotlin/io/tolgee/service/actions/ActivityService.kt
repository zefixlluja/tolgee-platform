package io.tolgee.service.actions

import io.tolgee.model.activities.Activity
import io.tolgee.security.AuthenticationFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ActivityService(
  private val authenticationFacade: AuthenticationFacade
) {
  fun create(): Activity {
    return Activity().apply {
      this.userId = authenticationFacade.userAccountOrNull?.id
      this.userName = authenticationFacade.userAccountOrNull?.name
    }
  }
}
