package ru.serg_nik.foodvoice.repository;

import org.junit.jupiter.api.Test;
import ru.serg_nik.foodvoice.model.BaseNamedEntity;
import ru.serg_nik.foodvoice.test_data.BaseNamedEntityTestData;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

abstract class BaseNamedEntityJpaRepositoryTest<T extends BaseNamedEntity> extends BaseEntityJpaRepositoryTest<T> {

    private final BaseNamedEntityJpaRepository<T> namedEntityJpaRepository;

    BaseNamedEntityJpaRepositoryTest(BaseNamedEntityJpaRepository<T> repository, BaseNamedEntityTestData<T> testData) {
        super(repository, testData);
        this.namedEntityJpaRepository = repository;
    }

    @Test
    void findFirstActiveByName() {
        Optional<T> optional = namedEntityJpaRepository.findByName(testData.getFirstActive().getName());
        assertTrue(optional.isPresent());
        assertTrue(testData.equals(testData.getFirstActive(), optional.get()));
    }

    @Test
    void findSecondActiveByName() {
        Optional<T> optional = namedEntityJpaRepository.findByName(testData.getSecondActive().getName());
        assertTrue(optional.isPresent());
        assertTrue(testData.equals(testData.getSecondActive(), optional.get()));
    }

    @Test
    void notFoundNotActiveByName() {
        Optional<T> optional = namedEntityJpaRepository.findByName(testData.getNotActive().getName());
        assertTrue(optional.isEmpty());
    }

    @Test
    void findNotActiveByName() {
        Optional<T> optional = namedEntityJpaRepository.findByNameWithNotActive(testData.getNotActive().getName());
        assertTrue(optional.isPresent());
        assertTrue(testData.equals(testData.getNotActive(), optional.get()));
    }

}