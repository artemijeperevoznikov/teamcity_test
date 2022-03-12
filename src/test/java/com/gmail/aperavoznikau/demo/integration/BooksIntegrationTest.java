package com.gmail.aperavoznikau.demo.integration;

import com.gmail.aperavoznikau.demo.controller.security.util.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.Assert.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BooksIntegrationTest extends BaseIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Sql("/sql/users.sql")
    void contextLoads() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<Object> response = restTemplate.exchange(
                createURLWithPort("/api/books"),
                HttpMethod.GET,
                entity,
                Object.class
        );
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}