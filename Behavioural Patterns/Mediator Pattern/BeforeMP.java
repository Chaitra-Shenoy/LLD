import java.util.ArrayList;
import java.util.List;
class User {
  private List<User> otherUsers;
  String username;
  private String documentName;

  public User(String documentName, String username) {
    otherUsers = new ArrayList<>();
    this.documentName = documentName;
    this.username = username;
  }

  public void addUser(User user) {
    otherUsers.add(user);
  }

  public void change(String change) {
    System.out.println(change + " made by "+username);
    for(User user : otherUsers) {
      System.out.println(user.username + " notified about the change "+change);
    }
  }
}
public class BeforeMP {
  public static void main(String args[]) {
    User mainUser = new User("Doc 1", "user1");
    mainUser.addUser(new User("Doc 1", "user2"));
    mainUser.addUser(new User("Doc 1", "user3"));

    mainUser.change("Add title");
  }
}