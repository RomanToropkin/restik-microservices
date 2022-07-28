package ru.franq.itemservice.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "ingredients", schema = "main")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Cacheable(value = "ingredient")
public class Ingredient implements Serializable {

    @Id
    @Column(name = "uuid", nullable = false)
    private String id;

    @Column(name = "dttm_create", nullable = false)
    private LocalDateTime dttmCreate;

    @Column(name = "dttm_update")
    private Timestamp dttmUpdate;

    @Column(name = "can_remove", nullable = false)
    private Boolean canRemove = false;

    @Column(name = "is_removed", nullable = false)
    private Boolean isRemoved = false;

    @Column(name = "title", nullable = false)
    private String title;

}