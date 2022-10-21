package tr.com.yavuzduran.pim.contact.model;

import lombok.*;
import tr.com.yavuzduran.pim.contact.enumaration.EnTag;

import javax.persistence.*;
import java.util.Map;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Contact {

    @Id
    private UUID uuid = UUID.randomUUID();

    private String nickname;

    private String name;

    private String surname;

    private String company;

    private String username;

    @ElementCollection
    @MapKeyColumn(name = "Tag")
    private Map<EnTag, String> tagPhoneMap;

}
