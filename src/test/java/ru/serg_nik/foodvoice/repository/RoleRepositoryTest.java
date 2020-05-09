package ru.serg_nik.foodvoice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.serg_nik.foodvoice.meta.Meta;
import ru.serg_nik.foodvoice.model.Role;
import ru.serg_nik.foodvoice.test_data.RoleTestData;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RoleRepositoryTest extends BaseEntityJpaRepositoryTest<Role> {

    private final RoleRepository roleRepository;

    @Autowired
    RoleRepositoryTest(RoleRepository repository) {
        super(repository, new RoleTestData(), PageRequest.of(0, 10, Sort.by(Meta.Role.NAME)));
        roleRepository = repository;
    }

    @Test
    void findFirstActiveByName() {
        Optional<Role> optional = roleRepository.findByName(testData.getFirstActive().getName());
        assertTrue(optional.isPresent());
        assertTrue(testData.equals(testData.getFirstActive(), optional.get()));
    }

    @Test
    void findSecondActiveByName() {
        Optional<Role> optional = roleRepository.findByName(testData.getSecondActive().getName());
        assertTrue(optional.isPresent());
        assertTrue(testData.equals(testData.getSecondActive(), optional.get()));
    }

    @Test
    void notFoundNotActiveByName() {
        Optional<Role> optional = roleRepository.findByName(testData.getNotActive().getName());
        assertTrue(optional.isEmpty());
    }

    @Test
    void findNotActiveByName() {
        Optional<Role> optional = roleRepository.findByNameWithNotActive(testData.getNotActive().getName());
        assertTrue(optional.isPresent());
        assertTrue(testData.equals(testData.getNotActive(), optional.get()));
    }

}