import io.github.tomhula.jecnaapi.data.examTopics.ExamTopicCategory;
import io.github.tomhula.jecnaapi.data.examTopics.ExamTopicCategoryReference;
import io.github.tomhula.jecnaapi.data.examTopics.ExamTopicsPage;
import io.github.tomhula.jecnaapi.java.JecnaClientJavaWrapper;

public class ExamTopicsExample
{
    public static void main(String[] args)
    {
        /* Metody join() používáme aby jsme z asynchroního běhu "udělali" synchroní; pouze v příkladech */

        JecnaClientJavaWrapper client = new JecnaClientJavaWrapper();

        client.login("user", "password").join();

        // Get the main exam topics page with all categories
        ExamTopicsPage examTopicsPage = client.getExamTopicsPage().join();

        System.out.println("Available exam topic categories:");
        for (ExamTopicCategoryReference category : examTopicsPage.getCategories()) {
            System.out.println("- " + category.getName() + " (" + category.getUrl() + ")");
        }

        System.out.println();

        // Get topics for a specific category
        ExamTopicCategoryReference firstCategory = examTopicsPage.getCategories().stream().findFirst().orElse(null);
        if (firstCategory != null) {
            ExamTopicCategory categoryDetails = client.getExamTopicCategory(firstCategory).join();
            
            System.out.println("Topics for " + categoryDetails.getName() + ":");
            for (int i = 0; i < categoryDetails.getTopics().size(); i++) {
                System.out.println((i + 1) + ". " + categoryDetails.getTopics().get(i).getDescription());
            }
        }
    }
}

