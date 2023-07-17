package Music;

import java.util.List;

public class MusicPlayer {
	class LocalPlaylist {
		private List<Song> songs;
		private String name;
		private List<String> genres;
		private String duration;
	}

	public MusicPlayer() {

	}

	MusicPlayer(List<Song> songs, String name, List<String> genres, String duration) {
		this.name = name;
		LocalPlaylist localPlaylist = new LocalPlaylist();
		localPlaylist.songs = songs;
		localPlaylist.genres = genres;
		localPlaylist.duration = duration;
		playlists = List.of(localPlaylist);
	}

	private String name;
	private String version;
	private float price;
	private List<LocalPlaylist> playlists;

	enum Color {
		Black,
		White,
		Red,
		Blue,
		Green,
		Yellow,
		Orange,
		Purple,
		Pink
	}

	public void addSongs(List<Song> songs) {
		LocalPlaylist localPlaylist = new LocalPlaylist();
		localPlaylist.songs = songs;
		for (Song song : songs) {
			localPlaylist.duration += song.getDuration();
		}
		if (playlists == null) {
			playlists = List.of(localPlaylist);
		} else {
			playlists.add(localPlaylist);
		}
	}

	public void playAllSongs() {
		for (LocalPlaylist localPlaylist : playlists) {
			for (Song song : localPlaylist.songs) {
				System.out.println("Playing " + song.getBand().getName() + " - " + song.getName());
			}
		}
	}
}
