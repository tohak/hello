package com.konovalov.hello.controller;

import com.konovalov.hello.domain.Contacts;
import com.konovalov.hello.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "{contacts:/^[ A-Za-z0-9_@./#&+-]*$/}")
public class ContactsController {
    private final ContactsService contactsService;

    @Autowired
    public ContactsController(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @GetMapping
    public List<Contacts> getNameFilter(@RequestParam(value = "nameFilter",required = false, defaultValue = "") String nameFilter){
        return contactsService.getByNameFilter(nameFilter);
    }

}
