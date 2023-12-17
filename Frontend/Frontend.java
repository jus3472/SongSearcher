import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 * Frontend class responsible for user interaction and command handling in the
 * Song Searcher app. This class drives an interactive loop of user prompts,
 * command execution, and result display. The user can load data, list songs by
 * danceability, show average danceability, and exit the app. The commands
 * interact with the Backend to perform data-related operations.
 * 
 * @author Justin Jiang
 */
public class Frontend implements FrontendInterface {

    private Backend backend; // Backend object for handling data
    private Scanner scanner; // Scanner for user input

    /**
     * Constructor for Frontend.
     *
     * @param backend Backend object for data handling
     * @param scanner Scanner for user input
     */
    public Frontend(Backend backend, Scanner scanner) {
        this.backend = backend;
        this.scanner = scanner;
    }

    /**
     * Starts the main menu loop for user interaction in the Song Searcher app.
     * Displays menu options, processes user input, and executes corresponding
     * commands.
     */
    @Override
    public void startMainMenu() {
        // Display initial menu options
        System.out.println("Hey there! Welcome to Song Searcher - Your Groovy Companion!");
        System.out.println("***********************************************");
        System.out.println("What's on your mind today?");
        System.out.println("1. Load up your favorite tunes (Load data file)");
        System.out.println("2. Find songs that match your vibe (List songs by danceability)");
        System.out.println("3. Check out the overall groove (Show average danceability)");
        System.out.println("4. Take a break from the music journey (Exit the app)");
        System.out.println("***********************************************");

        // Main menu loop
        while (true) {
            String userInput = scanner.next();

            // Process user input and execute corresponding commands
            if (userInput.equals("1")) {
                try {
                    // Command to load data file
                    handleLoadDataFile("/Users/justinjiang/VSCode Projects/song_searcher/Backend/songs.csv");
                    System.out.println("Your music library is loaded and ready for exploration!");
                } catch (FileNotFoundException e) {
                    System.out.println("Oops! Seems like there's a glitch in the playlist. Please check your file.");
                }
            } else if (userInput.equals("2")) {
                // Command to list songs by danceability
                System.out.println("Got a dance mood? Enter the danceability threshold:");

                if (scanner.hasNextDouble()) {
                    double userThreshold = scanner.nextDouble();
                    handleListSongsByDanceability(userThreshold);
                } else {
                    System.out.println("Invalid input. Please enter a number for the danceability threshold.");
                    scanner.next(); // Consume invalid input to avoid an infinite loop
                }
            } else if (userInput.equals("3")) {
                // Command to show average danceability
                handleShowAverageDanceability();
            } else if (userInput.equals("4")) {
                // Command to exit the app
                handleExitApp();
                break;
            } else {
                System.out.println("Oops! That's not a valid choice. Pick 1, 2, 3, or 4!");
            }

            System.out.println("What else would you like to do?");
        }
    }

    /**
     * Handles the command to load data from a file.
     *
     * @param filePath File path of the data file
     * @throws FileNotFoundException if the specified file is not found
     */
    @Override
    public void handleLoadDataFile(String filePath) throws FileNotFoundException {
        // Create a File object with the specified file path
        File file = new File(filePath);

        // Use the Backend to read data from the file and populate the songList
        backend.readFileData(file);
    }

    /**
     * Handles the command to list songs by danceability.
     *
     * @param threshold Minimum danceability threshold
     */
    @Override
    public void handleListSongsByDanceability(double threshold) {
        // Use the Backend to get a list of songs with danceability at or above the specified threshold
        Iterable<Song> songsAtThisThreshold = backend.getSongsWithinBounds(threshold);

        // Display the results based on whether songs are found or not
        if (!songsAtThisThreshold.iterator().hasNext()) {
            System.out.println("No songs found with at least " + threshold + " danceability. Time to explore new beats!");
        } else {
            System.out.println("Nice picks! Here are the songs with at least " + threshold + " danceability:");
            for (Song song : songsAtThisThreshold) {
                System.out.println(" - " + song.getTitle() + " by " + song.getArtist());
            }
        }
    }

    /**
     * Handles the command to show the average danceability of all songs.
     */
    @Override
    public void handleShowAverageDanceability() {
        // Use the Backend to calculate and retrieve the average danceability
        double average = backend.calculateAvgDanceability();

        // Display the average danceability to the user
        System.out.println("The average danceability across the tunes is " + average + ". Let the good vibes roll!");
    }

    /**
     * Handles the command to exit the application.
     */
    @Override
    public void handleExitApp() {
        // Display a farewell message
        System.out.println("Thanks for hanging out with Song Searcher! Catch you on the flip side!");
    }

    /**
     * Main method to instantiate and start the frontend.
     *
     * @param args Command line arguments (not used in this context)
     */
    public static void main(String[] args) {
        // Create an instance of Backend with an existing song list
        Backend backend = new Backend(new ArrayList<>());

        // Create an instance of Frontend with the Backend and Scanner
        Frontend frontend = new Frontend(backend, new Scanner(System.in));

        // Start the main menu loop
        frontend.startMainMenu();
    }
    
}
