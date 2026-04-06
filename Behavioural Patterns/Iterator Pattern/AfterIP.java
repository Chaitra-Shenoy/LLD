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

interface VideoIterator {
  public boolean hasNext();
  public VideoIP getNext();
}

class PlaylistIterator implements VideoIterator {
  private List<VideoIP> videos;
  private int position = 0;

  public PlaylistIterator(List<VideoIP> videos) {
    this.videos = videos;
  }

  @Override
  public boolean hasNext() {
    return position < videos.size();
  }

  @Override
  public VideoIP getNext() {
    return hasNext() ? videos.get(position++) : null;
  }
}

class Playlist {
  List<VideoIP> videoPlaylist;

  public Playlist() {
    videoPlaylist = new ArrayList<>();
  }

  public void addVideo(VideoIP video) {
    videoPlaylist.add(video);
  }

  public VideoIterator getVideos() {
    return new PlaylistIterator(videoPlaylist);
  }
  
}

public class AfterIP {
    public static void main(String args[]) {
      Playlist playlist = new Playlist();
      playlist.addVideo(new VideoIP("video1"));
      playlist.addVideo(new VideoIP("video2"));

      VideoIterator iterator = playlist.getVideos();

      while(iterator.hasNext()) {
        System.out.println(iterator.getNext().getTitle());
      }
    }
}