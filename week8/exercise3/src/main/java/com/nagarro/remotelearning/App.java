package com.nagarro.remotelearning;

import com.nagarro.remotelearning.classes.Address;
import com.nagarro.remotelearning.classes.Student;
import com.nagarro.remotelearning.classes.StudentCrudRepositoryImpl;

public class App {
	public static void main(String... args) {
		StudentCrudRepositoryImpl newStudentRepo = new StudentCrudRepositoryImpl();
		newStudentRepo.loadStudentsFromJsonFile();

		newStudentRepo.save(new Student(48912, "jesse", "pinkman", new java.sql.Date(1983, 01, 02), true,
				new Address("Albaquerque", "random street", "12345")));

		newStudentRepo.printAllStudentsNames();

		newStudentRepo.saveStudentsToJsonFile();
	}
}
