package ru.serg_nik.foodvoice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.serg_nik.foodvoice.model.User;
import ru.serg_nik.foodvoice.test_data.UserTestData;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static ru.serg_nik.foodvoice.test_data.RoleTestData.*;
import static ru.serg_nik.foodvoice.test_data.UserTestData.ADMIN;
import static ru.serg_nik.foodvoice.test_data.UserTestData.USER;

class UserRepositoryTest extends BaseNamedEntityJpaRepositoryTest<User> {

    private final UserRepository userRepository;
    private final UserTestData userTestData;

    @Autowired
    UserRepositoryTest(UserRepository repository) {
        super(repository, new UserTestData());
        userRepository = repository;
        userTestData = (UserTestData) testData;
    }

    @Test
    void findAdminWithRoles() {
        Optional<User> admin = userRepository.findWithRoles(ADMIN.getId());
        assertTrue(admin.isPresent());
        assertTrue(userTestData.equalsWithRoles(ADMIN, admin.get()));
    }

    @Test
    void findUserWithRoles() {
        Optional<User> user = userRepository.findWithRoles(USER.getId());
        assertTrue(user.isPresent());
        assertTrue(userTestData.equalsWithRoles(USER, user.get()));
    }

    @Test
    void findAdminWithVoices() {
        Optional<User> admin = userRepository.findWithVoices(ADMIN.getId());
        assertTrue(admin.isPresent());
        assertTrue(userTestData.equalsWithVoices(ADMIN, admin.get()));
    }

    @Test
    void findUserWithVoices() {
        Optional<User> user = userRepository.findWithVoices(USER.getId());
        assertTrue(user.isPresent());
        assertTrue(userTestData.equalsWithVoices(USER, user.get()));
    }

    @Test
    void addRole() {
        Optional<User> user = userRepository.findWithRoles(USER.getId());
        assertTrue(user.isPresent());
        assertFalse(user.get().getRoles().containsAll(ROLES));
        user.get().getRoles().add(ROLE_ADMIN);
        userRepository.save(user.get());

        Optional<User> updatedUser = userRepository.findWithRoles(USER.getId());
        assertTrue(updatedUser.isPresent());
        assertTrue(updatedUser.get().getRoles().containsAll(ROLES));
    }

    @Test
    void removeRole() {
        Optional<User> admin = userRepository.findWithRoles(ADMIN.getId());
        assertTrue(admin.isPresent());
        assertTrue(admin.get().getRoles().containsAll(ROLES));
        assertTrue(admin.get().getRoles().remove(ROLE_USER));
        userRepository.save(admin.get());

        Optional<User> updatedAdmin = userRepository.findWithRoles(ADMIN.getId());
        assertTrue(updatedAdmin.isPresent());
        assertFalse(updatedAdmin.get().getRoles().containsAll(ROLES));
    }

}
