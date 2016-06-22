package com.student.service.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.student.model.Student;
import com.student.service.exception.StudentFinderService;

@Service
public class StudentFinderServiceImpl extends TempStudentData implements StudentFinderService {

    @Override
    public Student getStudent(Long id) {
        return students.get(id);
    }

    @Override
    public Collection<Student> listStudents() {
        return students.values();
    }
}
