package com.konovalov.hello.controller;

import com.konovalov.hello.domain.Contacts;
import com.konovalov.hello.exceptions.BadRequestException;
import com.konovalov.hello.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Contacts> getNameFilter(@RequestParam(value = "nameFilter") String nameFilter) {
        if (nameFilter.isEmpty()) {
            throw new BadRequestException();
        }
        return contactsService.getByNameFilter(nameFilter);
    }

    @PostMapping
    public Contacts create(@RequestBody String contact) {
        Contacts contacts = contactsService.createContact(contact);
        if (contacts == null) {
            throw new BadRequestException();
        }
        return contacts;
    }

}
