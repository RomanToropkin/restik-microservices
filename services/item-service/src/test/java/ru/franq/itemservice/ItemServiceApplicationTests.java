package ru.franq.itemservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.franq.itemservice.service.ItemService;

@SpringBootTest
@ActiveProfiles("test")
class ItemServiceApplicationTests {

    @Autowired
    private ItemService itemService;
    @Test
    void contextLoads() {
        var uuid = "029a6754-2a44-424b-8857-0489e3ad484f";
        var item = itemService.getItemByUuid(uuid);
        System.out.println(item);
    }

}
