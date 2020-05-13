package ru.serg_nik.foodvoice.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.serg_nik.foodvoice.dto.UserDto;
import ru.serg_nik.foodvoice.model.User;
import ru.serg_nik.foodvoice.service.UserService;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.serg_nik.foodvoice.rest.UserRestControllerV1.REQUEST_URI;
import static ru.serg_nik.foodvoice.util.RestControllerUtils.getUriNewResource;

@RestController
@RequestMapping(value = REQUEST_URI, produces = APPLICATION_JSON_VALUE)
public class UserRestControllerV1 {

    public static final String REQUEST_URI = "/api/v1/users/";

    private final UserService service;

    @Autowired
    public UserRestControllerV1(UserService service) {
        this.service = service;
    }

    @PutMapping(value = "{id}", consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Обновляет существующего пользователя, требуется авторизация с ролью \"ADMIN\" по \"Bearer_\" ->",
            notes = "В результате успешного выполнения запроса возвращается JSON-объект пользователя"
    )
    public ResponseEntity<UserDto> update(@ApiParam(required = true, defaultValue = "f16eb5ea-14bc-42b0-809a-333639464730")
                                          @PathVariable("id") UUID id, @RequestBody UserDto userDto) {
        User updatedUser = service.update(id, service.entityOf(userDto));
        userDto = new UserDto(updatedUser);
        return ResponseEntity
                .created(getUriNewResource(REQUEST_URI, userDto.getId()))
                .body(userDto);
    }

}
