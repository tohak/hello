package com.konovalov.hello.controller;

import com.konovalov.hello.domain.Contacts;
import com.konovalov.hello.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("contacts")
public class ContactsController {
    private final ContactsService contactsService;

    @Autowired
    public ContactsController(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @GetMapping
    public List<Contacts> list() {
        return contactsService.getAll();
    }

}
