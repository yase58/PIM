package tr.com.yavuzduran.pim.contact.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tr.com.yavuzduran.pim.contact.dto.ContactDto;
import tr.com.yavuzduran.pim.contact.model.Contact;
import tr.com.yavuzduran.pim.contact.repository.ContactRepository;
import tr.com.yavuzduran.pim.contact.util.ContactModelConverter;
import tr.com.yavuzduran.pim.exceptionhandler.exception.ContactNullException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ContactServiceImp implements IContactService {

    private final ContactRepository contactRepository;

    @Override
    public void addNewContact(ContactDto contactDto) throws ContactNullException {
        contactRepository.save(ContactModelConverter.convert(contactDto));
    }

    @Override
    public void updateContact(String name, String surname, ContactDto contactDto) {
        Contact contact = contactRepository.findByNameAndSurnameAndUsername(name, surname, (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        contact.setCompany(contactDto.getCompany());
        contact.setName(contactDto.getName());
        contact.setSurname(contact.getSurname());
        contact.setNickname(contactDto.getNickname());
        contact.setTagPhoneMap(contact.getTagPhoneMap());
        contactRepository.save(contact);
    }

    @Override
    public void removeContact(String name, String surname) {
        Contact contact = contactRepository.findByNameAndSurnameAndUsername(name, surname, (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (contact != null) {
            contactRepository.delete(contact);
        }
    }

    @Override
    public List<ContactDto> getContacts() throws ContactNullException {
        return ContactModelConverter.convert(contactRepository.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }

    @Override
    public ContactDto getContact(String name, String surname) throws ContactNullException {
        return ContactModelConverter.convert(contactRepository.findByNameAndSurnameAndUsername(name, surname, (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }
}
