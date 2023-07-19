package com.nagarro.remotelearning.classes;

import java.util.Optional;

public interface StudentCrudRepository {

    /**
     * Saves the given student. If the given student is already present in the repository then the method will act as an
     * update operation.
     *
     * @param student must not be {@literal null}
     * @return the saved student; will never be {@literal null}
     * @throws IllegalArgumentException in case the given {@literal student} is {@literal null}.
     */
    Student save(Student student);

    /**
     * Retrieves a student by its id.
     *
     * @param id the unique student identifier
     * @return the student with the given id or {@literal Optional#empty()} if none found.
     */
    Optional<Student> findById(int id);

    /**
     * Returns all students.
     *
     * @return all students
     */
    Iterable<Student> findAll();

    /**
     * Deletes the student with the given id.
     *
     * @param id id the unique student identifier
     */
    public void deleteById(int id);
}
