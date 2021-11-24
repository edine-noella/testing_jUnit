package com.example.classajunit.repository;

import com.example.classajunit.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Long> {

    Optional<Contact> findByMobilePhone(String mobilePhone);

    List<Contact> findByWorkPhone(String workPhone);

    List<Contact> findByHomePhone(String homePhone);

    boolean existsByEmail(String email);

    boolean existsByMobilePhone(String mobilePhone);
}
