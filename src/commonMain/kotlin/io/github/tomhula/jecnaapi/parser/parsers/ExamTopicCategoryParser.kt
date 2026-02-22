package io.github.tomhula.jecnaapi.parser.parsers

import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.nodes.Document
import io.github.tomhula.jecnaapi.data.examTopics.ExamTopic
import io.github.tomhula.jecnaapi.data.examTopics.ExamTopicCategory
import io.github.tomhula.jecnaapi.parser.ParseException

internal object ExamTopicCategoryParser
{
    fun parse(html: String, name: String, url: String): ExamTopicCategory
    {
        try
        {
            val doc: Document = Ksoup.parse(html)
            val categoryBuilder = ExamTopicCategory.builder(name, url)

            val listItemEles = doc.select("ul li .item .label")

            for (listItemEle in listItemEles)
            {
                val topicText = listItemEle.text().trim()

                if (topicText.isNotEmpty())
                {
                    categoryBuilder.addTopic(ExamTopic(description = topicText))
                }
            }

            return categoryBuilder.build()
        } catch (e: Exception)
        {
            throw ParseException("Failed to parse exam topic category.", e)
        }
    }
}


