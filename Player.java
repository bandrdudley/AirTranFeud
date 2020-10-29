public class Player {
  private static String firstName = "Larry";
  
  private static String lastName = "Pickle";
  
  public Player(String first, String last) {
    firstName = first;
    lastName = last;
  }
  
  public static String getFirstName() {
    return firstName;
  }
  
  public static void setFirstName(String firstName) {
    Player.firstName = firstName;
  }
  
  public static String getLastName() {
    return lastName;
  }
  
  public static void setLastName(String lastName) {
    Player.lastName = lastName;
  }
}
