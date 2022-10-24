package tr.com.yavuzduran.pim.blog.personal.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table
public class BlogPersonal {

    @Id
    private long id;

    private String username;

    private Date createTime;

    private Date updateTime;

    @Column(unique = true)
    private String title;

    private String summary;

    @Column(columnDefinition = "TEXT")
    private String text;

}
