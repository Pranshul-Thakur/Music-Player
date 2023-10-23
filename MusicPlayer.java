import java.util.LinkedList;

import java.util.Scanner;
import java.util.Random;

class Song {
    // This is the Song class, we use it to store song information.
    private String title;
    private String artist;

    public Song(String title, String artist) {
        // This is the constructor for creating a new song.
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        // This method returns the title of the song.
        return title;
    }

    public String getArtist() {
        // This method returns the artist of the song.
        return artist;
    }

    @Override
    public String toString() {
        // This method provides a nice formatted string for the song.
        return title + " by " + artist;
    }
}

class MusicPlayer {
    // This is our MusicPlayer class that handles playing and managing songs.
    private LinkedList<Song> playlist = new LinkedList<>();
    private int currentSongIndex = 0;
    private boolean isShuffleMode = false;
    private boolean isRepeatOneSong = false;

    public void addSong(Song song) {
        // You can use this method to add a song to the playlist.
        playlist.add(song);
    }

    public void removeSong(int index) {
        // This method allows you to remove a song from the playlist.
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

    public void toggleShuffleMode() {
        // This toggles the shuffle mode on and off for random song selection.
        isShuffleMode = !isShuffleMode;
        if (isShuffleMode) {
            System.out.println("Shuffle mode is enabled.");
        } else {
            System.out.println("Shuffle mode is disabled.");
        }
    }

    public void toggleRepeatOneSong() {
        // You can use this to toggle repeat mode for playing one song repeatedly.
        isRepeatOneSong = !isRepeatOneSong;
        if (isRepeatOneSong) {
            System.out.println("Repeat one song mode is enabled.");
        } else {
            System.out.println("Repeat one song mode is disabled.");
        }
    }

    public void play() {
        // When you call this, it plays the current song in the playlist.
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty. Add songs first.");
        } else {
            Song currentSong = playlist.get(currentSongIndex);
            System.out.println("Now playing: " + currentSong);
        }
    }

    public void next() {
        // This method lets you play the next song in the playlist.
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

    public void previous() {
        // Use this to go back to the previous song in the playlist.
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

    public void displayPlaylist() {
        // This displays the current playlist with song numbers.
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
