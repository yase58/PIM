package tr.com.yavuzduran.pim.eventscheduler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tr.com.yavuzduran.pim.eventscheduler.dto.EventDto;
import tr.com.yavuzduran.pim.eventscheduler.enumaration.EnReminder;
import tr.com.yavuzduran.pim.eventscheduler.enumaration.EnRepeatPeriod;
import tr.com.yavuzduran.pim.eventscheduler.service.EventSchedulerServiceImp;
import tr.com.yavuzduran.pim.eventscheduler.util.TimeFormatter;
import tr.com.yavuzduran.pim.exceptionhandler.exception.EventParseException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@SpringBootTest
class EventSchedulerApplicationTests {

    @Autowired
    EventSchedulerServiceImp eventSchedulerServiceImp;

    @Test
    void contextLoads() throws InterruptedException, EventParseException {
//        Thread.sleep(10000);
//        EventDto eventDto = EventDto.builder()
//                .comment("comment")
//                .endDate(TimeFormatter.format(Date.from(Instant.now().plus(10, ChronoUnit.SECONDS))))
//                .isWholeDay(false)
//                .reminder(EnReminder.DAY_1)
//                .repeatPeriod(EnRepeatPeriod.DAILY)
//                .startDate(TimeFormatter.format(Date.from(Instant.now().plus(10, ChronoUnit.SECONDS))))
//                .title("title")
//                .build();
//        eventSchedulerServiceImp.createEvent(eventDto);
//
//        Thread.sleep(20000);
//        EventDto eventDto2 = EventDto.builder()
//                .comment("comment2")
//                .endDate(TimeFormatter.format(Date.from(Instant.now().plus(10, ChronoUnit.SECONDS))))
//                .isWholeDay(false)
//                .reminder(EnReminder.DAY_1)
//                .repeatPeriod(EnRepeatPeriod.DAILY)
//                .startDate(TimeFormatter.format(Date.from(Instant.now().plus(10, ChronoUnit.SECONDS))))
//                .title("title2")
//                .build();
//        eventSchedulerServiceImp.createEvent(eventDto2);
    }

}
