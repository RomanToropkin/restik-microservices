package ru.franq.itemservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.franq.itemservice.data.CreateItemDto;
import ru.franq.itemservice.persistence.entity.Ingredient;
import ru.franq.itemservice.persistence.entity.Item;
import ru.franq.itemservice.persistence.entity.ItemsIngredient;
import ru.franq.itemservice.persistence.repos.IngredientRepository;
import ru.franq.itemservice.persistence.repos.ItemRepository;
import ru.franq.itemservice.persistence.repos.ItemsIngredientRepository;
import ru.franq.itemservice.service.ItemService;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ItemServiceTests {

    @Mock
    private IngredientRepository ingredientRepository;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private ItemsIngredientRepository itemsIngredientRepository;

    private ItemService itemService;
    private CreateItemDto createItemDto;
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private ItemsIngredient itemsIngredient1;
    private ItemsIngredient itemsIngredient2;
    private Item item;

    @BeforeEach
    public void setUp() {
        itemService = new ItemService(itemRepository, ingredientRepository, itemsIngredientRepository, null);
        var ingList = new ArrayList<String>();
        ingList.add("id1");
        ingList.add("id2");
        createItemDto = CreateItemDto.builder()
                .title("Test")
                .description("Test 2")
                .calories(100)
                .protein(1)
                .fat(2)
                .carbohydrates(3)
                .ingredientList(ingList)
                .build();
        ingredient1 = Ingredient.builder()
                .id("id1")
                .title("ing1")
                .canRemove(false)
                .build();
        ingredient2 = Ingredient.builder()
                .id("id2")
                .title("ing2")
                .canRemove(true)
                .build();
        item = Item.builder()
                .id("id")
                .title("Test")
                .description("Test 2")
                .calories(100)
                .protein(1)
                .fat(2)
                .carbohydrates(3)
                .build();
        itemsIngredient1 = ItemsIngredient.builder()
                .itemsUuid(item)
                .ingredientsUuid(ingredient1)
                .build();
        itemsIngredient2 = ItemsIngredient.builder()
                .itemsUuid(item)
                .ingredientsUuid(ingredient2)
                .build();
    }


    @DisplayName("Test for createItem method")
    @Test
    void givenCreateItemDtoObject_whenCreateItem_thenSaveItem(){
        given(ingredientRepository.findByIdAndIsRemoved("id1", true)).willReturn(Optional.of(ingredient1));
        given(ingredientRepository.findByIdAndIsRemoved("id2", true)).willReturn(Optional.of(ingredient2));
        given(itemRepository.saveAndFlush(any(Item.class))).willReturn(item);
        given(itemsIngredientRepository.saveAndFlush(itemsIngredient1)).willReturn(itemsIngredient1);
        given(itemsIngredientRepository.saveAndFlush(itemsIngredient2)).willReturn(itemsIngredient2);
        Assertions.assertDoesNotThrow(()->itemService.createItem(createItemDto));
    }

}
