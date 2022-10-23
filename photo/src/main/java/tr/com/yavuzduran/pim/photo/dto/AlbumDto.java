package tr.com.yavuzduran.pim.photo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AlbumDto {

    private String name;

    private String description;

    private String username;

    @JsonIgnore
    private Date createDate;

}
