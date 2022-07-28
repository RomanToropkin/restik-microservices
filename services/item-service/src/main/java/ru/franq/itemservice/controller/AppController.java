package ru.franq.itemservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import ru.franq.itemservice.data.CreateItemDto;
import ru.franq.itemservice.data.ResponseMsg;
import ru.franq.itemservice.kafka.KafkaProducer;
import ru.franq.itemservice.persistence.entity.Item;
import ru.franq.itemservice.service.ItemService;

import javax.validation.Valid;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class AppController {

    private final ItemService itemService;
    private final KafkaProducer kafkaProducer;

    @PostMapping("/create")
    public ResponseMsg createItem(@RequestBody @Valid CreateItemDto dto){
        var item = itemService.createItem(dto);
        kafkaProducer.sendEvent("item-create", item);
        return ResponseMsg.builder()
                .status("0")
                .message("Новая еда успешно добавлена в базу!")
                .build();
    }

    @GetMapping("/{uuid}")
    public Item getItemById(@PathVariable(value = "uuid") String uuid){
        return itemService.getItemByUuid(uuid);
    }

}
