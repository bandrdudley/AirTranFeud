import junit.framework.TestCase;

public class AnswersTest extends TestCase {
  private static int SURVEY_SIZE = 100;
  
  private static int PANEL_SIZE = 12;
  
  String[][] answerList = new String[SURVEY_SIZE][PANEL_SIZE];
  
  public void testSurveys() {
    Answers answers = new Answers();
    this.answerList = answers.openFile("surveys.txt");
    answers.displayFile();
  }
}
