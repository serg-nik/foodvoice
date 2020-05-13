package ru.serg_nik.foodvoice.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.serg_nik.foodvoice.dto.RegisterRequestDto;
import ru.serg_nik.foodvoice.dto.UserDto;
import ru.serg_nik.foodvoice.model.User;
import ru.serg_nik.foodvoice.service.UserService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.serg_nik.foodvoice.rest.RegisterRestControllerV1.REQUEST_URI;
import static ru.serg_nik.foodvoice.util.RestControllerUtils.getUriNewResource;

@RestController
@RequestMapping(value = REQUEST_URI, produces = APPLICATION_JSON_VALUE)
public class RegisterRestControllerV1 {

    public static final String REQUEST_URI = "/api/v1/register/";

    private final UserService service;

    @Autowired
    public RegisterRestControllerV1(UserService service) {
        this.service = service;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Регистрирует нового пользователя",
            notes = "В результате успешного выполнения запроса возвращается JSON-объект пользователя"
    )
    public ResponseEntity<UserDto> register(@ApiParam(name = "requestBody", required = true)
                                            @RequestBody RegisterRequestDto registerRequestDto) {
        User user = service.register(registerRequestDto);
        return ResponseEntity
                .created(getUriNewResource(UserRestControllerV1.REQUEST_URI, user.getId()))
                .body(new UserDto(user));
    }

}
