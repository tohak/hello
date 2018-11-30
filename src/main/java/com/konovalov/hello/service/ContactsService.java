package com.konovalov.hello.service;

import com.konovalov.hello.domain.Contacts;
import com.konovalov.hello.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ContactsService {
    private final ContactsRepository contactsRepository;

    @Autowired
    public ContactsService(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public List<Contacts> getAll() {
        return contactsRepository.findAll();
    }

    public List<Contacts> getByNameFilter(String filter) {
        List<Contacts> result;
        if (StringUtils.isEmpty(filter)) {
            result = contactsRepository.findAll();
        } else {

            result = contactsRepository.findAll()
                    .stream()
                    .parallel()
                    .filter(contacts -> contacts.getName().matches(filter))
                    .collect(Collectors.toList());
        }
        return result;
    }
}
