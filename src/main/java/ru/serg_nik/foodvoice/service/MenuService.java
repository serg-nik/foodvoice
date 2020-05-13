package ru.serg_nik.foodvoice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.serg_nik.foodvoice.dto.MenuDto;
import ru.serg_nik.foodvoice.model.Menu;
import ru.serg_nik.foodvoice.model.Restaurant;
import ru.serg_nik.foodvoice.repository.MenuRepository;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class MenuService extends BaseEntityService<Menu, MenuRepository> {

    private final DishService dishService;

    @Autowired
    public MenuService(MenuRepository repository, DishService dishService) {
        super(repository);
        this.dishService = dishService;
    }

    public Menu entityOf(MenuDto dto, Restaurant restaurant) {
        Menu entity = new Menu();
        BeanUtils.copyProperties(dto, entity, "restaurantId", "dishes");
        entity.setRestaurant(restaurant);
        entity.setDishes(
                dto.getDishes().stream()
                        .map(dishDto -> dishService.entityOf(dishDto, entity))
                        .collect(toList())
        );
        return entity;
    }

}
