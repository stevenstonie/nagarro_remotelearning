package com.nagarro.remotelearning.classes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class StudentCrudRepositoryImpl implements StudentCrudRepository {
	private Map<Integer, Student> students;
	private ObjectMapper objectMapper;
	private String jsonFilePath;

	public StudentCrudRepositoryImpl() {
		students = new HashMap<>();
		objectMapper = new ObjectMapper();

		this.jsonFilePath = "src/main/resources/students.json";
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	@Override
	public Student save(Student student) {
		Student studentToSave = students.get(student.getId());

		if (studentToSave != null) {
			saveStudentCredentials(studentToSave, student);
		} else {
			students.put(student.getId(), student);
		}

		return studentToSave;
	}

	@Override
	public Optional<Student> findById(int id) {
		Student student = students.get(id);

		if (student != null) {
			return Optional.of(student);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Iterable<Student> findAll() {
		return students.values();
	}

	@Override
	public void deleteById(int id) {
		students.remove(id);
	}

	public int getLength() {
		return students.size();
	}

	public void saveStudentsToJsonFile() {
		try {
			String jsonSerializer = objectMapper.writeValueAsString(students.values());
			Files.write(Paths.get(jsonFilePath), jsonSerializer.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while saving students to json file");
		}
	}

	public void printAllStudentsNames() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for (Student student : students.values()) {
			System.out.println(student.getFirstName() + " " + student.getLastName());
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	public void clearJsonFile() {
		try {
			Files.write(Paths.get(jsonFilePath), "".getBytes());
		} catch (IOException e) {
			System.out.println("Error while clearing json file: " + e.getMessage());
		}
	}

	public void loadStudentsFromJsonFile() {
		createJsonFileIfNotFound();
		this.students.clear();

		try {
			Student[] studentsArr = objectMapper.readValue(new File(jsonFilePath), Student[].class);

			for (Student student : studentsArr) {
				this.students.put(student.getId(), student);
			}

		} catch (Exception e) {
			System.out.println("Error while reading students from the json file: " + e.getMessage());
		}
	}

	private void createJsonFileIfNotFound() {
		File file = new File(jsonFilePath);

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Error while creating json file: " + e.getMessage());
			}
		}
	}

	private void saveStudentCredentials(Student studentToSave, Student student) {
		studentToSave.setFirstName(student.getFirstName());
		studentToSave.setLastName(student.getLastName());
		studentToSave.setDateOfBirth(student.getDateOfBirth());
		studentToSave.setGraduated(student.isGraduated());
		studentToSave.setAddress(student.getAddress());
	}
}
