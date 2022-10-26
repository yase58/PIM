package tr.com.yavuzduran.pim.contact.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FullNameDto {

    private String name;
    private String surname;

}
