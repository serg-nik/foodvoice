package ru.serg_nik.foodvoice.service;

import lombok.extern.slf4j.Slf4j;
import ru.serg_nik.foodvoice.model.BaseEntity;
import ru.serg_nik.foodvoice.repository.BaseEntityJpaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.util.Assert.isNull;
import static org.springframework.util.Assert.notNull;

@Slf4j
public abstract class BaseEntityService<E extends BaseEntity, R extends BaseEntityJpaRepository<E>> {

    protected final R repository;

    protected BaseEntityService(R repository) {
        this.repository = repository;
    }

    private void checkId(UUID id) {
        notNull(id, "Параметр \"id\" не может быть пустым (null)");
    }

    private void checkIsPresent(E entity) {
        notNull(entity, "Сущность не может быть пустой (null)");
    }

    public E get(UUID id) {
        log.info("Поиск сущности по id [{}]", id);
        checkId(id);
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Сущность c id [%s] не найдена", id)));
    }

    protected void prepareBeforeCreate(E entity) {
        checkIsPresent(entity);
        isNull(entity.getId(), String.format("У новой сущности не может быть id: [%s]", entity));
    }

    public E create(E entity) {
        log.info("Создание сущности [{}]", entity);
        prepareBeforeCreate(entity);
        entity = repository.save(entity);
        log.info("Создана новая сущность с типом [{}] и id [{}]", entity.getClass().getName(), entity.getId());
        return entity;
    }

    protected void prepareBeforeUpdate(UUID id, E entity) {
        checkId(id);
        checkIsPresent(entity);
        if (Objects.isNull(entity.getId())) {
            entity.setId(id);
        } else if (!id.equals(entity.getId())) {
            throw new IllegalArgumentException(String.format(
                    "id обновляемого объекта [%s] отличается от id [%s] переданного объекта", id, entity.getId()
            ));
        }
    }

    public E update(UUID id, E entity) {
        log.info("Обновление сущности с типом [{}] и id [{}]", entity.getClass().getName(), entity.getId());
        prepareBeforeUpdate(id, entity);
        entity = repository.save(entity);
        return entity;
    }

    public void delete(UUID id) {
        log.info("Удаление сущности с id [{}]", id);
        checkId(id);
        repository.deleteById(id);
        log.info("Произведено мягкое удаление сущности с id [{}]", id);
    }

}
