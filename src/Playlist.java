import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Playlist {

    private String name;
    private LinkedList<Song> playlist;

    public Playlist(String name) {
        this.name = name;
        this.playlist = new LinkedList<Song>();
    }

    public String getName() {
        return name;
    }

    public LinkedList<Song> getPlaylist() {
        return playlist;
    }

    public boolean addSong (Album album, String title) {
        Song song = album.getSong(title);
        for (int i = 0;i< playlist.size();i++)
            if (playlist.get(i).getTitle().equals(title)) {
                return false;
            }
        playlist.add(song);
        return true;
    }

    public void addAllAlbumsSongs(Album album) {
        for (int i = 0; i<album.getAlbum().size(); i++) {
            addSong(album, album.getAlbum().get(i).getTitle());
        }
    }

    public Album uploadAlbumFromFile (String fileName) {
        String[][] albumArray = null;
        Album album = null;
        try (Stream<String> fStream = Files.lines(Paths.get(fileName))){
            albumArray = fStream
                    .map((line) ->
                        line.split(";"))
                    .toArray(String[][]::new);
        } catch (IOException e) {
            System.out.println("Couldn't find file \"" + fileName + "\".");
        }
        if (albumArray != null) {
            album = new Album(albumArray[0][1], albumArray[0][0]);
            for (int i = 1; i < albumArray.length; i++) {
                album.addSong(albumArray[i][1], albumArray[0][0], albumArray[i][2]);
            }
        }
        return album;
    }



    public void playAll () {
        ListIterator<Song> listIterator = playlist.listIterator();
        while (listIterator.hasNext()) {
            System.out.println("Now playing: " + listIterator.next().getTitle());
        }
        System.out.println("No more songs.");
    }

    public void printInstructions () {
        System.out.println("0 - quit");
        System.out.println("1 - forward");
        System.out.println("2 - previous");
        System.out.println("3 - replay");
        System.out.println("4 - list all");
        System.out.println("5 - menu");
        System.out.println("6 - delete a song");
    }

    public void play () {

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean goingForward = true;
        ListIterator<Song> listIterator = playlist.listIterator();

        printInstructions();
        if (playlist.isEmpty()) {
            System.out.println("Nothing to play.");
        } else {
            System.out.println("Now playing: " + listIterator.next().getTitle());
        }

        while (!quit) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("Goodbye!");
                    quit = true;
                    break;
                case 1:
                    if (!goingForward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                        } goingForward = true;
                    }

                    if (listIterator.hasNext()) {
                        System.out.println("Now playing: " + listIterator.next().getTitle());
                    } else {
                        System.out.println("Playlist have ended.");
                    }
                    break;
                case 2:
                    if (goingForward){
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        } goingForward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println("Now playing: " + listIterator.previous().getTitle());
                    } else {
                        System.out.println("That's the beginning of your playlist.");
                    }
                    break;
                case 3:
                    if (goingForward) {
                        System.out.println("Now playing: " + listIterator.previous().getTitle());
                        goingForward = false;
                    } else {
                        System.out.println("Now playing: " + listIterator.next().getTitle());
                        goingForward = true;
                    }
                    break;
                case 4:
                    for (int i=0;i<playlist.size();i++) {
                        System.out.println((i+1) + ". " + playlist.get(i).getTitle());
                    }
                    System.out.println("_________________");
                    break;
                case 5:
                    printInstructions();
                    break;
                case 6:
                    if (playlist.size() > 0) {
                        listIterator.remove();
                        if (listIterator.hasNext()) {
                            System.out.println("Now playing: " + listIterator.next().getTitle());;
                        } else if (listIterator.hasPrevious()) {
                            System.out.println("Now playing: " + listIterator.previous().getTitle());
                        }
                    }

            }


        }

    }

}