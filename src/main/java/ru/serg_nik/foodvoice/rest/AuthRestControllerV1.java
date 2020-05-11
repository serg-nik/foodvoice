package ru.serg_nik.foodvoice.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.serg_nik.foodvoice.dto.AuthRequestDto;
import ru.serg_nik.foodvoice.dto.AuthResponseDto;
import ru.serg_nik.foodvoice.security.jwt.JwtTokenProvider;
import ru.serg_nik.foodvoice.service.AuthService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.serg_nik.foodvoice.rest.AuthRestControllerV1.REQUEST_URI;

@RestController
@RequestMapping(value = REQUEST_URI, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class AuthRestControllerV1 {

    public static final String REQUEST_URI = "/api/v1/login/";

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService service;

    @Autowired
    public AuthRestControllerV1(AuthService service, AuthenticationManager authenticationManager,
                                JwtTokenProvider jwtTokenProvider) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping
    @ApiOperation(value = "Авторизует пользователя",
            notes = "В результате успешного выполнения запроса возвращается JSON-объект с email пользователя и токеном"
    )
    public ResponseEntity<AuthResponseDto> login(@ApiParam(name = "requestBody", required = true)
                                                 @RequestBody AuthRequestDto authRequestDto) {
        String email = authRequestDto.getEmail();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, authRequestDto.getPassword())
        );
        String token = jwtTokenProvider.create(service.loadUserByUsername(email));
        return new ResponseEntity<>(new AuthResponseDto(email, token), CREATED);
    }

}
