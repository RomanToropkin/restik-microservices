package ru.franq.itemservice.redis.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.franq.itemservice.persistence.entity.Item;

@Repository
public interface ItemRedisRepository extends CrudRepository<Item, String> {
}
