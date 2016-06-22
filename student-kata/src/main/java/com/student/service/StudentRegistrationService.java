package com.student.service;

import java.util.List;

import com.student.model.Student;

public interface StudentRegistrationService {

    void registerStudent(Student student);

    void registerStudents(List<Student> students);

    void update(Student student);

    void deregisterStudent(Long studentId);

    void deregisterStudents(List<Long> studentIds);
}
