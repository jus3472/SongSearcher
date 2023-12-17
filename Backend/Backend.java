import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Backend class responsible for handling data-related operations in the Song Searcher app.
 * This class provides methods for reading data from a CSV file, calculating average danceability, and retrieving songs within a specified danceability range.
 * The backend maintains a list of songs for data storage and manipulation.
 * 
 * @author Justin Jiang
 */
public class Backend implements BackendInterface {

    // List to store songs
    private List<Song> songList;

    /**
     * Constructor for Backend.
     *
     * @param songList Initial list of songs (must not be null)
     * @throws NullPointerException if songList is null
     */
    public Backend(List<Song> songList) throws NullPointerException {
        if (songList == null) {
            throw new NullPointerException("Null value in songList");
        }
        this.songList = songList;
    }

    /**
     * Reads data from a CSV file and adds it to the songList.
     *
     * @param csvFile CSV text file containing information about the songs
     * @return Iterable of songs updated to include the new songs
     * @throws FileNotFoundException if the CSV file cannot be located
     */
    @Override
    public Iterable<Song> readFileData(File csvFile) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(csvFile)) {
            scanner.nextLine(); // Ignore the first line as it describes the data

            while (scanner.hasNextLine()) {
                String[] songAttributes = parseCsvLine(scanner.nextLine());

                this.songList.add(new Song(
                        songAttributes[1], songAttributes[0],
                        Integer.parseInt(songAttributes[3]), songAttributes[2],
                        Double.parseDouble(songAttributes[6])
                ));
            }
        }

        return this.songList;
    }

    /**
     * Calculates the average danceability of the songList.
     *
     * @return double representing the average danceability rating
     */
    @Override
    public double calculateAvgDanceability() {
        double sum = 0;
        int numSongs = 0;

        Iterator<Song> iterator = songList.iterator();

        while (iterator.hasNext()) {
            sum += iterator.next().getDanceability();
            numSongs++;
        }

        return (numSongs != 0) ? sum / numSongs : 0;
    }

    /**
     * Retrieves songs within a specified danceability range.
     *
     * @param minDanceability Minimum acceptable danceability score
     * @return ArrayList of songs that meet the threshold
     * @throws IllegalArgumentException when minDanceability is negative
     */
    @Override
    public ArrayList<Song> getSongsWithinBounds(double minDanceability) throws IllegalArgumentException {
        if (Double.compare(minDanceability, 0) < 0) {
            throw new IllegalArgumentException("Negative danceability");
        }

        ArrayList<Song> songListWithinBounds = new ArrayList<>();

        Iterator<Song> iterator = songList.iterator();

        while (iterator.hasNext()) {
            Song currentSong = iterator.next();
            if (Double.compare(currentSong.getDanceability(), minDanceability) >= 0) {
                songListWithinBounds.add(currentSong);
            }
        }

        return songListWithinBounds;
    }

    /**
     * Parses a CSV line into an array of attributes.
     *
     * @param line CSV line to be parsed
     * @return String array of attributes
     */
    private String[] parseCsvLine(String line) {
        return line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
    }

}
