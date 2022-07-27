package ru.franq.itemservice.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class CreateItemDto {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private Integer calories;
    @NotNull
    private Integer protein;
    @NotNull
    private Integer fat;
    @NotNull
    private Integer carbohydrates;
    @NotEmpty
    @JsonProperty("ingredient_list")
    private List<String> ingredientList;

}
