package com.gmail.aperavoznikau.demo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.aperavoznikau.demo.controller.model.ResponseResult;
import com.gmail.aperavoznikau.demo.service.UserService;
import com.gmail.aperavoznikau.demo.service.model.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    void shouldReturn200WhenWeGetUsers() throws Exception {
        mockMvc.perform(get("/api/users")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn400WhenWeGetUsersWithXmlContentType() throws Exception {
        mockMvc.perform(get("/api/users")
                        .contentType("application/xml"))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void shouldReturns200whenValidInput() throws Exception {
        UserDTO user = new UserDTO();
        user.setName("Test");

        when(userService.isUniqueUsername("Test")).thenReturn(true);

        mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturns200whenInValidInput() throws Exception {
        UserDTO user = new UserDTO();
        user.setName("Test");

        when(userService.isUniqueUsername("Test")).thenReturn(false);

        mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturns400whenInvalidInput() throws Exception {
        UserDTO user = new UserDTO();
        user.setName("Tes");

        when(userService.isUniqueUsername("Tes")).thenReturn(false);

        mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturns400whenInvalidNameInput() throws Exception {
        UserDTO user = new UserDTO();

        when(userService.isUniqueUsername(null)).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        Map<String, Object> map = objectMapper.readValue(actualResponseBody, Map.class);
        List<String> errors = (List<String>) map.get("errors");
        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("Please provide a name", errors.get(0));
    }

    @Test
    void shouldMapsToBusinessModelWhenValidInput() throws Exception {
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setName("Test");

        when(userService.getUser(1L)).thenReturn(user);

        MvcResult mvcResult = mockMvc.perform(get("/api/users/{id}", 1L)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();
        verify(userService, times(1)).getUser(1L);
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        UserDTO result = objectMapper.readValue(actualResponseBody, UserDTO.class);

        Assertions.assertEquals("Test", result.getName());
    }

    @Test
    void shouldReturnUserWhenValidInput() throws Exception {
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setName("Test");

        when(userService.getUser(1L)).thenReturn(user);

        MvcResult mvcResult = mockMvc.perform(get("/api/users/{id}", 1L)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(user));
    }
}