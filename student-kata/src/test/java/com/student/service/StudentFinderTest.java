package com.student.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.student.AbstractStudentTest;
import com.student.model.Student;

public class StudentFinderTest extends AbstractStudentTest {

    @Test
    public void findAStudentShouldReturnARecord() {
        studentRegistrationService.registerStudent(student);

        Student student = studentFinderService.getStudent(1001L);

        assertNotNull(student);
        assertEquals("Raj", student.getName());
        assertEquals(Long.valueOf(1001L), student.getId());
        assertEquals("10, Cyprus Road, Basingstoke, UK", student.getAddress());
    }

    @Test
    public void getStudentDetails() {

        Student foundStudent = studentFinderService.getStudent(1001L);

        assertNotNull("Student not found!", foundStudent);
        assertEquals("Raj", foundStudent.getName());
        assertEquals("10, Cyprus Road, Basingstoke, UK", foundStudent.getAddress());
    }
}
