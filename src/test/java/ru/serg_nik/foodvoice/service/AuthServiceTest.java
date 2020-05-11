package ru.serg_nik.foodvoice.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.serg_nik.foodvoice.BaseTest;
import ru.serg_nik.foodvoice.model.User;
import ru.serg_nik.foodvoice.test_data.UserTestData;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static ru.serg_nik.foodvoice.test_data.UserTestData.*;

class AuthServiceTest extends BaseTest {

    @Autowired
    private AuthService authService;
    private final UserTestData userTestData = new UserTestData();

    static Stream<Arguments> initArgumentsForLoadUser() {
        return Stream.of(arguments(ADMIN), arguments(USER));
    }

    static Stream<Arguments> initArgumentsForFailureLoadUser() {
        return Stream.of(arguments(USER_DELETED), arguments(USER_NOT_ACTIVE));
    }

    @ParameterizedTest
    @MethodSource("initArgumentsForLoadUser")
    void loadUserByUsername(User expectedUser) {
        User actualUser = authService.loadUserByUsername(expectedUser.getEmail());
        assertTrue(userTestData.equalsWithRoles(expectedUser, actualUser));
    }

    @ParameterizedTest
    @MethodSource("initArgumentsForFailureLoadUser")
    void failureLoadUserByUsername(User expectedUser) {
        assertThrows(UsernameNotFoundException.class, () -> authService.loadUserByUsername(expectedUser.getEmail()));
    }

}