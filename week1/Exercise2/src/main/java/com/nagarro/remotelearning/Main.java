package Exercise2.src.main.java.com.nagarro.remotelearning;

import java.util.Arrays;

public class Main {
	public static void main(String... args) {
		if (args.length == 0) {
			System.out.println("no arguments were given. Exiting program.. ");
			System.exit(1);
		}
		System.out.print('1');
		boolean[] sieveOfEratosthenes = new boolean[Integer.parseInt(args[0]) + 1];
		Arrays.fill(sieveOfEratosthenes, true); // is there a way to initialize the array as true?

		for (int a = 2; a < sieveOfEratosthenes.length; a++) {
			System.out.print(", " + a);
			if (sieveOfEratosthenes[a] == true) {
				for (int b = a + 1; b < sieveOfEratosthenes.length; b++) {
					if (b % a == 0)
						sieveOfEratosthenes[b] = false;
				}
				System.out.print("-PRIME");
			}
		}
	}
}
