package ru.franq.itemservice.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.franq.itemservice.persistence.entity.Ingredient;

import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {

    boolean existsByIdAndIsRemoved(String s, boolean isRemoved);
    Optional<Ingredient> findByIdAndIsRemoved(String s, boolean isRemoved);
}