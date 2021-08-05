package com.example.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void return_hello_string() throws Exception {
        String hello = "hello";

        mvc
            .perform(MockMvcRequestBuilders.get("/hello"))
            .andExpect(status().isOk())
            .andExpect(content().string(hello));
    }

    @Test
    void return_hello_dto_object() throws Exception {
        String name = "hello";
        int amount = 1_000;

        mvc.perform(
                MockMvcRequestBuilders
                        .get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.amount").value(amount));
    }

}