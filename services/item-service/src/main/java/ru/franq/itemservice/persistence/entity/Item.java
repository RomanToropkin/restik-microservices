package ru.franq.itemservice.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "items", schema = "main")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("item")
public class Item implements Serializable {

    private static final long serialVersionUID = 7156526077883281623L;

    @Id
    @Column(name = "uuid", nullable = false)
    private String id = UUID.randomUUID().toString();

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "calories", nullable = false)
    private Integer calories;

    @Column(name = "protein", nullable = false)
    private Integer protein;

    @Column(name = "fat", nullable = false)
    private Integer fat;

    @Column(name = "carbohydrates", nullable = false)
    private Integer carbohydrates;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSS")
    @Column(name = "dttm_create", nullable = false)
    private LocalDateTime dttmCreate = LocalDateTime.now();

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSS")
    @Column(name = "dttm_update")
    private LocalDateTime dttmUpdate;

    @Column(name = "is_removed", nullable = false)
    private Boolean isRemoved = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "items_ingredients", schema = "main",
            joinColumns = {@JoinColumn(name = "items_uuid")},
            inverseJoinColumns = {@JoinColumn(name = "ingredients_uuid")})
    private List<Ingredient> ingredients = new ArrayList<>();

}