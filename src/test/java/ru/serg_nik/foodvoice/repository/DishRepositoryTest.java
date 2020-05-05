package ru.serg_nik.foodvoice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.serg_nik.foodvoice.model.Dish;
import ru.serg_nik.foodvoice.test_data.DishTestData;

class DishRepositoryTest extends BaseNamedEntityJpaRepositoryTest<Dish> {

    @Autowired
    DishRepositoryTest(DishRepository repository) {
        super(repository, new DishTestData());
    }

}