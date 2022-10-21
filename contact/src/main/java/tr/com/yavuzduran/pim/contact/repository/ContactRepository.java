package tr.com.yavuzduran.pim.contact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.yavuzduran.pim.contact.model.Contact;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Contact findByNameAndSurnameAndUsername(String name, String surname, String username);

    List<Contact> findByUsername(String username);
}
