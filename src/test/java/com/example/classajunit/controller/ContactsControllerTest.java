package com.example.classajunit.controller;

import com.example.classajunit.model.Contact;
import com.example.classajunit.service.IContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactController.class)
public class ContactsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IContactService contactService;

    @Test
    public void getAll_test() throws Exception {
        when(contactService.all()).thenReturn(Arrays.asList(new Contact(1L, "Kaisa", "250783384212"), new Contact(2L, "Mugabe", "250")));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/contacts").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void findById_test() throws Exception {
        when(contactService.findById(anyLong())).thenReturn(new Contact(1L, "Kaisa", "250783384212"));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/contacts/1").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void findByMobilePhone_test() throws Exception {
        when(contactService.findByMobilePhone(anyString())).thenReturn(new Contact(1L, "Kaisa", "250783384212"));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/contacts/by-mobile-phone/250783384212").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void findByWorkPhone_test() throws Exception {
        when(contactService.findByWorkPhone(anyString())).thenReturn(Arrays.asList(new Contact(1L, "Kaisa", "250783384212"), new Contact(2L, "Mugabe", "250")));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/contacts/by-work-phone/250783384212").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void findByHomePhone_test() throws Exception {
        when(contactService.findByHomePhone(anyString())).thenReturn(Arrays.asList(new Contact(1L, "Kaisa", "250783384212"), new Contact(2L, "Mugabe", "250")));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/contacts/by-home-phone/250783384212").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }


    @Test
    public void create_test() throws Exception {
        // TODO: implement me
    }

//    @PostMapping("")
//    public ResponseEntity<?> create(@RequestBody Contact contact) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(contactService.create(contact));
//    }

    @Test
    public void update_test() throws Exception {

        when(contactService.update(anyLong(), any(Contact.class))).thenReturn(new Contact(1L, "Kaisa", "250783384212"), new Contact(2L, "Mugabe", "250"));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/api/contacts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"firstName\": \"irumva\",\"lastName\": \"anselme\",\"email\": \"andesanselme@gmail.com\",\"address\": \"Kirehe - Rwanda\",\"workPhone\": \"07888888888\",\"homePhone\": \"07832433243\",\"mobilePhone\": \"0783384212\",\"twitterProfileUrl\": \"https://app.simplenote.com/p/p2RKTD\",\"facebookProfileUrl\": \"https://app.simplenote.com/p/p2RKTD\",\"linkedInProfileUrl\": \"https://app.simplenote.com/p/p2RKTD\"}");

        mockMvc.perform(request).andExpect(status().isAccepted()).andExpect(content().json(" {\"id\":1,\"firstName\":\"Kaisa\",\"lastName\":null,\"email\":null,\"address\":null,\"workPhone\":null,\"homePhone\":null,\"mobilePhone\":\"250783384212\",\"twitterProfileUrl\":null,\"facebookProfileUrl\":null,\"linkedInProfileUrl\":null}")).andReturn();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Contact contact) {
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(contactService.update(id, contact));
//    }

    @Test
    public void remote_test() throws Exception {
        // TODO: implement me
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> remove(@PathVariable Long id) {
//
//        contactService.remove(id);
//
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Removed");
//    }
}
