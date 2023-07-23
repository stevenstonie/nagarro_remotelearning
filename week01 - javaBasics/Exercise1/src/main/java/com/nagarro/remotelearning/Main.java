package Exercise1.src.main.java.com.nagarro.remotelearning;

import Exercise1.Sources.Person;
import Exercise1.Sources.ReadFromFile;
import java.io.IOException;
import java.util.HashSet;

public class Main {
	public static void main(String args[]) throws IOException {
		String[] arrayOfStrings = ReadFromFile.readLinesFromTextFile("./Exercise1/Sources/W1P1input.txt");
		int nbOfLinesInFile = arrayOfStrings.length;
		HashSet<String> peopleSet = new HashSet<>();

		for (String line : arrayOfStrings) {
			String[] tokens = line.split(", ");

			Person person = new Person(tokens[0], tokens[1], tokens[2]);
			try {
				person.setDeceaseDate(tokens[3]);
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {
				person.setDeceaseDate("still alive");
			} finally {
				peopleSet.add(person.toString());
			}
		}

		int uniqueNbOfLinesInFile = peopleSet.size();
		for (String line : peopleSet) {
			System.out.println(line);
		}

		System.out.println(
				"nb of lines in file: " + nbOfLinesInFile + "\nnb of unique lines in file:" + uniqueNbOfLinesInFile);
	}
}
