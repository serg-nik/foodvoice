package ru.serg_nik.foodvoice.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.serg_nik.foodvoice.model.Menu;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseNamedEntityJpaRepository<Menu> {
}
