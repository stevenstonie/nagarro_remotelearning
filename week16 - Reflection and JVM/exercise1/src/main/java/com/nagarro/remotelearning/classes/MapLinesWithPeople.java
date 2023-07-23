package com.nagarro.remotelearning.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import com.nagarro.remotelearning.week1ex1.Person;

public class MapLinesWithPeople {
    private List<Person> people;
    private String pathOfPeopleInput;

    public MapLinesWithPeople(String pathOfPeopleInput) {
        people = new ArrayList<>();
        this.pathOfPeopleInput = pathOfPeopleInput;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void insertPeopleIntoList() {
        try (BufferedReader reader = new BufferedReader(new FileReader(pathOfPeopleInput));) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(", ");

                Person person = createPerson(tokens);

                people.add(person);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public Person createPerson(String... tokens) {
        Class<?> personClassInstance = Person.class;
        Constructor<?> constructor;
        try {
            constructor = personClassInstance.getConstructor(String.class, String.class,
                    String.class, String.class);

            return buildPersonFromTokens(tokens, constructor);

        } catch (NoSuchMethodException e) {
            System.err.println("NoSuchMethodException: " + e.getMessage());
        } catch (SecurityException e) {
            System.err.println("SecurityException: " + e.getMessage());
        }

        return null;
    }

    public void printPeople() {
        for (Person person : people) {
            System.out.println(person);
        }
    }

    private Person buildPersonFromTokens(String[] tokens, Constructor<?> constructor) {
        try {
            if (tokens.length == 3) {
                return (Person) constructor.newInstance(tokens[0], tokens[1], tokens[2], null);
            }
            if (tokens.length == 4) {
                return (Person) constructor.newInstance(tokens[0], tokens[1], tokens[2], tokens[3]);
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return null;
    }
}
