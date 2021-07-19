package io.tolgee.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Service
@Transactional
class TranslationHistoryService(
  private val entityManager: EntityManager
) {



}
