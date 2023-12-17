/**
 * Represents a Song object with its attributes.
 * Implements Comparable for custom comparisons and SongInterface for method contracts.
 * 
 * @author Justin Jiang
 */
public class Song implements Comparable<Song>, SongInterface {

    private String artist;        // Artist who created the song
    private String title;         // Title of the song
    private int year;             // Year the song was made
    private String genre;         // Genre of the song
    private double danceability;  // Danceability score of the song

    /**
     * Constructor that defines the attributes of a Song object.
     * 
     * @param artist       Artist of the song
     * @param title        Title of the song
     * @param year         Year the song was made
     * @param genre        Genre of the song
     * @param danceability Danceability score of the song
     * @throws NullPointerException Thrown if any of the String values passed into constructor are null
     */
    public Song(String artist, String title, int year, String genre, double danceability)
            throws NullPointerException {

        if (artist == null) {
            throw new NullPointerException("Artist is null, can't declare Song object");
        }

        if (title == null) {
            throw new NullPointerException("Title is null, can't declare Song object");
        }

        if (genre == null) {
            throw new NullPointerException("Genre is null, can't declare Song object");
        }

        this.artist = artist;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.danceability = danceability;
    }

    /**
     * Returns the value of the Song's artist.
     * 
     * @return String
     */
    @Override
    public String getArtist() {
        return artist;
    }

    /**
     * Returns the value of the Song's title.
     * 
     * @return String
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Returns the value of the Song's year.
     * 
     * @return int
     */
    @Override
    public int getYear() {
        return year;
    }

    /**
     * Returns the value of the Song's genre.
     * 
     * @return String
     */
    @Override
    public String getGenre() {
        return genre;
    }

    /**
     * Returns the value of the Song's danceability score.
     * 
     * @return double
     */
    @Override
    public double getDanceability() {
        return danceability;
    }

    /**
     * Checks if one Song object is equal to another.
     * 
     * @param otherSong The Song object to compare with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object otherSong) {

        if (!(otherSong instanceof Song)) {
            return false;
        }

        Song castedSong = (Song) otherSong;

        // Compare all the attributes of a song
        return this.getArtist().equals(castedSong.getArtist())
                && this.getTitle().equals(castedSong.getTitle())
                && this.getYear() == castedSong.getYear()
                && this.getGenre().equals(castedSong.getGenre())
                && this.getDanceability() == castedSong.getDanceability();
    }

    /**
     * Compares the danceability of one Song object to another.
     * 
     * @param otherSong The Song object to compare with
     * @return 1 if this Song's danceability is greater, -1 if less, and 0 if equal
     */
    @Override
    public int compareTo(Song otherSong) {
        return Double.compare(this.danceability, otherSong.getDanceability());
    }

}
