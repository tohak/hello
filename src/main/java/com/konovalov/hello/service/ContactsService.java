package com.konovalov.hello.service;

import com.konovalov.hello.domain.Contacts;
import com.konovalov.hello.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContactsService {
    private final ContactsRepository contactsRepository;
    private List<Contacts> contactsList;

    @Autowired
    public ContactsService(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public List<Contacts> getAll() {
        if (this.contactsList == null) {
            this.contactsList = contactsRepository.findAll();
        }
        return this.contactsList;
    }

    public List<Contacts> getByNameFilter(String filter) {
        List<Contacts> result;
        if (StringUtils.isEmpty(filter)) {
            result = getAll();
        } else {
            Pattern pattern = Pattern.compile(filter);
            result = getAll()
                    .stream()
                    .parallel()
                    .filter(contact -> pattern.matcher(contact.getName()).find())
                    .collect(Collectors.toList());
        }
        return result;
    }

    public Contacts createContact(String contact) {
        if (!StringUtils.isEmpty(contact)) {
            Contacts contactBD = contactsRepository.findByName(contact);
            if (contactBD == null) {
                Contacts contacts = new Contacts(contact);
                contactsRepository.save(contacts);
               this.contactsList = contactsRepository.findAll();
                return contacts;
            }
        }
        return null;
    }
}
