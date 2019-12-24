package com.dmarco.luckymoney.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LuckymoneyControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void listLuckymoney() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/list"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}