package tr.com.yavuzduran.pim.contact.dto;

import lombok.*;
import tr.com.yavuzduran.pim.contact.enumaration.EnTag;

import javax.persistence.ElementCollection;
import javax.persistence.MapKeyColumn;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ContactDto {

    private String nickname;

    private String name;

    private String surname;

    private String company;

    @ElementCollection
    @MapKeyColumn(name = "Tag")
    private Map<EnTag, String> tagPhoneMap;

}
