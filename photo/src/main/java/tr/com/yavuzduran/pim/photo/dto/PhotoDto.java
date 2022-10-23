package tr.com.yavuzduran.pim.photo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.yavuzduran.pim.photo.entity.Album;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PhotoDto {

    private String name;
    private String title;
    @JsonIgnore
    private Date createDate;
    private String url;
    private String comment;

    private String username;

    private Album album;

}
