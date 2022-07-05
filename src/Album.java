import java.util.ArrayList;

public class Album {

    private String name;
    private String artist;
    private ArrayList<Song> album;

    public Album(String name) {
        this.name = name;
        this.artist = "Unknown";
        this.album = new ArrayList<>();
    }

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.album = new ArrayList<>();
    }

    public ArrayList<Song> getAlbum() {
        return album;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public boolean addSong (String title, String artist, String duration) {
        for (int i =0;i<album.size();i++) {
            Song song = album.get(i);
            if (song.getTitle().equals(title)) {
                return false;
            }
        } album.add(new Song(title,artist, duration));
        return true;
    }

    public Song getSong (String title) {
        for (int i =0;i<album.size();i++) {
            Song song = album.get(i);
            if (song.getTitle().equals(title)) {
                return song;
            }
        }
        return null;
    }

    public void printAlbumSongs () {
        System.out.println("Album \"" + getName() + "\" by " + getArtist() + " tracklist:");
        for (int i =0;i<album.size();i++) {
            Song song = album.get(i);
            System.out.println((i+1) + ". " + song.getTitle() +
                    "\n Duration: " + song.getDuration());
        }
        System.out.println("=====================");
    }

}