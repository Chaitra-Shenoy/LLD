import java.util.List;
import java.util.ArrayList;

class Video {
  private String title;
  public Video(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}

class VideoPlaylist {
  List<Video> videoList;
  public VideoPlaylist() {
    videoList = new ArrayList<>();
  }

  public void addVideo(Video video) {
    videoList.add(video);
  }

  public List<Video> getVideos() {
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
    playlist.addVideo(new Video("video1"));
    playlist.addVideo(new Video("video2"));

    for(Video v : playlist.getVideos()) {
      System.out.println(v.getTitle());
    }
  }
}