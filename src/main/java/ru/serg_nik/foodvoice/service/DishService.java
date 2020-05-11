package ru.serg_nik.foodvoice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.serg_nik.foodvoice.dto.DishDto;
import ru.serg_nik.foodvoice.model.Dish;
import ru.serg_nik.foodvoice.model.Menu;
import ru.serg_nik.foodvoice.repository.DishRepository;

@Service
@Slf4j
public class DishService extends BaseEntityService<Dish, DishRepository> {

    @Autowired
    public DishService(DishRepository repository) {
        super(repository);
    }

    public Dish entityOf(DishDto dto, Menu menu) {
        Dish entity = new Dish();
        BeanUtils.copyProperties(dto, entity);
        entity.setMenu(menu);
        return entity;
    }

}
