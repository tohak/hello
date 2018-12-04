package com.konovalov.hello.service;

import com.konovalov.hello.domain.Contacts;
import com.konovalov.hello.repository.ContactsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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
        Mockito.doReturn(Arrays.asList(new Contacts("Anton"), new Contacts("Vlad")))
                .when(contactsRepository)
                .findAll();

        List<Contacts> result = contactsService.getByNameFilter(filter);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("Anton", result.get(0).getName());
    }

    @Test
    public void createContact() {
        String contactName = "Vala";
        Mockito.doReturn(Arrays.asList(new Contacts("Anton"), new Contacts("Vlad")))
                .when(contactsRepository)
                .findAll();
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
        Mockito.doReturn(new Contacts())
                .when(contactsRepository)
                .findByName("Vala");

        Contacts contacts = contactsService.createContact(contactName);
        Assert.assertNull(contacts);
        Mockito.verify(contactsRepository, Mockito.times(0)).save(contacts);
    }
}