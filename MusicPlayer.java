import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;

// This is the Song class, we use it to store song information.
class Song {
    // This variable stores the title of the song.
    private String title;

    // This variable stores the artist of the song.
    private String artist;

    // This is the constructor for creating a new song.
    // It takes the title and artist as parameters and sets the corresponding variables.
    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    // This method returns the title of the song.
    public String getTitle() {
        return title;
    }

    // This method returns the artist of the song.
    public String getArtist() {
        return artist;
    }

    // This method provides a nice formatted string for the song.
    // It returns the title of the song followed by "by" and then the artist of the song.
    @Override
    public String toString() {
        return title + " by " + artist;
    }
}

// This is the MusicPlayer class that handles playing and managing songs.
class MusicPlayer {
    // A LinkedList to store the songs in the playlist
    private LinkedList<Song> playlist = new LinkedList<>();

    // An integer to keep track of the current song index
    private int currentSongIndex = 0;

    // A boolean to indicate whether shuffle mode is enabled
    private boolean isShuffleMode = false;

    // A boolean to indicate whether repeat one song mode is enabled
    private boolean isRepeatOneSong = false;

    // Adds a song to the playlist
    public void addSong(Song song) {
        playlist.add(song);
        System.out.println("Song added to the playlist.");
    }

    // Removes a song from the playlist at the specified index
    public void removeSong(int index) {
        if (index >= 0 && index < playlist.size()) {
            playlist.remove(index);
            if (index <= currentSongIndex) {
                currentSongIndex--;
            }
            System.out.println("Song removed from the playlist.");
        } else {
            System.out.println("Invalid song index. Please try again.");
        }
    }

    // Toggles shuffle mode on and off
    public void toggleShuffleMode() {
        isShuffleMode = !isShuffleMode;
        if (isShuffleMode) {
            System.out.println("Shuffle mode is enabled.");
        } else {
            System.out.println("Shuffle mode is disabled.");
        }
    }

    // Toggles repeat one song mode on and off
    public void toggleRepeatOneSong() {
        isRepeatOneSong = !isRepeatOneSong;
        if (isRepeatOneSong) {
            System.out.println("Repeat one song mode is enabled.");
        } else {
            System.out.println("Repeat one song mode is disabled.");
        }
    }

    // Starts playing the current song in the playlist
    public void play() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty. Add songs first.");
        } else {
            Song currentSong = playlist.get(currentSongIndex);
            System.out.println("Now playing: " + currentSong);
        }
    }

    // Skips to the next song in the playlist
    public void next() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty. Add songs first.");
        } else {
            if (isShuffleMode) {
                currentSongIndex = new Random().nextInt(playlist.size());
            } else {
                currentSongIndex = (currentSongIndex + 1) % playlist.size();
            }
            play();
        }
    }

    // Skips to the previous song in the playlist
    public void previous() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty. Add songs first.");
        } else {
            if (isShuffleMode) {
                currentSongIndex = new Random().nextInt(playlist.size());
            } else {
                currentSongIndex = (currentSongIndex - 1 + playlist.size()) % playlist.size();
            }
            play();
        }
    }

    // Displays the current playlist with song numbers
    public void displayPlaylist() {
        System.out.println("Playlist:");
        int songNumber = 1;
        for (Song song : playlist) {
            System.out.println(songNumber + ". " + song);
            songNumber++;
        }
    }
}

public class Main {
    // Here's our main program where the user interacts with the player.
    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add a song");
            System.out.println("2. Remove a song");
            System.out.println("3. Toggle shuffle mode");
            System.out.println("4. Toggle repeat one song");
            System.out.println("5. Play current song");
            System.out.println("6. Play next song");
            System.out.println("7. Play previous song");
            System.out.println("8. Display playlist");
            System.out.println("9. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Adding songs to the playlist
                    System.out.print("Enter the title of the song: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter the artist of the song: ");
                    String artist = scanner.nextLine();
                    Song song = new Song(title, artist);
                    player.addSong(song);
                    System.out.println("Song added to the playlist.");
                    break;
                case 2:
                    // Removing a song from the playlist
                    System.out.print("Enter the index of the song to remove: ");
                    int indexToRemove = scanner.nextInt();
                    player.removeSong(indexToRemove - 1); // Adjust for 0-based index
                    break;
                case 3:
                    // Toggling shuffle mode on or off
                    player.toggleShuffleMode();
                    break;
                case 4:
                    // Toggling repeat mode for playing one song repeatedly
                    player.toggleRepeatOneSong();
                    break;
                case 5:
                    // Playing the current song
                    player.play();
                    break;
                case 6:
                    // Moving on to the next song
                    player.next();
                    break;
                case 7:
                    // Going back to the previous song
                    player.previous();
                    break;
                case 8:
                    // Displaying the current playlist
                    player.displayPlaylist();
                    break;
                case 9:
                    // Saying goodbye and exiting the program
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    // Handling an invalid choice
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
