package ru.serg_nik.foodvoice.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.serg_nik.foodvoice.dto.RestaurantDto;
import ru.serg_nik.foodvoice.model.Restaurant;
import ru.serg_nik.foodvoice.service.RestaurantService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.serg_nik.foodvoice.rest.RestaurantRestControllerV1.REQUEST_URI;
import static ru.serg_nik.foodvoice.util.RestControllerUtils.getUriNewResource;

@RestController
@RequestMapping(value = REQUEST_URI, produces = APPLICATION_JSON_VALUE)
public class RestaurantRestControllerV1 {

    public static final String REQUEST_URI = "/api/v1/restaurants/";
    public static final String MENUS_ACTUAL = "menus/actual";

    private final RestaurantService service;

    @Autowired
    public RestaurantRestControllerV1(RestaurantService service) {
        this.service = service;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Добавляет новый ресторан, требуется авторизация с ролью \"ADMIN\" по \"Bearer_\" ->",
            notes = "В результате успешного выполнения запроса возвращается JSON-объект ресторана"
    )
    public ResponseEntity<RestaurantDto> create(@ApiParam(name = "requestBody", required = true)
                                                @RequestBody RestaurantDto restaurantDto) {
        Restaurant restaurant = service.create(service.entityOf(restaurantDto));
        return ResponseEntity
                .created(getUriNewResource(REQUEST_URI, restaurant.getId()))
                .body(new RestaurantDto(restaurant));
    }

    @GetMapping(MENUS_ACTUAL)
    @ApiOperation(value = "Находит все рестораны с их меню дня",
            notes = "В результате успешного выполнения запроса возвращается массив JSON-объектов ресторанов с пагинацией"
    )
    public Page<RestaurantDto> getAllWithActualMenus(Pageable pageable) {
        return service.getAllWithActualMenus(pageable);
    }

}
