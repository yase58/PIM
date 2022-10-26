package tr.com.yavuzduran.pim.contact.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tr.com.yavuzduran.pim.common.service.ICrudService;
import tr.com.yavuzduran.pim.contact.dto.ContactDto;
import tr.com.yavuzduran.pim.contact.dto.FullNameDto;
import tr.com.yavuzduran.pim.contact.model.Contact;
import tr.com.yavuzduran.pim.contact.repository.ContactRepository;
import tr.com.yavuzduran.pim.contact.util.ContactModelConverter;
import tr.com.yavuzduran.pim.exceptionhandler.exception.contact.ContactAlreadyExistException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.contact.ContactIdentifierNullException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.contact.ContactNameNullException;
import tr.com.yavuzduran.pim.exceptionhandler.exception.contact.ContactNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ContactServiceImp implements ICrudService<ContactDto, FullNameDto> {

    private final ContactRepository contactRepository;

    @Override
    public void save(ContactDto contactDto) throws ContactIdentifierNullException, ContactAlreadyExistException {
        dtoCheck(contactDto);
        contactRepository.save(ContactModelConverter.convert(contactDto));
    }

    @Override
    public void update(FullNameDto fullNameDto, ContactDto contactDto) throws ContactIdentifierNullException, ContactAlreadyExistException, ContactNameNullException, ContactNotFoundException {
        dtoCheck(contactDto);
        Contact contact = uniqueKeyCheck(fullNameDto);
        contact.setCompany(contactDto.getCompany());
        contact.setName(contactDto.getName());
        contact.setSurname(contact.getSurname());
        contact.setNickname(contactDto.getNickname());
        contact.setTagPhoneMap(contact.getTagPhoneMap());
        contactRepository.save(contact);
    }

    @Override
    public void remove(FullNameDto fullNameDto) throws ContactNameNullException, ContactNotFoundException {
        Contact contact = uniqueKeyCheck(fullNameDto);
        contactRepository.delete(contact);
    }

    @Override
    public List<ContactDto> getAllData() {
        return ContactModelConverter.convert(contactRepository.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }

    @Override
    public ContactDto getData(FullNameDto fullNameDto) throws ContactNameNullException, ContactNotFoundException {
        Contact contact = uniqueKeyCheck(fullNameDto);
        return ContactModelConverter.convert(contact);
    }

    private void dtoCheck(ContactDto contactDto) throws ContactIdentifierNullException, ContactAlreadyExistException {
        if (contactDto == null || contactDto.getName() == null || contactDto.getSurname() == null) {
            throw new ContactIdentifierNullException("Name or surname does not be null!");
        }
        Contact contact = contactRepository.findByNameAndSurnameAndUsername(
                contactDto.getName(), contactDto.getSurname(), (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (contact != null) {
            throw new ContactAlreadyExistException("Contact Already Exist! Full Name : " + contact.getName() + " " + contact.getSurname());
        }
    }


    private Contact uniqueKeyCheck(FullNameDto fullNameDto) throws ContactNameNullException, ContactNotFoundException {
        if (fullNameDto == null || fullNameDto.getName() == null || fullNameDto.getSurname() == null) {
            throw new ContactNameNullException("Name or surname does not be null!");
        }
        Contact contact = contactRepository.findByNameAndSurnameAndUsername(
                fullNameDto.getName(), fullNameDto.getSurname(), (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (contact == null) {
            throw new ContactNotFoundException("Contact Not Found! Fullname : " + fullNameDto.getName() + " " + fullNameDto.getSurname());
        }
        return contact;
    }
}
