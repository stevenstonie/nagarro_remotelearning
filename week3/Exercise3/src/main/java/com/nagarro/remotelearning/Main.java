package main.java.com.nagarro.remotelearning;

import java.util.List;

import Band.Band;
import Band.Member;
import Band.Instruments.Instrument;
import Music.CDPlayer;
import Music.Song;

public class Main {
	public static void main(String... args) {
		Band rolling_stones = new Band("Rolling Stones", null, null, null, null);

		Member mickJagger = new Member("Mick Jagger");
		Member keithRichards = new Member("Keith Richards");
		Member charlieWatts = new Member("Charlie Watts");
		Member billWyman = new Member("Bill Wyman");
		Member ronnieWood = new Member("Ronnie Wood");
		Instrument guitar = new Instrument();
		Instrument drums = new Instrument();

		guitar.setMaterial(Instrument.Material.Wood);
		drums.setMaterial(Instrument.Material.Wood);

		mickJagger.setInstrument(guitar);
		keithRichards.setInstrument(guitar);
		charlieWatts.setInstrument(drums);
		billWyman.setInstrument(guitar);
		ronnieWood.setInstrument(guitar);

		rolling_stones.addMembers(List.of(mickJagger, keithRichards, charlieWatts, billWyman, ronnieWood));

		rolling_stones.playInstrument(mickJagger);
		rolling_stones.playInstrument(keithRichards);

		Song song1 = new Song("paint it black", "classic rock", "1965", "3:00", rolling_stones);
		Song song2 = new Song("satisfaction", "classic rock", "1964", "2:56", rolling_stones);

		rolling_stones.addSongs(List.of(song1, song2));

		CDPlayer cd_player = new CDPlayer();

		cd_player.addSongs(rolling_stones.getAllSongs());
		cd_player.playAllSongs();
	}
}
