package com.example.classajunit.service.impl;

import com.example.classajunit.model.Contact;
import com.example.classajunit.repository.IContactRepository;
import com.example.classajunit.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements IContactService {

    private final IContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(IContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    @Override
    public List<Contact> all() {
        return contactRepository.findAll();
    }

    @Override
    public Contact findByMobilePhone(String mobilePhone) {
        return contactRepository.findByMobilePhone(mobilePhone)
                .orElse(null);
    }

    @Override
    public List<Contact> findByWorkPhone(String workPhone) {
        return contactRepository.findByWorkPhone(workPhone);
    }

    @Override
    public List<Contact> findByHomePhone(String homePhone) {
        return contactRepository.findByHomePhone(homePhone);
    }

    @Override
    public Contact findById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public Contact create(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact update(Long id, Contact contact) {
        if (!contactRepository.existsById(id))
            throw new RuntimeException("Contact with this id is not found");

        contact.setId(id);

        return contactRepository.save(contact);
    }

    @Override
    public void remove(Long id) {
        if (!existsById(id))
            throw new RuntimeException("Contact with this id is not found");

        contactRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return contactRepository.existsById(id);
    }
}
