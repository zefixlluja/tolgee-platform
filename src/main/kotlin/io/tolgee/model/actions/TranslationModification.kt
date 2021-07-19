package io.tolgee.model.actions

import io.tolgee.model.enums.actions.TranslationMutableField
import javax.persistence.*

@Entity
class TranslationModification : StandardModificationModel<TranslationActivity, TranslationMutableField>()
