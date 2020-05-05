package ru.serg_nik.foodvoice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import ru.serg_nik.foodvoice.model.BaseEntity;

import java.util.Optional;

@NoRepositoryBean
public interface BaseNamedEntityJpaRepository<T extends BaseEntity> extends BaseEntityJpaRepository<T> {

    @Query("SELECT e FROM #{#entityName} e WHERE e.name = :name AND e.status = 1")
    Optional<T> findByName(@Param("name")String name);

    @Query("SELECT e FROM #{#entityName} e WHERE e.name = :name AND e.status IN (1, 2)")
    Optional<T> findByNameWithNotActive(@Param("name") String name);

}
