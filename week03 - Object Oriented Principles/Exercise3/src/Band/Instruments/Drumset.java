package Band.Instruments;

public class Drumset extends Instrument {
	private int nb_of_drums;

	@Override
	public void makeSound() {
		System.out.println("ba dum tss..");
	}
}
