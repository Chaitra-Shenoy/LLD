import java.util.ArrayList;
import java.util.List;

interface DocumentMediator {
  void broadcastChange(String change, UserMP sender);
  void join(UserMP user);
}

class CollaborativeDocumentEditing implements DocumentMediator {
  List<UserMP> users;
  String documentName;

  public CollaborativeDocumentEditing(String documentName) {
    this.documentName = documentName;
    this.users = new ArrayList<>();
  }

  @Override
  public void broadcastChange(String change, UserMP sender) {
    for (UserMP user : users) {
      if (user != sender) {
        user.receive(change, sender);
      }
    }
  }

  @Override
  public void join(UserMP user) {
    users.add(user);
  }
}

class UserMP {
  String username;
  DocumentMediator dm;

  public UserMP(String username, DocumentMediator dm) {
    this.username = username;
    this.dm = dm;
  }

  public void change(String change) {
    System.out.println(username + " made change: " + change);
    dm.broadcastChange(change, this);
  }

  public void receive(String change, UserMP sender) {
    System.out.println(username + " notified about change '" + change + "' by " + sender.username);
  }
}

public class AfterMP {
  public static void main(String args[]) {
    DocumentMediator dm = new CollaborativeDocumentEditing("Doc 1");

    UserMP alice = new UserMP("alice", dm);
    UserMP bob = new UserMP("bob", dm);
    UserMP charlie = new UserMP("charlie", dm);

    dm.join(alice);
    dm.join(bob);
    dm.join(charlie);

    // ✅ Correct usage
    bob.change("Added title");
    alice.change("Added table of content");
  }
}