package tr.com.yavuzduran.pim.eventscheduler.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TitleAndDate {

    private String title;
    private String date;
}
