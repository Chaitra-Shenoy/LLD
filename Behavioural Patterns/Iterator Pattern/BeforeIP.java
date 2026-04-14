import java.util.List;
import java.util.ArrayList;

class VideoIP {
  private String title;
  public VideoIP(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}

class VideoPlaylist {
  List<VideoIP> videoList;
  public VideoPlaylist() {
    videoList = new ArrayList<>();
  }

  public void addVideo(VideoIP video) {
    videoList.add(video);
  }

  public List<VideoIP> getVideos() {
    return videoList;
  }
}

// The user is aware of the internal implementation Data Structure - ArrayList
// Could break the code if internal data structure is changed
// No control over iteration
// You can't:
// restrict access
// provide custom traversal (reverse, filtered, etc.)
public class BeforeIP {
  public static void main(String args[]) {
    VideoPlaylist playlist = new VideoPlaylist();
    playlist.addVideo(new VideoIP("video1"));
    playlist.addVideo(new VideoIP("video2"));

    for(VideoIP v : playlist.getVideos()) {
      System.out.println(v.getTitle());
    }
  }
}