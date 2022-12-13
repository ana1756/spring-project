package com.ukma.springproject.security;

import com.ukma.springproject.SpringProjectApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SpringProjectApplication.class)
@AutoConfigureMockMvc
@Transactional
class SecurityTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    void anonAccessTest() throws Exception {

        mockMvc.perform(get("/profile"))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));

        mockMvc.perform(get("/applications"))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"));

        mockMvc.perform(get("/applications/create"))
                .andExpect(status().is3xxRedirection());
    }

}
