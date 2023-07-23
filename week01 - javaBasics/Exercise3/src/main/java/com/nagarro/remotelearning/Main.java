package Exercise3.src.main.java.com.nagarro.remotelearning;

public class Main {
	public static String fromIntToBinaryHomeMade(int number){
        if (number == 0)
            return "";
        return fromIntToBinaryHomeMade(number / 2) + (number % 2);
	}

	public static String fromIntToBinaryBorrowed(int number){
		return Integer.toBinaryString(number);
	}

	public static void main(String... args){
		try{
			System.out.println("from int to binary without using a library:\n" + fromIntToBinaryHomeMade(Integer.parseInt(args[0])));
			System.out.println("");
			System.out.println("from int to binary using a library:\n" + fromIntToBinaryBorrowed(Integer.parseInt(args[0])));
		}catch(java.lang.ArrayIndexOutOfBoundsException e){
			System.out.println("no arguments were given. exiting the program..");
			System.exit(1);
		}
	}
}
