package ru.franq.itemservice.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.franq.itemservice.persistence.entity.ItemsIngredient;
import ru.franq.itemservice.persistence.entity.ItemsIngredientId;

@Repository
public interface ItemsIngredientRepository extends JpaRepository<ItemsIngredient, ItemsIngredientId> {
}