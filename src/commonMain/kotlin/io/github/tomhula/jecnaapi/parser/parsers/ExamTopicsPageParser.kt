package io.github.tomhula.jecnaapi.parser.parsers

import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.nodes.Document
import io.github.tomhula.jecnaapi.data.examTopics.ExamTopicCategoryReference
import io.github.tomhula.jecnaapi.data.examTopics.ExamTopicsPage
import io.github.tomhula.jecnaapi.parser.ParseException

internal object ExamTopicsPageParser
{
    fun parse(html: String): ExamTopicsPage
    {
        try
        {
            val doc: Document = Ksoup.parse(html)
            val pageBuilder = ExamTopicsPage.builder()

            val listItemEles = doc.select("ul.list a.item")

            for (listItemEle in listItemEles)
            {
                val href = listItemEle.attr("href").trim()
                val labelText = listItemEle.selectFirst(".label")?.text()?.trim()

                if (!labelText.isNullOrEmpty() && href.isNotBlank())
                {
                    pageBuilder.addCategory(ExamTopicCategoryReference(name = labelText, url = href))
                }
            }
            return pageBuilder.build()
        } catch (e: Exception)
        {
            throw ParseException("Failed to parse exam topics page.", e)
        }
    }
}

