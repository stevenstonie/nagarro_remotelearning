package Exercise2.src.main.java.com.nagarro.remotelearning;

import Exercise2.imports.Tank;

public class Main {
	public static void main(String... args) {
		Tank integerTank = new Tank();

		integerTank.pushIntoTank(5);
		integerTank.pushIntoTank(4);
		integerTank.pushIntoTank(3);
		integerTank.pushIntoTank(2);
		integerTank.popFromTank();
		integerTank.pushIntoTank(1);
		integerTank.printTank();
		System.out.println();

		integerTank.finalize();
		System.out.println();

		System.out.println("deleting...");
		integerTank.clearTank();
		System.out.println();

		integerTank.finalize();
	}
}
