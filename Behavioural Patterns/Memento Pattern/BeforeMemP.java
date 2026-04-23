import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class ResumeEditor {
  String name;
  String address;
  List<String> skills;

  public void display() {
    System.out.println("Name = "+name);
    System.out.println("Address = "+address);
    System.out.println("Skills = "+skills);
  }
}

class ResumeSnapshot {
  private String name;
  private String address;
  private List<String> skills;

  public ResumeSnapshot(ResumeEditor editor) {
    this.name = editor.name;
    this.address = editor.address;
    this.skills = new ArrayList<>(editor.skills);
  }

  public void restore(ResumeEditor editor) {
    editor.name = this.name;
    editor.address = this.address;
    editor.skills = this.skills;
  }
}
public class BeforeMemP {
  public static void main(String args[]) {

    ResumeEditor re = new ResumeEditor();
    re.name = "Alice";
    re.address = "Address";
    re.skills = new ArrayList<>(Arrays.asList("Java", "SQL"));
    re.display();

    ResumeSnapshot rs = new ResumeSnapshot(re);
    re.name = "Alice A";
    re.skills = new ArrayList<>(Arrays.asList("Java", "SQL", "Python"));
    re.display();

    rs.restore(re);
    re.display();
  }
}