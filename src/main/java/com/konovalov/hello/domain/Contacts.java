package com.konovalov.hello.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "contacts_tbl")
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Length(max = 20)
    @Column(name = "name_contacts", nullable = false)
    private String name;

    public Contacts(@Length(max = 20) String name) {
        this.name = name;
    }
}
