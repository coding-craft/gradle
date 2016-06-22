package com.student.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.model.Student;
import com.student.service.StudentRegistrationService;
import com.student.service.exception.StudentUpdateException;

@Service
public class StudentRegistrationServiceImpl extends TempStudentData implements StudentRegistrationService {

    @Override
    public void registerStudent(Student student) {
        students.put(student.getId(), student);
    }

    @Override
    public void registerStudents(List<Student> newStudents) {
        newStudents.stream().forEach((mapEntry) -> students.put(mapEntry.getId(), mapEntry));
    }

    @Override
    public void update(Student student) {
        Student existingStudent = students.get(student.getId());
        if(existingStudent == null){
            throw new StudentUpdateException("Unable to update student");
        }

        students.put(existingStudent.getId(), student);
    }

    @Override
    public void deregisterStudent(Long studentId) {
        students.remove(studentId);
    }

    @Override
    public void deregisterStudents(List<Long> studentIds) {
        studentIds.stream().forEach((key) -> students.remove(key));
    }
}
