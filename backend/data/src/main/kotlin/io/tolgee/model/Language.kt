package io.tolgee.model

import io.tolgee.dtos.request.LanguageDto
import io.tolgee.events.OnLanguagePrePersist
import io.tolgee.model.translation.Translation
import io.tolgee.service.dataImport.ImportService
import org.springframework.beans.factory.ObjectFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.context.ApplicationEventPublisher
import org.springframework.transaction.annotation.Transactional
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.FetchType
import javax.persistence.Index
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.PrePersist
import javax.persistence.PreRemove
import javax.persistence.Table
import javax.persistence.UniqueConstraint
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

@Entity
@EntityListeners(Language.Companion.LanguageListeners::class)
@Table(
  uniqueConstraints = [
    UniqueConstraint(
      columnNames = ["project_id", "name"],
      name = "language_project_name"
    ),
    UniqueConstraint(
      columnNames = ["project_id", "tag"],
      name = "language_tag_name"
    )
  ],
  indexes = [
    Index(
      columnList = "tag",
      name = "index_tag"
    ),
    Index(
      columnList = "tag, project_id",
      name = "index_tag_project"
    )
  ]
)
class Language : StandardAuditModel() {
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "language")
  var translations: MutableSet<Translation>? = null

  @ManyToOne
  lateinit var project: Project

  @Column(nullable = false)
  @field:NotEmpty
  var tag: String = ""

  var name: String? = null

  var originalName: String? = null

  @field:Size(max = 20)
  @Column(length = 20)
  var flagEmoji: String? = null

  fun updateByDTO(dto: LanguageDto) {
    name = dto.name
    tag = dto.tag
    originalName = dto.originalName
    flagEmoji = dto.flagEmoji
  }

  override fun toString(): String {
    return "Language(tag=$tag, name=$name, originalName=$originalName)"
  }

  companion object {
    @JvmStatic
    fun fromRequestDTO(dto: LanguageDto): Language {
      val language = Language()
      language.name = dto.name
      language.tag = dto.tag
      language.originalName = dto.originalName
      language.flagEmoji = dto.flagEmoji
      return language
    }

    @Configurable
    class LanguageListeners {
      @Autowired
      lateinit var importServiceProvider: ObjectFactory<ImportService>

      @Autowired
      lateinit var eventPublisherProvider: ObjectFactory<ApplicationEventPublisher>

      @PrePersist
      fun prePersist(language: Language) {
        eventPublisherProvider.`object`.publishEvent(OnLanguagePrePersist(source = this, language))
      }

      @PreRemove
      @Transactional
      fun preRemove(language: Language) {
        importServiceProvider.`object`.onExistingLanguageRemoved(language)
      }
    }
  }
}
