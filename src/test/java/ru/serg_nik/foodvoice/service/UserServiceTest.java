package ru.serg_nik.foodvoice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.serg_nik.foodvoice.dto.RegisterRequestDto;
import ru.serg_nik.foodvoice.dto.UserDto;
import ru.serg_nik.foodvoice.model.User;
import ru.serg_nik.foodvoice.test_data.UserTestData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserServiceTest extends BaseEntityServiceTest<User, UserService, UserTestData> {

    @Autowired
    UserServiceTest(UserService service) {
        super(service, new UserTestData());
    }

    @Override
    protected void updateNewExpectedEntity(User expectedUser, User actualUser) {
        super.updateNewExpectedEntity(expectedUser, actualUser);
        assertNotNull(actualUser.getPassword());
        expectedUser.setPassword(actualUser.getPassword());
    }

    @Test
    void register() {
        User expectedUser = testData.getNew();
        RegisterRequestDto requestDto = new RegisterRequestDto();
        requestDto.setName(expectedUser.getName());
        requestDto.setEmail(expectedUser.getEmail());
        requestDto.setPassword(expectedUser.getPassword());
        User actualUser = service.register(requestDto);
        assertNotNull(actualUser);
        updateNewExpectedEntity(expectedUser, actualUser);
        assertEquals(new UserDto(expectedUser), new UserDto(actualUser));
    }

}