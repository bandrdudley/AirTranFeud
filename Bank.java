public class Bank {
  private int score = 0;
  
  public int getScore() {
    return this.score;
  }
  
  public void setScore(int score) {
    this.score = score;
  }
  
  public void addPoints(int points) {
    this.score += points;
  }
}