class Tree {
  private int x;
  private int y;
  private String type;
  private String name;
  private String color;

  public Tree(int x, int y, String type, String name, String color) {
    this.x = x;
    this.y = y;
    this.type = type;
    this.name = name;
    this.color = color;
  }

  public void drawTree() {
    System.out.println("Plant tree in ("+x+","+y+")");
  }
}

public class BeforeFlyweight {
    public static void main(String[] args) {
      for(int i = 0; i < 1000000; i++) {
        Tree tree = new Tree(i,i,"Oak", "Tree", "Green");
        tree.drawTree();
      }
    }
}