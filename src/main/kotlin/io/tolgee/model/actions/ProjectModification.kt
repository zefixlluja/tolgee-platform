package io.tolgee.model.actions

import io.tolgee.model.enums.actions.ProjectMutableField
import javax.persistence.Entity

@Entity
class ProjectModification : StandardModificationModel<ProjectAction, ProjectMutableField>()
