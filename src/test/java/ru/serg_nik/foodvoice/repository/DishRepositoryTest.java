package ru.serg_nik.foodvoice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.serg_nik.foodvoice.meta.Meta;
import ru.serg_nik.foodvoice.model.Dish;
import ru.serg_nik.foodvoice.test_data.DishTestData;

class DishRepositoryTest extends BaseEntityJpaRepositoryTest<Dish> {

    @Autowired
    DishRepositoryTest(DishRepository repository) {
        super(repository, new DishTestData(), PageRequest.of(0, 10, Sort.by(Meta.Dish.NAME)));
    }

}