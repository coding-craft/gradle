package com.student.web.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.student.model.Student;
import com.student.service.exception.StudentFinderService;

@RestController
public class StudentFinderController {
    @Autowired
    private StudentFinderService studentFinder;

    @RequestMapping(value = "/api/students", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Student>> listAllStudents() {
        return new ResponseEntity<Collection<Student>>(studentFinder.listStudents(), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/student/{studentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") Long studentId) {
        return new ResponseEntity<Student>(studentFinder.getStudent(studentId), HttpStatus.OK);
    }
}
