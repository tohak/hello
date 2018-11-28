package com.konovalov.hello.controller;

import com.konovalov.hello.domain.Contacts;
import com.konovalov.hello.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("contacts")
public class ContactsController {
    private final ContactsRepository contactsRepository;

    @Autowired
    public ContactsController(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    @GetMapping
    public List<Contacts> list(){
        return contactsRepository.findAll();
    }

}
