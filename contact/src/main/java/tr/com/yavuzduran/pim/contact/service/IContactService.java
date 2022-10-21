package tr.com.yavuzduran.pim.contact.service;

import tr.com.yavuzduran.pim.contact.dto.ContactDto;
import tr.com.yavuzduran.pim.exceptionhandler.exception.ContactNullException;

import java.util.List;

public interface IContactService {

    void addNewContact(ContactDto contactDto) throws ContactNullException;

    void updateContact(String name, String surname, ContactDto contactDto);

    void removeContact(String name, String surname);

    List<ContactDto> getContacts() throws ContactNullException;

    ContactDto getContact(String name, String surname) throws ContactNullException;

}
