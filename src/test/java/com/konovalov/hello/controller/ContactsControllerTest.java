package com.konovalov.hello.controller;

import com.konovalov.hello.service.ContactsService;
import jdk.nashorn.internal.parser.JSONParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.nio.charset.Charset;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContactsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactsController contactsController;
    @MockBean
    private ContactsService contactsService;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Test
    public void getNameFilter() throws Exception {
        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk());
               // .andExpect((ResultMatcher) content().contentType(contentType));
    }

    @Test
    public void create() throws Exception {
        String name = JSONParser.quote("vala");
        this.mockMvc.perform(post("/contacts").contentType(contentType)
                .content(name))
                .andExpect(status().isCreated());
    }
}