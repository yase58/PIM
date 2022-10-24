package tr.com.yavuzduran.pim.blog.personal.model;

import lombok.*;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
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
