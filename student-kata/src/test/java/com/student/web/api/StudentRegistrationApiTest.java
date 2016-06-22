package com.student.web.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.AbstractStudentTest;
import com.student.model.Student;

public class StudentRegistrationApiTest extends AbstractStudentTest {

    private static final String FAILURE_UNEXPECTED_RESPONSE_CODE_RECEIVED = "failure - Unexpected response code received!";
    @Autowired
    private StudentRegistrationController studentRegistrationController;

    @Test
    public void registerAStudent() throws Exception {
        MockMvc mockMvc = getStandaloneMockMvc();

        ObjectMapper mapper = new ObjectMapper();
        String jsonValueOfStudent = mapper.writeValueAsString(student);

        MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/student/single-registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(jsonValueOfStudent))
                .andReturn();

        int status = response.getResponse().getStatus();
        assertEquals(FAILURE_UNEXPECTED_RESPONSE_CODE_RECEIVED, 201, status);
        assertTrue("Returned invalid content", response.getResponse().getContentAsString().length() == 0);
    }

    @Test
    public void registerMultipleStudents() throws Exception {
        MockMvc mockMvc = getStandaloneMockMvc();

        List<Student> students = new ArrayList<>();
        students.add(studentBuilder.id(3001L).name("Student1").address("60, Cyprus road, Basingstoke, UK").build());
        students.add(studentBuilder.id(3002L).name("Student1").address("61, Cyprus road, Basingstoke, UK").build());
        ObjectMapper mapper = new ObjectMapper();
        String multipleStudentsJsonString = mapper.writeValueAsString(students);

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/api/student/multiple-registration").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(multipleStudentsJsonString)).andReturn();

        assertEquals(FAILURE_UNEXPECTED_RESPONSE_CODE_RECEIVED, 201, response.getResponse().getStatus());
        assertTrue("failure - unexpected content received", response.getResponse().getContentAsString().length() == 0);

        Collection<Student> foundStudents = studentFinderService.listStudents();
        assertEquals("failure - Not all students saved", 2, foundStudents.size());
    }

    @Test
    public void deregisterAStudent() throws Exception {
        studentRegistrationService.registerStudent(student);
        MockMvc mockMvc = getStandaloneMockMvc();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/student/single-deregister/1001").accept(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(FAILURE_UNEXPECTED_RESPONSE_CODE_RECEIVED, 201, response.getResponse().getStatus());

        assertTrue("failure - Error deleting students", studentFinderService.listStudents().size() == 0);
    }

    private HandlerExceptionResolver exceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

        Properties exceptionMappings = new Properties();

        exceptionMappings.put("net.petrikainulainen.spring.testmvc.todo.exception.TodoNotFoundException", "error/404");
        exceptionMappings.put("java.lang.Exception", "error/error");
        exceptionMappings.put("java.lang.RuntimeException", "error/error");

        exceptionResolver.setExceptionMappings(exceptionMappings);

        Properties statusCodes = new Properties();

        statusCodes.put("error/404", "404");
        statusCodes.put("error/error", "500");

        exceptionResolver.setStatusCodes(statusCodes);

        return exceptionResolver;
    }

    // For later use
    // private MessageSource messageSource() {
    // ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    //
    // messageSource.setBasename("i18n/messages");
    // messageSource.setUseCodeAsDefaultMessage(true);
    //
    // return messageSource;
    // }

    private LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    private ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    private MockMvc getStandaloneMockMvc() {
        StandaloneMockMvcBuilder standaloneSetup = MockMvcBuilders.standaloneSetup(studentRegistrationController);
        standaloneSetup.setHandlerExceptionResolvers(exceptionResolver());
        standaloneSetup.setValidator(validator());
        standaloneSetup.setViewResolvers(viewResolver());
        return standaloneSetup.build();
    }
}
