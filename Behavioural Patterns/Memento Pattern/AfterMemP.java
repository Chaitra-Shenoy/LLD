import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

class ResumeEditorMP {
  private String name;
  private String address;
  private List<String> skills;
  
  public ResumeEditorMP(String name, String address, List<String> skills) {
    this.name = name;
    this.address = address;
    this.skills = skills;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setSkills(List<String> skills) {
    this.skills = skills;
  }

  public void display() {
    System.out.println("Name = "+name);
    System.out.println("Address = "+address);
    System.out.println("Skills = "+skills);
  }

  public Memento snapshot() {
    return new Memento(name, address, new ArrayList<>(skills));
  }

  public void restore(Memento mem) {
    this.name = mem.getName();
    this.address = mem.getAddress();
    this.skills = mem.getSkills();
  }

  public static class Memento {
    private String name;
    private String address;
    private List<String> skills;

    private Memento(String name, String address, List<String> skills) {
      this.name = name;
      this.address = address;
      this.skills = skills;
    }

    public String getName(){
      return this.name;
    }

    public String getAddress() {
      return this.address;
    }

    public List<String> getSkills() {
      return new ArrayList<>(this.skills);
    }

  }
}

class ResumeHistory {
  Stack<ResumeEditorMP.Memento> st = new Stack<>();

  public void addHistory(ResumeEditorMP rem) {
    st.push(rem.snapshot());
  }

  public void undo(ResumeEditorMP editor) {
    if(st.size() > 1) {
      st.pop();
      editor.restore(st.peek());
    }
  }
}
public class AfterMemP {
  public static void main(String args[]) {
    ResumeEditorMP re = new ResumeEditorMP("Alice", "Address", new ArrayList<>(Arrays.asList("Java", "SQL")));
    ResumeHistory rh = new ResumeHistory();

    rh.addHistory(re);
    re.display();

    re.setName("Alice A");
    re.setSkills(new ArrayList<>(Arrays.asList("Java", "SQL", "Python")));
    re.setAddress("New Address");

    rh.addHistory(re);
    re.display();

    re.setName("Alice AB");

    rh.undo(re);
    re.display();

    rh.undo(re);
    re.display();
  }
}