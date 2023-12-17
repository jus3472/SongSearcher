import java.io.FileNotFoundException;

/**
 * Frontend interface responsible for user interaction and command handling in the
 * Song Searcher app. This interface defines the methods that the Frontend class
 * must implement.
 */
public interface FrontendInterface {

    /**
     * Starts the main command loop for the user.
     * - Prompts the user to select a command from the main menu.
     * - Calls the corresponding method for the selected command.
     * - Handles invalid input by providing instructive feedback to the user.
     * - Continues looping until the user exits the app.
     */
    public void startMainMenu();

    /**
     * Handles the command to specify (and load) a data file.
     * - Prompts the user for the path to a data file.
     * - Passes the file path to the backend for data loading.
     * - Displays feedback to the user regarding the success or failure of the operation.
     * - @param filePath the file path of the data file
     * - @throws FileNotFoundException if the specified file is not found
     */
    public void handleLoadDataFile(String filePath) throws FileNotFoundException;
    
    /**
     * Handles the command to list all songs that have a specific danceability score.
     * Parameters: threshold - the danceability score threshold for filtering songs.
     * - Prompts the user to enter a danceability score threshold.
     * - Passes the threshold to the backend to retrieve and display relevant songs.
     * - Displays the list of songs that meet the threshold
     * @param threshold Minimum danceability threshold
     */
    public void handleListSongsByDanceability(double threshold);

    /**
     * Handles the command that shows the average danceability score in the loaded dataset.
     * - Calls the backend to calculate and retrieve the average danceability score.
     * - Displays the average danceability score to the user.
     */
    public void handleShowAverageDanceability();

    /**
     * Handles the command to exit the app.
     */
    public void handleExitApp();

}
