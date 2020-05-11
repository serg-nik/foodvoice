package ru.serg_nik.foodvoice.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import ru.serg_nik.foodvoice.dto.AuthRequestDto;
import ru.serg_nik.foodvoice.dto.AuthResponseDto;
import ru.serg_nik.foodvoice.security.jwt.JwtTokenProvider;
import ru.serg_nik.foodvoice.test_data.UserTestData;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.serg_nik.foodvoice.rest.AuthRestControllerV1.REQUEST_URI;

class AuthRestControllerV1Test extends BaseRestControllerTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void login() throws Exception {
        String email = UserTestData.ADMIN.getEmail();
        AuthRequestDto requestDto = new AuthRequestDto(email, "password");

        ResultActions action =
                perform(postBuilder(REQUEST_URI, APPLICATION_JSON, requestDto, null))
                        .andDo(print())
                        .andExpect(status().isCreated())
                        .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));

        AuthResponseDto authResponseDto = readValue(action, AuthResponseDto.class);
        assertNotNull(authResponseDto);
        assertEquals(email, requestDto.getEmail());
        assertNotNull(authResponseDto.getToken());
        assertTrue(jwtTokenProvider.validate(authResponseDto.getToken()));
    }

}
