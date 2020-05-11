package ru.serg_nik.foodvoice.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;
import ru.serg_nik.foodvoice.dto.RestaurantDto;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.serg_nik.foodvoice.model.Status.ACTIVE;
import static ru.serg_nik.foodvoice.rest.RestaurantRestControllerV1.MENUS_ACTUAL;
import static ru.serg_nik.foodvoice.rest.RestaurantRestControllerV1.REQUEST_URI;
import static ru.serg_nik.foodvoice.test_data.RestaurantTestData.*;
import static ru.serg_nik.foodvoice.test_data.UserTestData.ADMIN;
import static ru.serg_nik.foodvoice.test_data.UserTestData.USER;

class RestaurantRestControllerV1Test extends BaseRestControllerTest {

    private final static RestaurantDto newRestaurantDto = new RestaurantDto();

    static {
        newRestaurantDto.setName(NEW_RESTAURANT_NAME);
        newRestaurantDto.setAddress(NEW_RESTAURANT_ADDRESS);
        newRestaurantDto.setEmail(NEW_RESTAURANT_EMAIL);
    }

    @Test
    void create() throws Exception {
        ResultActions action =
                perform(postBuilder(REQUEST_URI, APPLICATION_JSON, newRestaurantDto, ADMIN))
                        .andDo(print())
                        .andExpect(status().isCreated())
                        .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));

        RestaurantDto restaurantDto = readValue(action, RestaurantDto.class);
        assertNotNull(restaurantDto);
        assertNotNull(restaurantDto.getId());
        assertEquals(NEW_RESTAURANT_NAME, restaurantDto.getName());
        assertEquals(NEW_RESTAURANT_ADDRESS, restaurantDto.getAddress());
        assertEquals(NEW_RESTAURANT_EMAIL, restaurantDto.getEmail());
        assertEquals(ACTIVE, restaurantDto.getStatus());
        assertTrue(restaurantDto.getMenus().isEmpty());
    }

    @Test
    void createForbiddenToUser() throws Exception {
        perform(postBuilder(REQUEST_URI, APPLICATION_JSON, newRestaurantDto, USER))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void createForbiddenToAll() throws Exception {
        perform(postBuilder(REQUEST_URI, APPLICATION_JSON, newRestaurantDto, null))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void getAllWithActualMenus() throws Exception {
        ResultActions action =
                perform(getBuilder(REQUEST_URI + MENUS_ACTUAL, APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));

        List<RestaurantDto> actual = readPageContent(action, new TypeReference<>() {
        });
        List<RestaurantDto> expected = RESTAURANTS_WITH_ACTUAL_MENU.stream().map(RestaurantDto::new).collect(toList());
        equalsWithSorting(expected, actual);
    }

}