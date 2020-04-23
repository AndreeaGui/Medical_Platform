package com.rabbitmq.amqpclient.tutorialPractical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.rabbitmq.amqpclient.AmqpClientApplication.EXCHANGE_NAME;
import static com.rabbitmq.amqpclient.AmqpClientApplication.ROUTING_KEY;

@Service
@RequiredArgsConstructor
public class PracticalTipSender {

    private final RabbitTemplate rabbitTemplate;
    private final String file = "src/main/resources/activities.txt";
    private List<ActivityMessage> activities = new ArrayList<>();
    private int iter = 0;


//    @Scheduled(fixedDelay = 3000L)
    @Scheduled(fixedDelay = 10L)
    public void sendPracticalTip() throws FileNotFoundException {

        if (activities.isEmpty())
            readAllActivities();

        ActivityMessage activityMessage = activities.get(iter);
        this.iter++;

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, activityMessage);
        System.out.println("Sent " + activityMessage.toString());


    }

    private void readAllActivities() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(file));

        //this.iter = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] words = line.split("\t\t");
            ActivityMessage activityMessage = new ActivityMessage(words[0], words[1], words[2]);
            activities.add(activityMessage);
        }
    }



}
