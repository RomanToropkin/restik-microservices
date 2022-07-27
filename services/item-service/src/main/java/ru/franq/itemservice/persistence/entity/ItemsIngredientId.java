package ru.franq.itemservice.persistence.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemsIngredientId implements Serializable {
    private static final long serialVersionUID = -8719191793619227766L;
    @Column(name = "items_uuid", nullable = false)
    private String itemsUuid;

    @Column(name = "ingredients_uuid", nullable = false)
    private String ingredientsUuid;

    public ItemsIngredientId(String itemsUuid, String ingredientsUuid) {
        this.itemsUuid = itemsUuid;
        this.ingredientsUuid = ingredientsUuid;
    }

    public ItemsIngredientId() {
    }

    public String getItemsUuid() {
        return itemsUuid;
    }

    public void setItemsUuid(String itemsUuid) {
        this.itemsUuid = itemsUuid;
    }

    public String getIngredientsUuid() {
        return ingredientsUuid;
    }

    public void setIngredientsUuid(String ingredientsUuid) {
        this.ingredientsUuid = ingredientsUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ItemsIngredientId entity = (ItemsIngredientId) o;
        return Objects.equals(this.ingredientsUuid, entity.ingredientsUuid) &&
                Objects.equals(this.itemsUuid, entity.itemsUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientsUuid, itemsUuid);
    }

}