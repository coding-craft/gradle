package com.student.web.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.student.AbstractStudentTest;
import com.student.model.Student;

@WebAppConfiguration
public class StudentFinderApiTest extends AbstractStudentTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void createStudentRecord() {
        Student student = studentBuilder.id(2001L).name("Mathew").address("51, Cyprus road, Basingstoke, UK").build();
        studentRegistrationService.registerStudent(student);
    }

    @Test
    public void findAllStudents() throws Exception {
        MockMvc mockMvc = getWebAppContextMockMvc(webApplicationContext);

        MvcResult requestResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/students").accept(MediaType.APPLICATION_JSON)).andReturn();

        MockHttpServletResponse response = requestResult.getResponse();
        int status = response.getStatus();

        assertEquals("Not a successful response", 200, status);
        assertTrue("failure - Unexpecte response body content", response.getContentAsString().length() > 0);
    }

    @Test
    public void findOneStudent() throws Exception {
        MockMvc mvc = getWebAppContextMockMvc(webApplicationContext);

        // Set the 'Accept' header to the given request
        MockHttpServletRequestBuilder accept = MockMvcRequestBuilders.get("/api/student/2001").accept(MediaType.APPLICATION_JSON);
        MvcResult response = mvc.perform(accept).andReturn();

        int status = response.getResponse().getStatus();

        assertEquals("Unexpected response received", 200, status);
        assertTrue("Student data not found!", response.getResponse().getContentAsString().length() > 0);
    }

    private MockMvc getWebAppContextMockMvc(WebApplicationContext webApplicationContext) {
        return MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    // Test with standalone later.
}
