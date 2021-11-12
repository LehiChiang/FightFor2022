package 二十个实例玩转Java8Stream;

import java.util.Objects;

public class Song {

    private String songName;
    private String artist;
    private int year;

    public Song(String songName, String artist, int year) {
        this.songName = songName;
        this.artist = artist;
        this.year = year;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return year == song.year &&
                Objects.equals(songName, song.songName) &&
                Objects.equals(artist, song.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songName, artist, year);
    }

    @Override
    public String toString() {
        return "Song{" +
                "songName='" + songName + '\'' +
                ", artist='" + artist + '\'' +
                ", year=" + year +
                '}';
    }

    private boolean checkInfoValidation(String name, String artist, int year) {
        if ((year < 1900 && year >2021) || (name.isEmpty() || name.isBlank()) || (artist.isEmpty() || artist.isBlank())) {
            return false;
        }
        return true;
    }
}
