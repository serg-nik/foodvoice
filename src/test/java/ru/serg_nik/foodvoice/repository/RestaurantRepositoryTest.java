package ru.serg_nik.foodvoice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.serg_nik.foodvoice.model.Restaurant;
import ru.serg_nik.foodvoice.test_data.RestaurantTestData;

class RestaurantRepositoryTest extends BaseNamedEntityJpaRepositoryTest<Restaurant> {

    @Autowired
    RestaurantRepositoryTest(RestaurantRepository repository) {
        super(repository, new RestaurantTestData());
    }

}