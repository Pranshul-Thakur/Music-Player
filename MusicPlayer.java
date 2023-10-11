import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class MusicPlayer {
    private static int c = 0;
    private static Node head = null;
    private static int b = 0;
    private static int pos = 0;
    private static int p = 0;
    private static int l;
    private static int inf;
    private static String str;
    private static String[] songs = {
            "Blinding_lights.wav", "Too_many_nights.wav", "Art_deco.wav",
            "Night_changes.wav", "Money_Trees.wav", "Perfect.wav",
            "Popular.wav", "Ice_cream.wav", "After_dark.wav",
            "Calm_down.wav", "Sdp_Interlude.wav"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            mainMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    list();
                    System.out.println("Press '0' to return to the main menu.");
                    scanner.nextInt();
                    break;
                case 2:
                    list();
                    System.out.println("Press '0' to stop adding.");
                    while (createPlaylist()) {
                    }
                    break;
                case 3:
                    displayPlaylist();
                    System.out.println("Press '0' to return to the main menu.");
                    scanner.nextInt();
                    break;
                case 4:
                    displayPlaylist();
                    playSearch();
                    break;
                case 5:
                    System.out.println("The playlist currently is:");
                    displayPlaylist();
                    System.out.println("List of songs available is:");
                    list();
                    createPlaylist();
                    break;
                case 6:
                    prev();
                    break;
                case 7:
                    next();
                    break;
                case 8:
                    displayPlaylist();
                    deleteSong();
                    break;
                case 9:
                    System.out.println("Thank you for using our music player.\nPlease use us again....");
                    System.exit(0);
                default:
                    System.out.println("Please select a valid option.");
            }
        }
    }

    private static void mainMenu() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("                              MAIN MENU");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("   1   |  Display all available songs");
        System.out.println("   2   |  Create a playlist");
        System.out.println("   3   |  Show playlist");
        System.out.println("   4   |  Play the music from the playlist");
        System.out.println("   5   |  Add a song to the playlist");
        System.out.println("   6   |  Play previous track");
        System.out.println("   7   |  Play next track");
        System.out.println("   8   |  Delete a song from the playlist");
        System.out.println("   9   |  Exit music player");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("                        Enter your choice below");
    }

    private static void list() {
        System.out.println("----------------------------------------------------------------------");
        for (int i = 0; i < songs.length; i++) {
            System.out.println((i + 1) + "." + songs[i]);
        }
        System.out.println("12. Back To Main Menu.");
        System.out.println("----------------------------------------------------------------------");
    }

    private static boolean createPlaylist() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter The Song Index Number and Its Position: ");
        int n = scanner.nextInt();
        if (n == 0) {
            return false;
        }

        int p = scanner.nextInt();
        if (p <= 0 || p > c + 1) {
            System.out.println("Enter a valid position.");
            return true;
        } else {
            c++;
            Node newNode = new Node(n);
            if (p == 1) {
                newNode.next = head;
                head = newNode;
            } else {
                Node current = head;
                for (int i = 0; i < p - 2; i++) {
                    current = current.next;
                }
                newNode.next = current.next;
                current.next = newNode;
            }
            return true;
        }
    }

    private static void displayPlaylist() {
        int i = 1;
        Node current = head;
        if (current == null) {
            System.out.println("Playlist is empty.");
        } else {
            while (current != null) {
                int songIndex = current.data;
                String songName = assign(songIndex);
                System.out.println("----------------------------------------------------------------------");
                System.out.println("   " + i + "   |  " + songName);
                System.out.println("----------------------------------------------------------------------");
                current = current.next;
                i++;
            }
        }
    }

    private static String assign(int b) {
        return songs[b - 1];
    }

    private static void song(String str) {
        System.out.println("The song currently playing is " + str);
        // Play the song here using Java's audio library or an external tool.
    }

    private static void playSearch() {
        Scanner scanner = new Scanner(System.in);
        int p = 0;
        Node current = head;
        System.out.print("Enter the Index of the song you want to play: ");
        pos = scanner.nextInt();
        while (current != null) {
            p++;
            if (p == pos) {
                b = 1;
                break;
            }
            current = current.next;
        }
        if (b == 1) {
            str = assign(current.data);
            song(str);
            inf = current.data;
        }
        l = p;
    }

    private static void prev() {
        int n;
        Node current = head;
        if (l == 0) {
            System.out.println("No song was played previously.");
        } else {
            int k = 0;
            while (current != null) {
                k++;
                if (l - 1 == k) {
                    n = current.data;
                    str = assign(n);
                    song(str);
                    break;
                }
                current = current.next;
            }
            p--;
        }
    }

    private static void next() {
        Node current = head;
        int k = 0;
        int size = 0;
        while (current != null) {
            size++;
            current = current.next;
        }
        if (l == size) {
            System.out.println("This is the last song of the playlist.");
        } else {
            current = head;
            while (current != null) {
                k++;
                if (l + 1 == k) {
                    int n = current.data;
                    str = assign(n);
                    song(str);
                    break;
                }
                current = current.next;
                p = p + 1;
            }
        }
    }

    private static void deleteSong() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Index of the song you want to delete: ");
        int p = scanner.nextInt();
        if (p <= 0 || p > c) {
            System.out.println("Enter a valid position.");
        } else {
            c--;
            if (p == 1) {
                head = head.next;
            } else {
                Node current = head;
                for (int i = 0; i < p - 2; i++) {
                    current = current.next;
                }
                Node temp = current.next;
                current.next = temp.next;
                temp = null;
            }
        }
    }

    private static void info() {
        switch (inf) {
            case 1:
                System.out.println("Information for song 1...");
                break;
            case 2:
                System.out.println("Information for song 2...");
                break;
            // Add more cases for other songs.
            default:
                System.out.println("No song is being played currently, please play a song to get more info");
        }
    }
}
