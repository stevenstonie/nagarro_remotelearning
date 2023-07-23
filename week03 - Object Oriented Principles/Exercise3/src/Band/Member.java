package Band;

import Band.Instruments.Instrument;

public class Member {
	private String name;
	private Instrument instrument;

	public Member(String name) {
		this.name = name;
	}

	void playInstrument(Instrument instrument) {
		if (instrument == null) {
			System.out.println("no instrument to play");
		} else {
			System.out.println(name + " is playing " + instrument.toString());
		}
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public Instrument getInstrument() {
		return instrument;
	}
}
