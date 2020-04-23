package com.example.springdemo.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ActivityController {

    private final SimpMessagingTemplate messagingTemplate;

    @EventListener(BaseEvent.class)
    public void handleActivityProblem(BaseEvent event){
        System.out.println("Event detected: "+event.getActivity().toString());
        messagingTemplate.convertAndSend("/topic/events", event);
    }
}
