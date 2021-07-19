package io.tolgee.model.activities

import io.tolgee.model.enums.actions.ProjectMutableField
import javax.persistence.Entity

@Entity
class ProjectModification : StandardModificationModel<ProjectActivity, ProjectMutableField>()
