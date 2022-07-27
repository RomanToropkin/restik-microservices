package ru.franq.itemservice.data.event;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
public class Event {

    private String uuid = UUID.randomUUID().toString();
    private String dttmCreate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    private String eventType;
    private Object message;

    public Event(String eventType, Object message) {
        this.eventType = eventType;
        this.message = message;
    }
}
