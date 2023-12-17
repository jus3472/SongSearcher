import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * BackendInterface defines the methods that expose the required functionality of a song list
 * in the back-end to the front-end. The methods include reading data from a CSV file, retrieving songs within a specified danceability range, and calculating average danceability.
 */
public interface BackendInterface {

  /**
   * Reads data from a CSV file and adds it to the songList.
   *
   * @param csvFile CSV text file containing information about the songs
   * @return Iterable of songs updated to include the new songs
   * @throws FileNotFoundException if CSV file cannot be located
   */
  Iterable<Song> readFileData(File csvFile) throws FileNotFoundException;

  /**
   * Retrieves songs within a specified danceability range.
   *
   * @param minDanceability Minimum acceptable danceability score
   * @return ArrayList of songs that meet the threshold
   * @throws IllegalArgumentException when minDanceability is negative
   */
  ArrayList<Song> getSongsWithinBounds(double minDanceability) throws IllegalArgumentException;

  /**
   * Calculates the average danceability of the songList.
   *
   * @return double representing the average danceability rating
   */
  double calculateAvgDanceability();
  
}
