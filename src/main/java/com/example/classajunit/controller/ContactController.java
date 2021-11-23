package com.example.classajunit.controller;

import com.example.classajunit.model.Contact;
import com.example.classajunit.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final IContactService contactService;

    @Autowired
    public ContactController(IContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(contactService.all());
    }

    @GetMapping("/by-mobile-phone/{mobilePhone}")
    public ResponseEntity<?> findByMobilePhone(@PathVariable String mobilePhone) {
        return ResponseEntity.ok(contactService.findByMobilePhone(mobilePhone));
    }

    @GetMapping("/by-work-phone/{workPhone}")
    public ResponseEntity<?> findByWorkPhone(@PathVariable String workPhone) {
        return ResponseEntity.ok(contactService.findByWorkPhone(workPhone));
    }

    @GetMapping("/by-home-phone/{homePhone}")
    public ResponseEntity<?> findByHomePhone(@PathVariable String homePhone) {
        return ResponseEntity.ok(contactService.findByHomePhone(homePhone));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Contact contact) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contactService.create(contact));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Contact contact) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(contactService.update(id, contact));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {

        contactService.remove(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Removed");
    }
}
