package ru.serg_nik.foodvoice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.serg_nik.foodvoice.dto.UserDto;
import ru.serg_nik.foodvoice.model.User;
import ru.serg_nik.foodvoice.test_data.UserTestData;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest extends BaseEntityServiceTest<User, UserService, UserTestData> {

    @Autowired
    public UserServiceTest(UserService service) {
        super(service, new UserTestData());
    }

    @Override
    protected void updateNewExpectedEntity(User expected, User actual) {
        super.updateNewExpectedEntity(expected, actual);
        assertNotNull(actual.getPassword());
        expected.setPassword(actual.getPassword());
    }

    @Test
    void register() {
        User user = testData.getNew();
        UserDto actual = service.register(user);
        assertNotNull(actual);
        assertNotNull(actual.getId());
        UserDto expected = new UserDto(user);
        expected.setId(actual.getId());
        assertEquals(expected, actual);
    }

}