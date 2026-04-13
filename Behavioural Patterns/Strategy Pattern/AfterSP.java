interface MatchingStrategy {
  public void matchRide();
}

class AirportQueueStrategy implements MatchingStrategy {
  @Override
  public void matchRide() {
    System.out.println("Airport Queue Match Ride");
  }
}

class NearestDriverStrategy implements MatchingStrategy {
  @Override
  public void matchRide() {
    System.out.println("Nearest Driver Match Ride");
  }
}

class SurgePriorityStrategy implements MatchingStrategy {
  @Override
  public void matchRide() {
    System.out.println("Surge Priority Match Ride");
  }
}

class MatchingService {
  private MatchingStrategy ms;
  public MatchingService(MatchingStrategy ms) {
    this.ms = ms;
  }

  public void setStrategy(MatchingStrategy ms) {
    this.ms = ms;
  }

  public void rideMatch() {
    ms.matchRide();
  }
}
public class AfterSP {
  public static void main(String args[]) {
    MatchingService matchService = new MatchingService(new AirportQueueStrategy());
    matchService.rideMatch();

    matchService.setStrategy(new SurgePriorityStrategy());
    matchService.rideMatch();
  }
}