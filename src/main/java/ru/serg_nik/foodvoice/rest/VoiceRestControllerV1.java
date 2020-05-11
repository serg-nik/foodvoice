package ru.serg_nik.foodvoice.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.serg_nik.foodvoice.dto.VoiceDto;
import ru.serg_nik.foodvoice.dto.VoiceRequestDto;
import ru.serg_nik.foodvoice.model.Voice;
import ru.serg_nik.foodvoice.service.AuthService;
import ru.serg_nik.foodvoice.service.VoiceService;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.serg_nik.foodvoice.rest.VoiceRestControllerV1.REQUEST_URI;
import static ru.serg_nik.foodvoice.util.RestControllerUtils.getUriNewResource;

@RestController
@RequestMapping(value = REQUEST_URI, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class VoiceRestControllerV1 {

    public static final String REQUEST_URI = "/api/v1/voices/";

    private final VoiceService service;
    private final AuthService authService;

    @Autowired
    public VoiceRestControllerV1(VoiceService service, AuthService authService) {
        this.service = service;
        this.authService = authService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Выбирает ресторан (его меню) для обеда, требуется авторизация с ролью \"USER\" по \"Bearer_\" ->",
            notes = "В результате успешного выполнения запроса возвращается JSON-объект нового голоса"
    )
    public ResponseEntity<VoiceDto> vote(@ApiParam(name = "requestBody", required = true)
                                         @RequestBody VoiceRequestDto requestDto) {
        Voice voice = service.vote(authService.getAuthUser().getId(), requestDto.getMenuId());
        return ResponseEntity
                .created(getUriNewResource(REQUEST_URI, voice.getId()))
                .body(new VoiceDto(voice));
    }

    @PutMapping(value = "{id}", consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Изменяет выбор ресторана (его меню) для обеда в тот же день до 11:00, " +
            "требуется авторизация с ролью \"USER\" по \"Bearer_\" ->",
            notes = "В результате успешного выполнения запроса возвращается JSON-объект изменённого голоса"
    )
    public ResponseEntity<?> changeVote(@ApiParam(required = true, defaultValue = "f16eb5ea-14bc-42b0-809a-333639464730")
                                        @PathVariable UUID id,
                                        @ApiParam(name = "requestBody", required = true) @RequestBody VoiceRequestDto requestDto) {
        try {
            Voice voice = service.changeVote(id, authService.getAuthUser().getId(), requestDto.getMenuId());
            return ResponseEntity
                    .created(getUriNewResource(REQUEST_URI, voice.getId()))
                    .body(new VoiceDto(voice));
        } catch (EntityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (TimeoutException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

}
