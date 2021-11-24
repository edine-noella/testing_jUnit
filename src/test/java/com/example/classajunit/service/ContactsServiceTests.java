package com.example.classajunit.service;

import com.example.classajunit.model.Contact;
import com.example.classajunit.repository.IContactRepository;
import com.example.classajunit.service.impl.ContactServiceImpl;
import com.example.classajunit.utils.CustomException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void findByMobilePhone_test_404() {
        when(contactRepository.findByMobilePhone(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomException.class, () -> contactService.findByMobilePhone("07832435"));

        assertEquals(exception.getMessage(), "Contact with this mobile phone not found");
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
    public void findById_test_404() {
        when(contactRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomException.class, () -> contactService.findById(1L));

        assertEquals("Contact with this id not found", exception.getMessage());
    }

    @Test
    public void create_test() {
        when(contactRepository.save(any(Contact.class))).thenReturn(new Contact(1L, "Kaisa", "+250783384212"));

        assertEquals("Kaisa", contactService.create(new Contact()).getFirstName());
    }

    @Test
    public void create_test_duplicateMobilePhone() {
        when(contactRepository.existsByMobilePhone(anyString())).thenReturn(true);

        Exception exception = assertThrows(CustomException.class, () -> contactService.create(new Contact(32L, "Kaisa", "0783384212")));

        assertEquals("Mobile phone already taken", exception.getMessage());
    }

    @Test
    public void update_test() {
        when(contactRepository.save(any(Contact.class))).thenReturn(new Contact(1L, "Kaisa", "25078888881"));
        when(contactRepository.findById(anyLong())).thenReturn(Optional.of(new Contact(1L, "Kaisa", "25078888880")));
        when(contactRepository.existsByMobilePhone(anyString())).thenReturn(false);

        Contact updated = contactService.update(1L, new Contact(2L, "Mugabo", "25078888881"));

        assertEquals("Kaisa", updated.getFirstName());
    }

    @Test
    public void update_test_idNotFound() {
        when(contactRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomException.class, () -> contactService.update(1L, new Contact(2L, "Mugabo", "25078888881")));

        assertEquals("Contact with this id is not found", exception.getMessage());
    }

    @Test
    public void update_test_nameAlreadyTaken() {
        when(contactRepository.findById(anyLong())).thenReturn(Optional.of(new Contact(1L, "Kaisa", "+25078888880")));
        when(contactRepository.existsByMobilePhone(anyString())).thenReturn(true);

        Exception exception = assertThrows(CustomException.class, () -> contactService.update(1L, new Contact(2L, "Mugabo", "+25078888881")));

        assertEquals("Mobile Phone already registered", exception.getMessage());
    }
}
