package tr.com.yavuzduran.pim.contact.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.yavuzduran.pim.common.controller.ICrudController;
import tr.com.yavuzduran.pim.contact.dto.ContactDto;
import tr.com.yavuzduran.pim.contact.dto.FullNameDto;
import tr.com.yavuzduran.pim.contact.service.ContactServiceImp;
import tr.com.yavuzduran.pim.exceptionhandler.exception.contact.ContactAlreadyExistException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.contact.ContactIdentifierNullException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.contact.ContactNameNullException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.contact.ContactNotFoundException;
import tr.com.yavuzduran.pim.exceptionhandler.response.Response;
import tr.com.yavuzduran.pim.exceptionhandler.response.ResponseBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContactController extends ICrudController<ContactDto, FullNameDto> {

    private final ContactServiceImp contactService;

    @Override
    public ResponseEntity<Response> save(ContactDto contactDto) throws ContactIdentifierNullException, ContactAlreadyExistException {
        contactService.save(contactDto);
        return ResponseBuilder.createSuccess(HttpStatus.NO_CONTENT);
    }

    @Override
    @PutMapping("/{name}/{surname}")
    public ResponseEntity<Response> update(FullNameDto fullNameDto, ContactDto contactDto) throws ContactNameNullException, ContactIdentifierNullException, ContactNotFoundException, ContactAlreadyExistException {
        contactService.update(fullNameDto, contactDto);
        return ResponseBuilder.createSuccess();
    }

    @Override
    @DeleteMapping("/{name}/{surname}")
    public ResponseEntity<Response> remove(FullNameDto fullNameDto) throws ContactNameNullException, ContactNotFoundException {
        contactService.remove(fullNameDto);
        return ResponseBuilder.createSuccess();
    }

    @Override
    public ResponseEntity<List<ContactDto>> getAllData() {
        return ResponseEntity.ok(contactService.getAllData());
    }

    @Override
    @GetMapping("/{name}/{surname}")
    public ResponseEntity<ContactDto> getData(FullNameDto fullNameDto) throws ContactNameNullException, ContactNotFoundException {
        return ResponseEntity.ok(contactService.getData(fullNameDto));
    }

}
