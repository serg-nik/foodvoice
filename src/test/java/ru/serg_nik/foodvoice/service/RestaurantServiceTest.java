package ru.serg_nik.foodvoice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import ru.serg_nik.foodvoice.dto.RestaurantDto;
import ru.serg_nik.foodvoice.model.Restaurant;
import ru.serg_nik.foodvoice.test_data.RestaurantTestData;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static ru.serg_nik.foodvoice.test_data.RestaurantTestData.RESTAURANTS_WITH_ACTUAL_MENU;

class RestaurantServiceTest extends BaseEntityServiceTest<Restaurant, RestaurantService, RestaurantTestData> {

    @Autowired
    RestaurantServiceTest(RestaurantService service) {
        super(service, new RestaurantTestData());
    }

    @Test
    void findAllWithActualMenus() {
        List<RestaurantDto> expected = RESTAURANTS_WITH_ACTUAL_MENU.stream().map(RestaurantDto::new).collect(toList());
        Page<RestaurantDto> actualPage = service.getAllWithActualMenus(testData.getPageable());
        equalsWithSorting(expected, actualPage.getContent());
    }

}