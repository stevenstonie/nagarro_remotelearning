package Band;

import java.util.List;

import Music.Song;

public class Band {
	private String name;
	private List<Member> members;
	private List<Song> songs;
	private List<String> albums;
	private List<String> genres;

	public Band(String name, List<Member> members, List<Song> songs, List<String> albums, List<String> genres) {
		this.name = name;
		this.members = members;
		this.songs = songs;
		this.albums = albums;
		this.genres = genres;
	}

	// Getters / Setters

	public void addMembers(List<Member> members) {
		if (this.members == null) {
			this.members = members;
		} else {
			this.members.addAll(members);
		}
	}

	public void playInstrument(Member member) {
		member.playInstrument(member.getInstrument());
	}

	public void addSongs(List<Song> songs) {
		if (this.songs == null) {
			this.songs = songs;
		} else {
			this.songs.addAll(songs);
		}
	}

	public List<Song> getAllSongs() {
		return songs;
	}

	public String getName() {
		return name;
	}
}
