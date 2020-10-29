import junit.framework.TestCase;

public class QuestionTest extends TestCase {
  private static String QUESTION_STRING = "Name famous Georges";
  
  public void testGetQuestion() {
    Question question = new Question();
    question.setQuestion(QUESTION_STRING);
    assertEquals(QUESTION_STRING, question.getQuestion());
  }
}
