package com.student.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;

import com.student.AbstractStudentTest;
import com.student.model.Student;

public class StudentRegistrationTest extends AbstractStudentTest {

    @Test
    public void canRegisterAStudentWithDetails() {
        studentRegistrationService.registerStudent(student);

        Student student = studentFinderService.getStudent(1001l);

        assertEquals("Raj", student.getName());
    }

    @Test
    public void createStudentUsingStudentBuilder() {
        Student student = studentBuilder.id(1005L).name("Jojest").address("23, Cyprus Road, Basingstoke, UK").build();

        assertNotNull("Student is Null", student);
        assertEquals("Jojest", student.getName());
        assertEquals(Long.valueOf(1005L), student.getId());
        assertEquals("23, Cyprus Road, Basingstoke, UK", student.getAddress());
    }

    @Test
    public void registerCollectionOfStudents() {
        students.add(student);
        students.add(studentBuilder.id(1002L).name("Bhanu").address("19, Cyprus road, Basingstoke, UK").build());
        students.add(studentBuilder.id(1003L).name("Amir").address("20, Cyprus road, Basingstoke, UK").build());
        students.add(studentBuilder.id(1004L).name("Raj").address("21, Cyprus road, Basingstoke, UK").build());

        studentRegistrationService.registerStudents(students);

        Collection<Student> students = studentFinderService.listStudents();

        assertNotNull("Cant find any records for list students!", students);
        assertEquals("Could not retrieve all records", 4, students.size());

    }

    @Test
    public void updateStudentAddress() {
        // Initial student data
        studentRegistrationService.registerStudent(student);

        // modify student data and update
        student.setAddress("50, Cyprus road, Basingstoke, UK");
        studentRegistrationService.update(student);

        Student updatedStudent = studentFinderService.getStudent(student.getId());
        assertNotNull(updatedStudent);
        assertEquals("Address is not updated", "50, Cyprus road, Basingstoke, UK", student.getAddress());
    }
}