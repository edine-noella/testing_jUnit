package com.example.classajunit.controller;

import com.example.classajunit.model.Contact;
import com.example.classajunit.service.IContactService;
import com.example.classajunit.utils.Formatter;
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
        return Formatter.ok(contactService.all());
    }

    @GetMapping("/by-mobile-phone/{mobilePhone}")
    public ResponseEntity<?> findByMobilePhone(@PathVariable String mobilePhone) {
        return Formatter.ok(contactService.findByMobilePhone(mobilePhone));
    }

    @GetMapping("/by-work-phone/{workPhone}")
    public ResponseEntity<?> findByWorkPhone(@PathVariable String workPhone) {
        return Formatter.ok(contactService.findByWorkPhone(workPhone));
    }

    @GetMapping("/by-home-phone/{homePhone}")
    public ResponseEntity<?> findByHomePhone(@PathVariable String homePhone) {
        return Formatter.ok(contactService.findByHomePhone(homePhone));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return Formatter.ok(contactService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Contact contact) {
        return Formatter.send(contactService.create(contact), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Contact contact) {
        return Formatter.send(contactService.update(id, contact), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {

        contactService.remove(id);

        return Formatter.send("Removed", HttpStatus.ACCEPTED);
    }
}
