package tr.com.yavuzduran.pim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactApplication.class, args);
	}


//	@Bean
//	CommandLineRunner commandLineRunner(ContactRepository contactRepository){
//		return args -> {
//			Contact contact = Contact.builder()
//					.company("company")
//					.name("name")
//					.nickname("nickname")
//					.surname("surname")
//					.tagPhoneMap(Map.of(EnTag.HOME, "555555",EnTag.MOBILE, "5439288958"))
//					.uuid(UUID.randomUUID())
//					.build();
//
//			contactRepository.save(contact);
//		};
//	}
}
