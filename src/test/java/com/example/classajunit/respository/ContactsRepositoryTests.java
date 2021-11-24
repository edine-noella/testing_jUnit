package com.example.classajunit.respository;

import com.example.classajunit.model.Contact;
import com.example.classajunit.repository.IContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class ContactsRepositoryTests {

    @Autowired
    private IContactRepository contactRepository;

    @Test
    public void findAll_test() {
        List<Contact> contacts = contactRepository.findAll();

        assertEquals(contacts.size(), 14);
    }

    @Test
    public void findById_test() {
        Optional<Contact> contact = contactRepository.findById(109L);

        if (!contact.isPresent())
            fail("User with this id is not found");

        assertEquals(contact.get().getMobilePhone(), "0718888881");
    }

    @Test
    public void findByMobilePhone_test() {
        Optional<Contact> contact = contactRepository.findByMobilePhone("0718888881");

        if (!contact.isPresent())
            fail("User with this mobile phone number is not found");

        assertEquals(contact.get().getId(), Long.valueOf(109));
    }

    @Test
    public void findByWorkPhone_test() {
        List<Contact> contacts = contactRepository.findByWorkPhone("0783384212");

        assertEquals(contacts.size(), 3);
    }

    @Test
    public void findByHomePhone_test() {
        List<Contact> contacts = contactRepository.findByHomePhone(null);

        assertEquals(contacts.size(), 14);
    }

    @Test
    public void remove_test(){
        contactRepository.deleteById(109L);

        List<Contact> contacts = contactRepository.findAll();

        // it would be 14 but I removed 1 now remaining 13

        assertEquals(contacts.size(), 13);
    }

    @Test
    public void existsByMobilePhone_test(){
        boolean res = contactRepository.existsByMobilePhone("0788888891");

        assertTrue(res);
    }

    @Test
    public void existsByMobilePhone_test_notThere(){
        boolean res = contactRepository.existsByMobilePhone("0781111891");

        assertFalse(res);
    }
}
