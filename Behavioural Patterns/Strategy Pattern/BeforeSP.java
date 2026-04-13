class RideMatching {
  public void matchRide(String type) {
    if(type == "Airport Queue") {
      System.out.println("Airport Queue Match Ride");
    } else if(type == "Nearest Driver") {
      System.out.println("Nearest Driver Match Ride");
    } else if(type == "Surge Priority") {
      System.out.println("Surge Priority Match Ride");
    }
  }
}
public class BeforeSP {
  public static void main(String args[]) {
    RideMatching rm = new RideMatching();
    rm.matchRide("Airport Queue");
  }
}