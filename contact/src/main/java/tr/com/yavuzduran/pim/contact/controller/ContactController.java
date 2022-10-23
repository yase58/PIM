package tr.com.yavuzduran.pim.contact.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.yavuzduran.pim.contact.dto.ContactDto;
import tr.com.yavuzduran.pim.contact.service.IContactService;
import tr.com.yavuzduran.pim.exceptionhandler.exception.ContactNullException;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final IContactService contactService;

    @PostMapping
    public ResponseEntity<Response> addContact(@RequestBody ContactDto contactDto) throws ContactNullException {
        contactService.addNewContact(contactDto);
        return ResponseBuilder.createSuccess();
    }

    @PatchMapping
    public ResponseEntity<Response> updateContact(@RequestParam String name, @RequestParam String surname, @RequestBody ContactDto contactDto) {
        contactService.updateContact(name, surname, contactDto);
        return ResponseBuilder.createSuccess();
    }

    @DeleteMapping
    public ResponseEntity<Response> removeContact(@RequestParam String name,@RequestParam  String surname) {
        contactService.removeContact(name, surname);
        return ResponseBuilder.createSuccess();
    }

    @GetMapping("/allContact")
    public ResponseEntity<List<ContactDto>> getContacts() throws ContactNullException {
        return ResponseEntity.ok(contactService.getContacts());
    }

    @GetMapping
    public ResponseEntity<ContactDto> getContacts(@RequestParam String name, @RequestParam String surname) throws ContactNullException {
        return ResponseEntity.ok(contactService.getContact(name, surname));
    }

}
