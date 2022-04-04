package io.tolgee.activity.activities.translation.translationComment

import io.tolgee.activity.ActivityService
import org.springframework.stereotype.Component

@Component
class TranslationCommentEditActivity(activityService: ActivityService) : BaseTranslationCommentActivity(activityService) {
  override val type: String = "TRANSLATION_COMMENT_EDIT"
}
