import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Answers {
  ArrayList<String> answers;
  
  final int ARRAY_SIZE = 100;
  
  final int PANEL_SIZE = 12;
  
  String[][] surveys;
  
  private int surveySize;
  
  public Answers() {
    this.surveys = new String[100][12];
  }
  
  public ArrayList<String> buildAnswers() {
    this.answers = new ArrayList<String>();
    this.answers.add("George Bush");
    this.answers.add("George Jones");
    this.answers.add("George Foreman");
    this.answers.add("George Washington");
    this.answers.add("George of the Jungle");
    this.answers.add("Curious George");
    return this.answers;
  }
  
  public void getAnswers(ArrayList<String> answers) {
    for (int i = 0; i < answers.size(); i++)
      System.out.println(answers.get(i)); 
  }
  
  public String[][] openFile(String file) {
    try {
      BufferedReader fh = new BufferedReader(new FileReader(file));
      int k = 0;
      String s;
      while ((s = fh.readLine()) != null) {
        this.surveys[k] = s.split("\t");
        for (int i = 1; i < (this.surveys[k]).length; i++) {
          if (this.surveys[k][i].length() > 30)
            this.surveys[k][i] = "<html>" + this.surveys[k][i].substring(0, 29) + "</br>" + this.surveys[k][i].substring(29) + "</html>"; 
        } 
        k++;
      } 
      setSurveySize(k);
    } catch (IOException ioException) {
      System.out.println("Error Opening File.");
    } 
    return this.surveys;
  }
  
  public void displayFile() {
    for (int i = 0; i < this.surveySize; i++) {
      for (int j = 0; j < (this.surveys[i]).length; j++)
        System.out.print(String.valueOf(this.surveys[i][j]) + " "); 
      System.out.println("");
    } 
  }
  
  public int getSurveySize() {
    return this.surveySize;
  }
  
  public void setSurveySize(int surveySize) {
    this.surveySize = surveySize;
  }
  
  public int surveySays(String guess, int currentSurvey) {
    System.out.println("Answers : surveySays : guess = " + guess + " currentSurvey= " + currentSurvey);
    for (int i = 0; i < (this.surveys[currentSurvey]).length; i++) {
      if (this.surveys[currentSurvey][i].equals(guess))
        return i; 
    } 
    return 0;
  }
}
