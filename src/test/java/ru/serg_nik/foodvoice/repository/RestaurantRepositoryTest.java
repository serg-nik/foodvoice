package ru.serg_nik.foodvoice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import ru.serg_nik.foodvoice.model.Restaurant;
import ru.serg_nik.foodvoice.test_data.RestaurantTestData;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.serg_nik.foodvoice.test_data.RestaurantTestData.RESTAURANTS_WITH_ACTUAL_MENU;

class RestaurantRepositoryTest
        extends BaseEntityJpaRepositoryTest<Restaurant,RestaurantRepository, RestaurantTestData> {

    @Autowired
    RestaurantRepositoryTest(RestaurantRepository repository) {
        super(repository, new RestaurantTestData());
    }

    @Test
    void findAllWithActualMenu() {
        Page<Restaurant> page = repository.findAllWithActualMenus(testData.getPageable());
        equalsWithSorting(RESTAURANTS_WITH_ACTUAL_MENU, page.getContent());
        for (int i = 0; i < RESTAURANTS_WITH_ACTUAL_MENU.size(); i++) {
            assertTrue(testData.equalsWithMenus(
                    RESTAURANTS_WITH_ACTUAL_MENU.get(i), page.getContent().get(i)
            ));
        }
    }

}