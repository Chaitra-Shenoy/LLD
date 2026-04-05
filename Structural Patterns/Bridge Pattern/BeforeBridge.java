interface VideoPlayer {
  public void playVideo(String title);
}

class MobileHDPlayer implements VideoPlayer {
  @Override
  public void playVideo(String title) {
    System.out.println("Mobile Video Player playing "+title+" in HD Quality");
  }
}

class TVHDPlayer implements VideoPlayer {
  @Override
  public void playVideo(String title) {
    System.out.println("TV Video Player playing "+title+" in HD Quality");
  }
}

class WebHDPlayer implements VideoPlayer {
  @Override
  public void playVideo(String title) {
    System.out.println("Web Player playing "+title+" in HD Quality");
  }
}

class MobileUltraHDPlayer implements VideoPlayer {
  @Override
  public void playVideo(String title) {
    System.out.println("Mobile Player playing "+title+" in Ultra HD Quality");
  }
}

class TvUltraHDPlayer implements VideoPlayer {
  @Override
  public void playVideo(String title) {
    System.out.println("TV Player playing "+title+" in Ultra HD Quality");
  }
}

public class BeforeBridge {
    public static void main(String args[]) {
      MobileHDPlayer mhd = new MobileHDPlayer();
      mhd.playVideo("Movie 1");
    }
}