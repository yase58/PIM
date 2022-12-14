package tr.com.yavuzduran.pim.photo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Photo {

    @Id
    @JsonIgnore
    private UUID id = UUID.randomUUID();
    private String name;
    private String title;
    @JsonIgnore
    private Date createDate;
    private String url;
    private String comment;

    private String username;

    @ManyToOne
    private Album album;

}
