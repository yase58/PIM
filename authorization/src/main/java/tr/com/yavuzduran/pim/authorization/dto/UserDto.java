package tr.com.yavuzduran.pim.authorization.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String name;
    private String surname;
    private String username;
    @JsonIgnore
    private String password;

    private Set<RoleDto> roles;
}
