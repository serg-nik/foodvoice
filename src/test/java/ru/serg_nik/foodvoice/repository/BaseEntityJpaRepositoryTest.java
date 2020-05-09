package ru.serg_nik.foodvoice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import ru.serg_nik.foodvoice.BaseTest;
import ru.serg_nik.foodvoice.model.BaseEntity;
import ru.serg_nik.foodvoice.test_data.BaseEntityTestData;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static ru.serg_nik.foodvoice.model.Status.DELETED;

abstract class BaseEntityJpaRepositoryTest
        <E extends BaseEntity, R extends BaseEntityJpaRepository<E>, T extends BaseEntityTestData<E>> extends BaseTest {

    protected final R repository;
    protected final T testData;

    BaseEntityJpaRepositoryTest(R repository, T testData) {
        this.repository = repository;
        this.testData = testData;
    }

    @Test
    void persist() {
        E persisted = repository.save(testData.getNew());
        assertNotNull(persisted);
        Optional<E> found = repository.findById(persisted.getId());
        assertTrue(found.isPresent());
        assertTrue(testData.equals(persisted, found.get()));
    }

    @Test
    void update() {
        E updated = testData.getUpdated();

        Optional<E> beforeUpdate = repository.findById(updated.getId());
        assertTrue(beforeUpdate.isPresent());

        E expected = testData.getEmpty();
        BeanUtils.copyProperties(beforeUpdate.get(), expected);

        repository.save(updated);

        Optional<E> afterUpdate = repository.findById(updated.getId());
        assertTrue(afterUpdate.isPresent());
        assertTrue(testData.equals(updated, afterUpdate.get()));
        assertFalse(testData.equals(expected, afterUpdate.get()));
    }

    @Test
    void findFirstActive() {
        Optional<E> optional = repository.findById(testData.getFirstActive().getId());
        assertTrue(optional.isPresent());
        assertTrue(testData.equals(testData.getFirstActive(), optional.get()));
    }

    @Test
    void findSecondActive() {
        Optional<E> optional = repository.findById(testData.getSecondActive().getId());
        assertTrue(optional.isPresent());
        assertTrue(testData.equals(testData.getSecondActive(), optional.get()));
    }

    @Test
    void findNotActive() {
        Optional<E> optional = repository.findByIdWithNotActive(testData.getNotActive().getId());
        assertTrue(optional.isPresent());
        assertTrue(testData.equals(testData.getNotActive(), optional.get()));
    }

    @Test
    void notFoundNotActive() {
        Optional<E> optional = repository.findById(testData.getNotActive().getId());
        assertTrue(optional.isEmpty());
    }

    @Test
    void notFoundDeleted() {
        Optional<E> optional = repository.findById(testData.getDeleted().getId());
        assertTrue(optional.isEmpty());
    }

    @Test
    void findAllActive() {
        Page<E> page = repository.findAll(testData.getPageable());
        equalsWithSorting(testData.getAll(), page.getContent());
    }

    @Test
    void findAllNotActive() {
        Page<E> page = repository.findAllWithNotActive(testData.getPageable());
        equalsWithSorting(testData.getAllWithNotActive(), page.getContent());
    }

    @Test
    void notFoundAnyDeleted() {
        assertTrue(repository.findAll().stream().noneMatch(e -> e.getStatus().equals(DELETED)));
    }

    @Test
    void deleteById() {
        UUID id = testData.getFirstActive().getId();
        assertTrue(repository.findById(id).isPresent());
        repository.deleteById(id);
        assertTrue(repository.findById(id).isEmpty());
    }

    @Test
    void deleteAll() {
        assertFalse(repository.findAll().isEmpty());
        repository.deleteAll();
        assertTrue(repository.findAll().isEmpty());
    }

}