package com.ukma.springproject.security;

import com.ukma.springproject.SpringProjectApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SpringProjectApplication.class)
@AutoConfigureMockMvc
public class SecurityTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void adminsAccessTest() throws Exception {

        mockMvc.perform(get("/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"));

        mockMvc.perform(get("/applications"))
                .andExpect(status().isOk())
                .andExpect(view().name("applications"));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"));

        mockMvc.perform(get("/applications/create"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void usersAccessTest() throws Exception {

        mockMvc.perform(get("/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"));

        mockMvc.perform(get("/applications"))
                .andExpect(status().is4xxClientError());

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"));

        mockMvc.perform(get("/applications/create"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "user", roles = {"DEV"})
    public void devsAccessTest() throws Exception {

        mockMvc.perform(get("/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"));

        mockMvc.perform(get("/applications"))
                .andExpect(status().is4xxClientError());

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"));

        mockMvc.perform(get("/applications/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("application"));
    }

    @Test
    @WithAnonymousUser
    public void anonAccessTest() throws Exception {

        mockMvc.perform(get("/profile"))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/applications"))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"));

        mockMvc.perform(get("/applications/create"))
                .andExpect(status().is3xxRedirection());
    }

}
