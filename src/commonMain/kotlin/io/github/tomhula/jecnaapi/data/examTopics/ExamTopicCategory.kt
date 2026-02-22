package io.github.tomhula.jecnaapi.data.examTopics

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmStatic

@Serializable
data class ExamTopicCategory(
    val name: String,
    val url: String,
    val topics: List<ExamTopic>
)
{
    companion object
    {
        @JvmStatic
        fun builder(name: String, url: String) = Builder(name, url)
    }

    class Builder(private val name: String, private val url: String)
    {
        private val topics = mutableListOf<ExamTopic>()

        fun addTopic(topic: ExamTopic): Builder
        {
            topics.add(topic)
            return this
        }

        fun build() = ExamTopicCategory(name, url, topics)
    }
}

