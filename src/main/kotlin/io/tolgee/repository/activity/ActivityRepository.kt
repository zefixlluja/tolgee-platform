package io.tolgee.repository.activity

import io.tolgee.model.activities.Activity
import io.tolgee.model.views.TranslationActivityView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ActivityRepository : JpaRepository<Activity, Long> {

  @Query("""
    select a.id as activityId, ta.type as type, tm as modifications
    from Activity a 
    join a.projectActivity pa
    join pa.translationActivity ta
    left join ta.modifications tm
    where ta.keyId = :keyId and ta.languageId = :languageId
    """)
  fun getTranslationActivity(keyId: Long, languageId: Long): List<TranslationActivityView>
}
