package Exercise4.src.main.java.com.nagarro.remotelearning;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Main {
	public static boolean isPalindrome(String number){
		for(int i=0; i < number.length()/2; i++){
			if(number.charAt(i) != number.charAt(number.length()-1 - i))
				return false;
		}
		return true;
	}
	
	// unfortunately its specifically made for 10000019
	public static long palindromeCounterToMaxLong(long number){
		long counter = 0, incrementer = 109;
		char lastFirstDigit = '1';
		long lastIncrementerLength = 3;

		try{
			Path filePath = Path.of("./Exercise4/tests/increments_and_palins.txt");
			Files.writeString(filePath, "");	// deletes file contents after opening it

			for(long a = number; a < Long.MAX_VALUE; a = incrementer * number){
				if(isPalindrome(Long.toString(a))){
					++counter;
					Files.writeString(filePath, Long.toString(incrementer) + " " + Long.toString(a) + "\n", StandardOpenOption.APPEND);
					// dk if i should close it (or how to do it)
				}

				incrementer+=10;
				String str_incrementer = Long.toString(incrementer);
				if(str_incrementer.charAt(0) != lastFirstDigit){
					lastFirstDigit = str_incrementer.charAt(0);
					--incrementer;
				}
				if(str_incrementer.length() > lastIncrementerLength){
					lastIncrementerLength = str_incrementer.length();
					incrementer+= 9;
				}
			}

		}catch(IOException e){
			e.printStackTrace();
		}

		return counter;
	}

	public static void main(String... args){
		System.out.println(palindromeCounterToMaxLong(10000019));
	}
}

// what i found: 
// -'increments_and_palins.txt' --> half + 1 of incrementers are (almost?) always part of the palindromes.
// -'increments_and_palins.txt' --> first and last digits of incrementers always sum up to 10
// -'diff_of_neighb_increms_output.txt' --> some differences in neighbouring incrementers are repeating
// -divisibility by 19 --> double the units digit and add it to the number without its units digit
// ex: 1444: 144 + 4x2 = 152. 152 is divisible by 19. (and further 152: 15 + 6x2 = 19 (div by 19))
// -incrementing by multiplication is better than by palindromes.
// ...
