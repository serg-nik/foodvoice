package ru.serg_nik.foodvoice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.serg_nik.foodvoice.meta.Meta;
import ru.serg_nik.foodvoice.model.Restaurant;
import ru.serg_nik.foodvoice.test_data.RestaurantTestData;

class RestaurantRepositoryTest extends BaseEntityJpaRepositoryTest<Restaurant> {

    @Autowired
    RestaurantRepositoryTest(RestaurantRepository repository) {
        super(repository, new RestaurantTestData(),
                PageRequest.of(0, 10, Sort.by(Meta.Restaurant.NAME).and(Sort.by(Meta.Restaurant.ADDRESS)))
        );
    }

}