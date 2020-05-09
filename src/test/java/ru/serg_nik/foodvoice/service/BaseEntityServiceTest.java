package ru.serg_nik.foodvoice.service;

import org.junit.jupiter.api.Test;
import ru.serg_nik.foodvoice.BaseTest;
import ru.serg_nik.foodvoice.model.BaseEntity;
import ru.serg_nik.foodvoice.test_data.BaseEntityTestData;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

abstract class BaseEntityServiceTest
        <E extends BaseEntity, S extends BaseEntityService<E, ?>, T extends BaseEntityTestData<E>> extends BaseTest {

    protected final S service;
    protected final T testData;

    public BaseEntityServiceTest(S service, T testData) {
        this.service = service;
        this.testData = testData;
    }

    @Test
    void get() {
        E expected = testData.getFirstActive();
        E actual = service.get(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    void getWithThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.get(null));
        assertThrows(EntityNotFoundException.class, () -> service.get(UUID.randomUUID()));
    }

    @Test
    void create() {
        createNewEntity();
    }

    protected void createNewEntity() {
        E actual = service.create(testData.getNew());
        assertNotNull(actual);
        assertDoesNotThrow(() -> service.get(actual.getId()));
        E expected = testData.getNew();
        updateNewExpectedEntity(expected, actual);
        assertTrue(testData.equals(expected, actual));
    }

    protected void updateNewExpectedEntity(E expected, E actual) {
        expected.setId(actual.getId());
    }

    @Test
    void createWithThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.create(null));
        E newEntityWithId = testData.getNew();
        newEntityWithId.setId(UUID.randomUUID());
        assertThrows(IllegalArgumentException.class, () -> service.create(newEntityWithId));
    }

    @Test
    void delete() {
        UUID id = testData.getFirstActive().getId();
        assertDoesNotThrow(() -> service.get(id));
        service.delete(id);
        assertThrows(EntityNotFoundException.class, () -> service.get(id));
    }

    @Test
    void deleteWithThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.delete(null));
    }

}