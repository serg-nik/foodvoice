package ru.serg_nik.foodvoice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.serg_nik.foodvoice.model.Dish;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DishDto extends BaseNamedDto<Dish> {

    private UUID menuId;
    @ApiModelProperty(position = 4, required = true, example = "100000")
    private Long price;

    public DishDto() {
        super();
    }

    public DishDto(Dish entity) {
        super(entity);
        menuId = entity.getMenu().getId();
        price = entity.getPrice();
    }

    public UUID getMenuId() {
        return menuId;
    }

    @ApiModelProperty(position = 3, hidden = true)
    public void setMenuId(UUID menuId) {
        this.menuId = menuId;
    }

}
