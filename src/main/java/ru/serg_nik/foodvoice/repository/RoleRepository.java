package ru.serg_nik.foodvoice.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.serg_nik.foodvoice.model.Role;

@Transactional(readOnly = true)
public interface RoleRepository extends BaseNamedEntityJpaRepository<Role> {
}
