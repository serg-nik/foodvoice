package ru.serg_nik.foodvoice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import ru.serg_nik.foodvoice.BaseTest;
import ru.serg_nik.foodvoice.model.BaseEntity;
import ru.serg_nik.foodvoice.test_data.BaseEntityTestData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static ru.serg_nik.foodvoice.model.Status.DELETED;

abstract class BaseEntityJpaRepositoryTest<T extends BaseEntity> extends BaseTest {

    protected final BaseEntityJpaRepository<T> repository;
    protected final BaseEntityTestData<T> testData;

    BaseEntityJpaRepositoryTest(BaseEntityJpaRepository<T> repository, BaseEntityTestData<T> testData) {
        this.repository = repository;
        this.testData = testData;
    }

    @Test
    void persist() {
        T persisted = repository.save(testData.getNew());
        assertNotNull(persisted);
        Optional<T> found = repository.findById(persisted.getId());
        assertTrue(found.isPresent());
        assertTrue(testData.equals(persisted, found.get()));
    }

    @Test
    void update() {
        T updated = testData.getUpdated();

        Optional<T> beforeUpdate = repository.findById(updated.getId());
        assertTrue(beforeUpdate.isPresent());

        T expected = testData.getEmpty();
        BeanUtils.copyProperties(beforeUpdate.get(), expected);

        repository.save(updated);

        Optional<T> afterUpdate = repository.findById(updated.getId());
        assertTrue(afterUpdate.isPresent());
        assertTrue(testData.equals(updated, afterUpdate.get()));
        assertFalse(testData.equals(expected, afterUpdate.get()));
    }

    @Test
    void findFirstActive() {
        Optional<T> optional = repository.findById(testData.getFirstActive().getId());
        assertTrue(optional.isPresent());
        assertTrue(testData.equals(testData.getFirstActive(), optional.get()));
    }

    @Test
    void findSecondActive() {
        Optional<T> optional = repository.findById(testData.getSecondActive().getId());
        assertTrue(optional.isPresent());
        assertTrue(testData.equals(testData.getSecondActive(), optional.get()));
    }

    @Test
    void findNotActive() {
        Optional<T> optional = repository.findByIdWithNotActive(testData.getNotActive().getId());
        assertTrue(optional.isPresent());
        assertTrue(testData.equals(testData.getNotActive(), optional.get()));
    }

    @Test
    void notFoundNotActive() {
        Optional<T> optional = repository.findById(testData.getNotActive().getId());
        assertTrue(optional.isEmpty());
    }

    @Test
    void notFoundDeleted() {
        Optional<T> optional = repository.findById(testData.getDeleted().getId());
        assertTrue(optional.isEmpty());
    }

    @Test
    void findAllActive() {
        List<T> activeList = repository.findAll();
        assertEquals(testData.getAll().size(), activeList.size());
        assertTrue(testData.getAll().containsAll(activeList));
    }

    @Test
    void findAllNotActive() {
        List<T> notActiveList = repository.findAllWithNotActive();
        assertEquals(testData.getAllWithNotActive().size(), notActiveList.size());
        assertTrue(testData.getAllWithNotActive().containsAll(notActiveList));
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