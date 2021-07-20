package io.tolgee.repository.activity

import io.tolgee.model.activities.Activity
import io.tolgee.model.views.TranslationActivityView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ActivityRepository : JpaRepository<Activity, Long> {

  @Query("""
    select a.id as activityId, a.time as time, ta.translationId as translationId, ta, ta.type as type
    from Activity a 
    join a.projectActivity pa
    join pa.translationActivity ta
    where ta.keyId = :keyId and ta.languageId = :languageId
    """)
  fun getTranslationActivity(keyId: Long, languageId: Long, pageable: Pageable): Page<TranslationActivityView>
}
