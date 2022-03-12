package com.gmail.aperavoznikau.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.aperavoznikau.demo.controller.security.point.AuthEntryPointJwt;
import com.gmail.aperavoznikau.demo.controller.security.util.JwtUtils;
import com.gmail.aperavoznikau.demo.service.BookService;
import com.gmail.aperavoznikau.demo.service.model.BookDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthEntryPointJwt unauthorizedHandler;

    @MockBean
    private JwtUtils jwtUtils;
    @MockBean
    private BookService bookService;

    @Test
    void shouldReturn200WhenWeGetBooks() throws Exception {
        mockMvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn415WhenWeGetBooks() throws Exception {
        mockMvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_ATOM_XML_VALUE))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void shouldReturn200WhenWeGetBooksFromBusinessLogic() throws Exception {

        mockMvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(bookService, times(1)).findAll();
    }

    @Test
    void shouldReturnBookWhenWeGetBooks() throws Exception {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setName("Test Book");

        when(bookService.findAll()).thenReturn(Collections.singletonList(bookDTO));

        MvcResult mvcResult = mockMvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(Collections.singletonList(bookDTO))
        );
    }

}