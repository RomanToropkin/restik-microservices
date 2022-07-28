package ru.franq.itemservice.data.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Event {

    private String uuid = UUID.randomUUID().toString();
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSS")
    private LocalDateTime dttmCreate = LocalDateTime.now();
    private String eventType;
    private Object message;

    public Event(String eventType, Object message) {
        this.eventType = eventType;
        this.message = message;
    }
}
