package com.student.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.student.model.Student;
import com.student.service.StudentRegistrationService;

@RestController
public class StudentRegistrationController {

    @Autowired
    private StudentRegistrationService studentRegistrationService;

    @RequestMapping(value = "/api/student/single-registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> registerAStudent(@RequestBody Student student) {
        studentRegistrationService.registerStudent(student);
        return new ResponseEntity<Student>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/student/multiple-registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> registerMultipleStudents(@RequestBody List<Student> students) {
        studentRegistrationService.registerStudents(students);
        return new ResponseEntity<Student>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/student/single-deregistration/{studentId}", method = RequestMethod.DELETE)
    public void deregisterStudent(@PathVariable("studentId") Long studentId) {
        studentRegistrationService.deregisterStudent(studentId);
    }

    @RequestMapping(value = "/api/student/multiple-deregistration", method = RequestMethod.DELETE)
    public void deregisterStudent(@PathVariable("studentIds") List<Long> studentIds) {
        studentRegistrationService.deregisterStudents(studentIds);
    }
}
