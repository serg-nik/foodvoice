package ru.serg_nik.foodvoice.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import ru.serg_nik.foodvoice.model.User;
import ru.serg_nik.foodvoice.test_data.UserTestData;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static ru.serg_nik.foodvoice.test_data.RoleTestData.*;
import static ru.serg_nik.foodvoice.test_data.UserTestData.*;

class UserRepositoryTest extends BaseEntityJpaRepositoryTest<User, UserRepository, UserTestData> {

    static Stream<Arguments> initArgumentsForFind() {
        return Stream.of(arguments(ADMIN), arguments(USER));
    }

    static Stream<Arguments> initArgumentsForNotFound() {
        return Stream.of(arguments(USER_DELETED), arguments(USER_NOT_ACTIVE));
    }

    @Autowired
    UserRepositoryTest(UserRepository repository) {
        super(repository, new UserTestData());
    }

    @ParameterizedTest
    @MethodSource("initArgumentsForFind")
    void findByIdWithRoles(User user) {
        Optional<User> optionalUser = repository.findByIdWithRoles(user.getId());
        assertTrue(optionalUser.isPresent());
        assertTrue(testData.equalsWithRoles(user, optionalUser.get()));
    }

    @ParameterizedTest
    @MethodSource("initArgumentsForNotFound")
    void notFoundByIdWithRoles(User user) {
        assertTrue(repository.findByIdWithRoles(user.getId()).isEmpty());
    }

    @ParameterizedTest
    @MethodSource("initArgumentsForFind")
    void findByEmailWithRoles(User user) {
        Optional<User> optionalUser = repository.findByEmailWithRoles(user.getEmail());
        assertTrue(optionalUser.isPresent());
        assertTrue(testData.equalsWithRoles(user, optionalUser.get()));
    }

    @ParameterizedTest
    @MethodSource("initArgumentsForNotFound")
    void notFoundByEmailWithRoles(User user) {
        assertTrue(repository.findByEmailWithRoles(user.getEmail()).isEmpty());
    }

    @ParameterizedTest
    @MethodSource("initArgumentsForFind")
    void findByIdWithVoices(User user) {
        Optional<User> optionalUser = repository.findByIdWithVoices(user.getId());
        assertTrue(optionalUser.isPresent());
        assertTrue(testData.equalsWithVoices(user, optionalUser.get()));
    }

    @ParameterizedTest
    @MethodSource("initArgumentsForNotFound")
    void notFoundByIdWithVoices(User user) {
        assertTrue(repository.findByIdWithVoices(user.getId()).isEmpty());
    }

    @Test
    void addRole() {
        Optional<User> optionalUser = repository.findByIdWithRoles(USER.getId());
        assertTrue(optionalUser.isPresent());
        assertFalse(optionalUser.get().getRoles().containsAll(ROLES));
        optionalUser.get().getRoles().add(ROLE_ADMIN);
        repository.save(optionalUser.get());

        Optional<User> updatedUser = repository.findByIdWithRoles(USER.getId());
        assertTrue(updatedUser.isPresent());
        assertTrue(updatedUser.get().getRoles().containsAll(ROLES));
    }

    @Test
    void removeRole() {
        Optional<User> optionalUser = repository.findByIdWithRoles(ADMIN.getId());
        assertTrue(optionalUser.isPresent());
        assertTrue(optionalUser.get().getRoles().containsAll(ROLES));
        assertTrue(optionalUser.get().getRoles().remove(ROLE_USER));
        repository.save(optionalUser.get());

        Optional<User> updatedAdmin = repository.findByIdWithRoles(ADMIN.getId());
        assertTrue(updatedAdmin.isPresent());
        assertFalse(updatedAdmin.get().getRoles().containsAll(ROLES));
    }

}
