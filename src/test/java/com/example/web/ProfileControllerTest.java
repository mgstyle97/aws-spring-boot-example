package com.example.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfileControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void call_profile_request_without_authentication() {
        // given
        final String expected = "default";

        // when
        ResponseEntity<String> response = restTemplate.getForEntity("/profile", String.class);

        // then
        assertAll(
            () -> assertEquals(response.getStatusCode(), HttpStatus.OK),
            () -> assertEquals(response.getBody(), expected)
        );
    }

}
