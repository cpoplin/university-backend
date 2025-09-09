package com.colepoplin.universitybackend.integrationtests;


import com.colepoplin.universitybackend.repositories.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testThatGetAllStudentsReturnsHttp201OKAndIsExpectedSize() throws Exception {
        //should return 201 OK even if the db is empty, should return a list of 1 bc that is the current length of the dummy data
        //in data.sql
        mockMvc.perform(
                MockMvcRequestBuilders.get("/students")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1))
        );
    }


    @Test
    public void testThatGetStudentByIdReturnsHttp201OkAndProperObject() throws Exception{
        //testing for basic functionality, and proper return of the dummy data
        mockMvc.perform(
                MockMvcRequestBuilders.get("/students/1")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.studentId").value(1)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Smith")
        );

    }

    @Test
    public void testThatGetStudentByIdFailsProperly404AndEmptyBody() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/students/10000")
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.studentId").doesNotExist()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").doesNotExist()
        );
    }
}
