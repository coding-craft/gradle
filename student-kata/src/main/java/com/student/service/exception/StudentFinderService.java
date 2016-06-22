package com.student.service.exception;

import java.util.Collection;

import com.student.model.Student;

public interface StudentFinderService {

    Student getStudent(Long id);

    Collection<Student> listStudents();
}
