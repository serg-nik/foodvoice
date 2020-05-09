package ru.serg_nik.foodvoice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.serg_nik.foodvoice.model.Menu;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MenuDto extends BaseNamedDto<Menu> {

    private UUID restaurantId;
    private List<DishDto> dishes;

    public MenuDto() {
        super();
    }

    public MenuDto(Menu entity) {
        super(entity);
        restaurantId = entity.getRestaurant().getId();
        dishes = entity.getDishes().stream().map(DishDto::new).collect(toList());
    }

}
