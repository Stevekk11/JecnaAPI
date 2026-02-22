package io.github.tomhula.jecnaapi.data.examTopics

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmStatic

@Serializable
data class ExamTopicsPage(val categories: Set<ExamTopicCategoryReference>)
{
    companion object
    {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder
    {
        private val categories = mutableSetOf<ExamTopicCategoryReference>()

        fun addCategory(category: ExamTopicCategoryReference): Builder
        {
            categories.add(category)
            return this
        }

        fun build() = ExamTopicsPage(categories)
    }
}

