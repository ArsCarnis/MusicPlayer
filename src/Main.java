public class Main {
    public static void main(String[] args) {


        Playlist playlist = new Playlist("My favourites");

        // playlist.uploadAlbumFromFile("Album_WillBeFun.txt").printAlbumSongs();

        playlist.addAllAlbumsSongs(playlist.uploadAlbumFromFile("Album_FearOfTheDark.txt"));
        playlist.addAllAlbumsSongs(playlist.uploadAlbumFromFile("Album_LAWoman.txt"));
        playlist.addAllAlbumsSongs(playlist.uploadAlbumFromFile("Album_WillBeFun.txt"));

        playlist.play();
    }
}