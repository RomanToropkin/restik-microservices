package ru.franq.itemservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.franq.itemservice.data.CreateItemDto;
import ru.franq.itemservice.persistence.entity.Item;
import ru.franq.itemservice.persistence.entity.ItemsIngredient;
import ru.franq.itemservice.persistence.entity.ItemsIngredientId;
import ru.franq.itemservice.persistence.repos.IngredientRepository;
import ru.franq.itemservice.persistence.repos.ItemRepository;
import ru.franq.itemservice.persistence.repos.ItemsIngredientRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;
    private final IngredientRepository ingredientRepository;
    private final ItemsIngredientRepository itemsIngredientRepository;

    @Transactional
    public Item createItem(CreateItemDto dto) {
        var item = Item.builder()
                .id(UUID.randomUUID().toString())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .calories(dto.getCalories())
                .protein(dto.getProtein())
                .fat(dto.getFat())
                .carbohydrates(dto.getCarbohydrates())
                .dttmCreate(LocalDateTime.now())
                .isRemoved(false)
                .build();
        itemRepository.saveAndFlush(item);

        dto.getIngredientList()
                .forEach(v -> {
                    var ingredient = ingredientRepository.findByIdAndIsRemoved(v, false)
                            .orElseThrow(() -> new EntityNotFoundException("Ингредиента с uuid = %s не найдено".formatted(v)));
                    var itemIngr = ItemsIngredient.builder()
                            .id(new ItemsIngredientId(item.getId(), ingredient.getId()))
                            .itemsUuid(item)
                            .ingredientsUuid(ingredient)
                            .dttmCreate(LocalDateTime.now())
                            .isRemoved(false)
                            .build();
                    itemsIngredientRepository.saveAndFlush(itemIngr);
                });
        log.info("Successful create item with id: %s".formatted(item.getId()));
        return item;
    }

}
