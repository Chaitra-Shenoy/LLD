interface VideoQuality {
  public void load();
}

class HDQuality implements VideoQuality{
  @Override
  public void load() {
    System.out.println("Load video in HD Quality");
  }
}

class UltraHDQuality implements VideoQuality {
  @Override
  public void load() {
    System.out.println("Load video in Ultra HD Quality");
  }
}

class K4Quality implements VideoQuality {
  @Override
  public void load() {
    System.out.println("Load video in 4K Quality");
  }
}

abstract class Device {
  protected VideoQuality videoQuality;
  public Device(VideoQuality videoQuality) {
    this.videoQuality = videoQuality;
  }

  abstract void playVideo(String title);
}

class MobileDevice extends Device {
  public MobileDevice(VideoQuality videoQuality) {
    super(videoQuality);
  }

  public void playVideo(String title) {
    System.out.println("Mobile - Play title - "+title);
    videoQuality.load();
  }
}

class TVDevice extends Device {
  public TVDevice(VideoQuality videoQuality) {
    super(videoQuality);
  }

  public void playVideo(String title) {
    System.out.println("TV - Play title - "+title);
    videoQuality.load();
  }
}

public class AfterBridge {
    public static void main(String args[]) {
      Device device = new MobileDevice(new HDQuality());
      device.playVideo("Movie 1");

      Device device2 = new TVDevice(new UltraHDQuality());
      device2.playVideo("Movie 1");
    }
}