import java.util.ArrayList;

public class Team {
  private String lastName;
  
  ArrayList<Player> family = new ArrayList<Player>();
  
  private int strikeCount = 0;
  
  private int score = 0;
  
  private boolean hasControl = false;
  
  public void addMember(Player player) {
    this.family.add(player);
  }
  
  public String getLastName() {
    return this.lastName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public int getStrikeCount() {
    return this.strikeCount;
  }
  
  public void setStrikeCount(int strikeCount) {
    this.strikeCount = strikeCount;
  }
  
  public int getScore() {
    return this.score;
  }
  
  public void setScore(int score) {
    this.score = score;
  }
  
  public boolean isHasControl() {
    return this.hasControl;
  }
  
  public void setHasControl(boolean hasControl) {
    this.hasControl = hasControl;
  }
  
  public void addStrike(int strike) {
    this.strikeCount += strike;
  }
  
  public void addPoints(int points) {
    this.score += points;
  }
}
