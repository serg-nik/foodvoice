package ru.serg_nik.foodvoice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.serg_nik.foodvoice.model.Role;
import ru.serg_nik.foodvoice.test_data.RoleTestData;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static java.util.Collections.singleton;
import static org.junit.jupiter.api.Assertions.*;
import static ru.serg_nik.foodvoice.util.SetUtils.getById;

class RoleRepositoryTest extends BaseEntityJpaRepositoryTest<Role, RoleRepository, RoleTestData> {

    @Autowired
    RoleRepositoryTest(RoleRepository repository) {
        super(repository, new RoleTestData());
    }

    @Test
    void findFirstActiveByName() {
        Role expectedRole = testData.getFirstActive();
        Set<Role> roles = repository.findByNameIsIn(
                singleton(expectedRole.getName())
        );
        assertNotNull(roles);
        assertFalse(roles.isEmpty());
        Optional<Role> optionalRole = getById(roles, expectedRole.getId());
        assertTrue(optionalRole.isPresent());
        assertTrue(testData.equals(expectedRole, optionalRole.get()));
    }

    @Test
    void findSecondActiveByName() {
        Role expectedRole = testData.getSecondActive();
        Set<Role> roles = repository.findByNameIsIn(
                singleton(expectedRole.getName())
        );
        assertNotNull(roles);
        assertFalse(roles.isEmpty());
        Optional<Role> optionalRole = getById(roles, expectedRole.getId());
        assertTrue(optionalRole.isPresent());
        assertTrue(testData.equals(expectedRole, optionalRole.get()));
    }

    @Test
    void notFoundNotActiveByName() {
        Set<Role> roles = repository.findByNameIsIn(
                singleton(testData.getNotActive().getName())
        );
        assertNotNull(roles);
        assertTrue(roles.isEmpty());
    }

    @Test
    void findNotActiveByName() {
        Role expectedRole = testData.getNotActive();
        Set<Role> roles = repository.findByNameIsInWithNotActive(
                singleton(expectedRole.getName())
        );
        assertNotNull(roles);
        assertFalse(roles.isEmpty());
        Optional<Role> optionalRole = getById(roles, expectedRole.getId());
        assertTrue(optionalRole.isPresent());
        assertTrue(testData.equals(expectedRole, optionalRole.get()));
    }

}