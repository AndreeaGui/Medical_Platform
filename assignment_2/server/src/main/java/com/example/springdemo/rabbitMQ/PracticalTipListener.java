package com.example.springdemo.rabbitMQ;

import com.example.springdemo.SpringDemoApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PracticalTipListener {

    private final ActivityRepository activityRepository;
    private final ApplicationEventPublisher eventPublisher;

    @RabbitListener(queues = SpringDemoApplication.DEFAULT_PARSING_QUEUE)
    public void consumeDefaultMessage(final ActivityMessage message) {
        Activity activity = convertMessageIntoActivity(message);
        System.out.println("Received and persisted: "+activity.toString());

    }

    private Activity convertMessageIntoActivity(ActivityMessage activityMessage){
        Activity activity = new Activity();

        activity.setActivity(activityMessage.getActivity());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Long sM = Timestamp.valueOf(LocalDateTime.parse(activityMessage.getStartTime(), dateTimeFormatter)).getTime();
        activity.setStarTime(sM);

        Long eM = Timestamp.valueOf(LocalDateTime.parse(activityMessage.getEndTime(), dateTimeFormatter)).getTime();
        activity.setEndTime(eM);

        activity.setPersonId(UUID.randomUUID());

        Activity a = activityRepository.save(activity);

        System.out.println("There are problems: "+ problemsWereFound(a));
        if(problemsWereFound(a)){
            eventPublisher.publishEvent(new BaseEvent(a));
        }

        return a;
    }

    private boolean problemsWereFound(Activity activity){
        Long start = activity.getStarTime();
        Long end = activity.getEndTime();
        String name = activity.getActivity();
        if(name.contains("Sleeping") && (end-start>12*3600000L)){
            return true;
        }
        if((name.contains("Toileting") || name.contains("Showering")) && (end-start>3600000L)){
            return true;
        }
        if(name.contains("Leaving") && (end-start>12*3600000L)){
            return true;
        }
        return false;
    }
}
