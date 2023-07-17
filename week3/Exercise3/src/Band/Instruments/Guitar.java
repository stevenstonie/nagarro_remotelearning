package Band.Instruments;

public class Guitar extends Instrument {
	private int nb_of_strings;

	@Override
	public void makeSound() {
		System.out.println("*stairway to heavens plays*");
	}
}
