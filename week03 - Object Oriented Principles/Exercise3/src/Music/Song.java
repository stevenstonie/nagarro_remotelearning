package Music;

import Band.Band;

public class Song {
	private String name;
	private String genre;
	private String release_date;
	private String duration;
	private Band band;

	public Song(String name, String genre, String release_date, String duration, Band band) {
		this.name = name;
		this.genre = genre;
		this.release_date = release_date;
		this.duration = duration;
		this.band = band;
	}

	// Setters / Getters

	public String getDuration() {
		return duration;
	}

	public Band getBand() {
		return band;
	}

	public String getName() {
		return name;
	}
}
