package Band.Instruments;

public class Instrument {
	public enum Material {
		Wood,
		Steel,
		Glass,
		Plastic
	}

	private Material material;

	public void makeSound() {
		System.out.println("Making a sound");
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
}
