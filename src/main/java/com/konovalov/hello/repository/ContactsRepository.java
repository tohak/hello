package com.konovalov.hello.repository;

import com.konovalov.hello.domain.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactsRepository extends JpaRepository<Contacts, Long> {
    List<Contacts> findByNameMatchesRegex(String pattern);
}
