/**
 * SongInterface defines the attributes and methods for a single song object.
 * It extends Comparable to support custom comparisons between songs.
 */
public interface SongInterface extends Comparable<Song> {

  /**
   * Gets the artist of the song.
   * 
   * @return String representing the artist
   */
  public String getArtist();

  /**
   * Gets the title of the song.
   * 
   * @return String representing the title
   */
  public String getTitle();

  /**
   * Gets the year the song was made.
   * 
   * @return int representing the year
   */
  public int getYear();

  /**
   * Gets the genre of the song.
   * 
   * @return String representing the genre
   */
  public String getGenre();

  /**
   * Gets the danceability score of the song.
   * 
   * @return double representing the danceability
   */
  public double getDanceability();

}
