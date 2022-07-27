package ru.franq.itemservice.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "items_ingredients", schema = "main")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemsIngredient {
    @EmbeddedId
    private ItemsIngredientId id;

    @MapsId("itemsUuid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "items_uuid", nullable = false)
    private Item itemsUuid;

    @MapsId("ingredientsUuid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ingredients_uuid", nullable = false)
    private Ingredient ingredientsUuid;

    @Column(name = "dttm_create", nullable = false)
    private LocalDateTime dttmCreate = LocalDateTime.now();

    @Column(name = "is_removed", nullable = false)
    private Boolean isRemoved = false;

}