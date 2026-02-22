package io.github.tomhula.jecnaapi.data.examTopics

import kotlinx.serialization.Serializable

@Serializable
data class ExamTopicCategoryReference(
    val name: String,
    val url: String
)

