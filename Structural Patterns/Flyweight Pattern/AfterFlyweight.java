import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TreeType {
  private String type;
  private String name;
  private String color;

  public TreeType(String type, String name, String color) {
    this.type = type;
    this.name = name;
    this.color = color;
  }

  public void plantTree(int x, int y) {
    System.out.println("Tree - ("+type+", "+name+", "+color+")");
    System.out.println("Plant tree in ("+x+","+y+")");
  }
}

class TreeFP {
  private int x;
  private int y;
  private TreeType tp;

  public TreeFP(int x, int y, TreeType tp) {
    this.x = x;
    this.y = y;
    this.tp = tp;
  }

  public void drawTree() {
    tp.plantTree(x,y);
  }
}

class TreeTypeFactory {
  static final Map<String, TreeType> hashMap;
  static {
    hashMap = new HashMap<>();
  }
  public static TreeType getTree(String type, String name, String color) {
    String key = type + "-" + name + "-" + color;
    System.out.println(TreeTypeFactory.hashMap.size());
    if(!hashMap.containsKey(key)) {
      hashMap.put(key, new TreeType(type, name, color));
    }
    return hashMap.get(key);
  }
}
class Forest {
  List<TreeFP> forest;
  public Forest() {
    forest = new ArrayList<>();
  }

  public void plantForest() {
    for(int i = 0; i < 10; i++) {
      TreeFP tree = new TreeFP(i,i, TreeTypeFactory.getTree("Oak", "Tree", "Green"));
      tree.drawTree();
    }
  }
}
public class AfterFlyweight {
    public static void main(String[] args) {
      Forest f = new Forest();
      f.plantForest();
    }
}