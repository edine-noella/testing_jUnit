package com.example.classajunit.service.impl;

import com.example.classajunit.repository.IContactRepository;
import com.example.classajunit.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements IContactService {

    private final IContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(IContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


}
