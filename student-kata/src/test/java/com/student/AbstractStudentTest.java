package com.student;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.student.main.Application;
import com.student.model.Student;
import com.student.service.StudentRegistrationService;
import com.student.service.exception.StudentFinderService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class AbstractStudentTest {
    @Autowired
    protected StudentRegistrationService studentRegistrationService;

    @Autowired
    protected StudentFinderService studentFinderService;

    protected Student student;

    protected List<Student> students = new ArrayList<>();
    protected Student.StudentBuilder studentBuilder = new Student.StudentBuilder();

    @Before
    public void createStudent() {
        student = studentBuilder.id(1001L).name("Raj").address("10, Cyprus Road, Basingstoke, UK").build();
    }
}
