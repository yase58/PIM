package tr.com.yavuzduran.pim.contact.util;

import org.springframework.security.core.context.SecurityContextHolder;
import tr.com.yavuzduran.pim.contact.dto.ContactDto;
import tr.com.yavuzduran.pim.contact.model.Contact;
import tr.com.yavuzduran.pim.exceptionhandler.exception.ContactNullException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContactModelConverter {

    public static Contact convert(ContactDto contactDto) throws ContactNullException {
        if(contactDto == null){
            throw new ContactNullException("Contact is Empty");
        }
        return Contact.builder()
                .company(contactDto.getCompany())
                .name(contactDto.getName())
                .nickname(contactDto.getNickname())
                .surname(contactDto.getSurname())
                .tagPhoneMap(contactDto.getTagPhoneMap())
                .username((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .uuid(UUID.randomUUID())
                .build();
    }

    public static ContactDto convert(Contact contact) throws ContactNullException {
        if(contact == null){
            throw new ContactNullException("Contact Not Found");
        }
        return ContactDto.builder()
                .company(contact.getCompany())
                .name(contact.getName())
                .nickname(contact.getNickname())
                .surname(contact.getSurname())
                .tagPhoneMap(contact.getTagPhoneMap())
                .build();
    }

    public static List<ContactDto> convert(List<Contact> contactList) throws ContactNullException {
        if(contactList == null || contactList.size() == 0){
            throw new ContactNullException("Contact Not Found!");
        }
        List<ContactDto> contactDtoList = new ArrayList<>();
        for (Contact contact : contactList) {
            contactDtoList.add(convert(contact));
        }
        return contactDtoList;
    }


}
