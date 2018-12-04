package com.konovalov.hello.service;

import com.konovalov.hello.domain.Contacts;
import com.konovalov.hello.repository.ContactsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactsServiceTest {
    @Autowired
    private ContactsService contactsService;

    @MockBean
    private ContactsRepository contactsRepository;

    @Test
    public void getByNameFilter() {
        String filter = "^A.*$";
        Mockito.doReturn(Arrays.asList(new Contacts("Anton")))
                .when(contactsService)
                .getAll();
        List<Contacts> contactsList = contactsService.getByNameFilter(filter);
        Assert.assertNotNull(contactsList);
    }

    @Test
    public void createContact() {
        String contactName = "Vala";
        Contacts contacts = contactsService.createContact(contactName);

        Assert.assertNotNull(contacts);
        Assert.assertNotNull(contacts.getName());
        Mockito.verify(contactsRepository, Mockito.times(1)).save(contacts);
    }

    @Test
    public void badCreateContact() {
        String contactName = "Vala";

        Mockito.doReturn(new Contacts())
                .when(contactsRepository)
                .findByName("Vala");

        Contacts contacts = contactsService.createContact(contactName);
        Assert.assertNull(contacts);
        Mockito.verify(contactsRepository, Mockito.times(0)).save(contacts);
    }
}