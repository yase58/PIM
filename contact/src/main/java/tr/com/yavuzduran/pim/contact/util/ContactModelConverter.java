package tr.com.yavuzduran.pim.contact.util;

import org.springframework.security.core.context.SecurityContextHolder;
import tr.com.yavuzduran.pim.contact.dto.ContactDto;
import tr.com.yavuzduran.pim.contact.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContactModelConverter {

    private ContactModelConverter() {

    }

    public static Contact convert(ContactDto contactDto) {
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

    public static ContactDto convert(Contact contact) {
        return ContactDto.builder()
                .company(contact.getCompany())
                .name(contact.getName())
                .nickname(contact.getNickname())
                .surname(contact.getSurname())
                .tagPhoneMap(contact.getTagPhoneMap())
                .build();
    }

    public static List<ContactDto> convert(List<Contact> contactList) {
        List<ContactDto> contactDtoList = new ArrayList<>();
        for (Contact contact : contactList) {
            contactDtoList.add(convert(contact));
        }
        return contactDtoList;
    }

}
