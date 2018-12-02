package com.konovalov.hello.repository;

import com.konovalov.hello.domain.Contacts;
import com.konovalov.hello.repository.common.BaseRepository;

public interface ContactsRepository extends BaseRepository<Contacts, Long> {

    Contacts findByName (String name);
}
