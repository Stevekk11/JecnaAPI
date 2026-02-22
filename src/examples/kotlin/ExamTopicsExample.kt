import kotlinx.coroutines.runBlocking
import io.github.tomhula.jecnaapi.JecnaClient

fun main(): Unit = runBlocking {

    val client = JecnaClient()

    client.login("user", "password")

    // Get the main exam topics page with all categories
    val examTopicsPage = client.getExamTopicsPage()

    println("Available exam topic categories:")
    for (category in examTopicsPage.categories)
    {
        println("- ${category.name} (${category.url})")
    }

    println()

    // Get topics for a specific category
    val firstCategory = examTopicsPage.categories.firstOrNull()
    if (firstCategory != null)
    {
        val categoryDetails = client.getExamTopicCategory(firstCategory)

        println("Topics for ${categoryDetails.name}:")
        categoryDetails.topics.forEachIndexed { index, topic ->
            println("${index + 1}. ${topic.description}")
        }
    }
}

