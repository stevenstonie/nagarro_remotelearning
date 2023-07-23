package com.nagarro.remotelearning;

import com.nagarro.remotelearning.classes.Address;
import com.nagarro.remotelearning.classes.Student;
import com.nagarro.remotelearning.classes.StudentCrudRepositoryImpl;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppTest {
    private Student student1;
    private Student student2;
    private final StudentCrudRepositoryImpl students = new StudentCrudRepositoryImpl();
    private StudentCrudRepositoryImpl studentsDeserialized = new StudentCrudRepositoryImpl();

    @BeforeEach
    public void createTwoStudentsAndSerializeThem() {
        students.clearJsonFile();

        student1 = new Student(0, "John", "Cena", new Date(1901, 11, 21), true,
                new Address("Buciuresti", "1000002", "ce-i asta"));
        student2 = new Student(1, "Jack", "Son", new Date(2047, 04, 12), false,
                new Address("Timisoreana", "123123", "que es esto"));

        students.save(student1);
        students.save(student2);
        students.saveStudentsToJsonFile();

        studentsDeserialized.loadStudentsFromJsonFile();
    }

    @Test
    public void testIfStudentDataIsStoredCorrectly() {
        for (int i = 0; i < students.getLength() - 1; i++) {
            assertEquals(students.findById(i).get().getFirstName(),
                    studentsDeserialized.findById(i).get().getFirstName());

            assertEquals(students.findById(i).get().getAddress().getCity(),
                    studentsDeserialized.findById(i).get().getAddress().getCity());
        }
    }

    @Test
    public void testUpdateOfStudentData() {
        students.findById(0).get().setFirstName("Gica");
        students.save(students.findById(0).get());
        students.saveStudentsToJsonFile();

        studentsDeserialized.loadStudentsFromJsonFile();

        assertEquals(students.findById(0).get().getFirstName(),
                studentsDeserialized.findById(0).get().getFirstName());
    }

    @Test
    public void testDeletionOfAStudent() {
        int nbOfStudentsBeforeDeletion = students.getLength();

        students.deleteById(0);
        students.saveStudentsToJsonFile();

        studentsDeserialized.loadStudentsFromJsonFile();

        assertEquals(nbOfStudentsBeforeDeletion - 1, studentsDeserialized.getLength());
    }

    @Test
    public void testIfAllStudentsAreReturned() {
        assertEquals(students.getLength(), studentsDeserialized.getLength());
    }
}
