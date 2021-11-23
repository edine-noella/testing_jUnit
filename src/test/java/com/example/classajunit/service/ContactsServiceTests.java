package com.example.classajunit.service;

import com.example.classajunit.model.Contact;
import com.example.classajunit.repository.IContactRepository;
import com.example.classajunit.service.impl.ContactServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactsServiceTests {

    @Mock
    private IContactRepository contactRepository;

    @InjectMocks
    private ContactServiceImpl contactService;

    @Test
    public void all_test() {
        when(contactRepository.findAll()).thenReturn(Arrays.asList(new Contact(1L, "Kaisa", "+250783384212"), new Contact(2L, "Mugabe", "+250")));

        assertEquals("Mugabe", contactService.all().get(1).getFirstName());
    }

    @Test
    public void findByMobilePhone_test() {
        when(contactRepository.findByMobilePhone(anyString())).thenReturn(Optional.of(new Contact(1L, "Kaisa", "+250783384212")));

        assertEquals("Kaisa", contactService.findByMobilePhone("+250783384212").getFirstName());
    }

    @Test
    public void findByWorkPhone_test() {
        when(contactRepository.findByWorkPhone(anyString())).thenReturn(Arrays.asList(new Contact(1L, "Kaisa", "+250783384212"), new Contact(2L, "Mugabe", "+250")));

        assertEquals("Mugabe", contactService.findByWorkPhone(null).get(1).getFirstName());
    }

    @Test
    public void findByHomePhone_test() {
        when(contactRepository.findByHomePhone(anyString())).thenReturn(Arrays.asList(new Contact(1L, "Kaisa", "+250783384212"), new Contact(2L, "Mugabe", "+250")));

        assertEquals("Mugabe", contactService.findByHomePhone(null).get(1).getFirstName());
    }

    @Test
    public void findById_test() {
        when(contactRepository.findById(anyLong())).thenReturn(Optional.of(new Contact(1L, "Kaisa", "+250783384212")));

        assertEquals("Kaisa", contactService.findById(1L).getFirstName());
    }

    @Test
    public void create_test() {
        when(contactRepository.save(any(Contact.class))).thenReturn(new Contact(1L, "Kaisa", "+250783384212"));

        assertEquals("Kaisa", contactService.create(new Contact()).getFirstName());
    }

    @Test
    public void update_test() {
        when(contactRepository.save(any(Contact.class))).thenReturn(new Contact(1L, "Kaisa", "+250783384212"));
        when(contactRepository.findById(anyLong())).thenReturn(Optional.of(new Contact(1L, "Kaisa", "+250783384212")));

        Contact updated = contactService.update(1L, new Contact());

        assertEquals("Kaisa", updated.getFirstName());
    }
}
