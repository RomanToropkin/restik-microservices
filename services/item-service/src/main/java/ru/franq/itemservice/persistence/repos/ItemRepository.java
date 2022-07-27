package ru.franq.itemservice.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.franq.itemservice.persistence.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
}