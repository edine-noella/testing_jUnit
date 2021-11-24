package com.example.classajunit.service;

import com.example.classajunit.model.Contact;

import java.util.List;

public interface IContactService {

    List<Contact> all();

    Contact findByMobilePhone(String mobilePhone);

    List<Contact> findByWorkPhone(String workPhone);

    List<Contact> findByHomePhone(String homePhone);

    Contact findById(Long id);

    Contact create(Contact contact);

    Contact update(Long id, Contact contact);

    void remove(Long id);

    boolean existsById(Long id);
}
