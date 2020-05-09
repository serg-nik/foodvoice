package ru.serg_nik.foodvoice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.serg_nik.foodvoice.model.Role;

import java.util.Optional;

@Transactional(readOnly = true)
public interface RoleRepository extends BaseEntityJpaRepository<Role> {

    @Query("SELECT e FROM #{#entityName} e WHERE e.name = :name AND e.status = 1")
    Optional<Role> findByName(@Param("name")String name);

    @Query("SELECT e FROM #{#entityName} e WHERE e.name = :name AND e.status IN (1, 2)")
    Optional<Role> findByNameWithNotActive(@Param("name") String name);

}
