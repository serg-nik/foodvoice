package ru.serg_nik.foodvoice.rest;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;
import ru.serg_nik.foodvoice.dto.RegisterRequestDto;
import ru.serg_nik.foodvoice.dto.UserDto;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.serg_nik.foodvoice.rest.RegisterRestControllerV1.REQUEST_URI;
import static ru.serg_nik.foodvoice.test_data.RoleTestData.ROLE_USER;
import static ru.serg_nik.foodvoice.test_data.UserTestData.*;

class RegisterRestControllerV1Test extends BaseRestControllerTest {

    @Test
    void register() throws Exception {
        RegisterRequestDto requestDto = new RegisterRequestDto(NEW_USER_NAME, NEW_USER_EMAIL, NEW_USER_PASSWORD);

        ResultActions action =
                perform(postBuilder(REQUEST_URI, APPLICATION_JSON, requestDto, null))
                        .andDo(print())
                        .andExpect(status().isCreated())
                        .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));

        UserDto userDto = readValue(action, UserDto.class);
        assertNotNull(userDto);
        assertNotNull(userDto.getId());
        assertEquals(NEW_USER_NAME, userDto.getName());
        assertEquals(NEW_USER_EMAIL, userDto.getEmail());
        assertNotNull(userDto.getPassword());
        assertNotEquals(NEW_USER_PASSWORD, userDto.getPassword());
        assertNotEquals(Collections.singleton(ROLE_USER), userDto.getRoles());
    }

}
