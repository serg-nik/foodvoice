package ru.serg_nik.foodvoice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.serg_nik.foodvoice.model.User;
import ru.serg_nik.foodvoice.test_data.UserTestData;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.serg_nik.foodvoice.test_data.RoleTestData.*;
import static ru.serg_nik.foodvoice.test_data.UserTestData.ADMIN;
import static ru.serg_nik.foodvoice.test_data.UserTestData.USER;

class UserRepositoryTest extends BaseEntityJpaRepositoryTest<User, UserRepository, UserTestData> {

    @Autowired
    UserRepositoryTest(UserRepository repository) {
        super(repository, new UserTestData());
    }

    @Test
    void findAdminWithRoles() {
        Optional<User> admin = repository.findWithRoles(ADMIN.getId());
        assertTrue(admin.isPresent());
        assertTrue(testData.equalsWithRoles(ADMIN, admin.get()));
    }

    @Test
    void findUserWithRoles() {
        Optional<User> user = repository.findWithRoles(USER.getId());
        assertTrue(user.isPresent());
        assertTrue(testData.equalsWithRoles(USER, user.get()));
    }

    @Test
    void findAdminWithVoices() {
        Optional<User> admin = repository.findWithVoices(ADMIN.getId());
        assertTrue(admin.isPresent());
        assertTrue(testData.equalsWithVoices(ADMIN, admin.get()));
    }

    @Test
    void findUserWithVoices() {
        Optional<User> user = repository.findWithVoices(USER.getId());
        assertTrue(user.isPresent());
        assertTrue(testData.equalsWithVoices(USER, user.get()));
    }

    @Test
    void addRole() {
        Optional<User> user = repository.findWithRoles(USER.getId());
        assertTrue(user.isPresent());
        assertFalse(user.get().getRoles().containsAll(ROLES));
        user.get().getRoles().add(ROLE_ADMIN);
        repository.save(user.get());

        Optional<User> updatedUser = repository.findWithRoles(USER.getId());
        assertTrue(updatedUser.isPresent());
        assertTrue(updatedUser.get().getRoles().containsAll(ROLES));
    }

    @Test
    void removeRole() {
        Optional<User> admin = repository.findWithRoles(ADMIN.getId());
        assertTrue(admin.isPresent());
        assertTrue(admin.get().getRoles().containsAll(ROLES));
        assertTrue(admin.get().getRoles().remove(ROLE_USER));
        repository.save(admin.get());

        Optional<User> updatedAdmin = repository.findWithRoles(ADMIN.getId());
        assertTrue(updatedAdmin.isPresent());
        assertFalse(updatedAdmin.get().getRoles().containsAll(ROLES));
    }

}
