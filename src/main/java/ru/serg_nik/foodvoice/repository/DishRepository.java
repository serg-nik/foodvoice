package ru.serg_nik.foodvoice.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.serg_nik.foodvoice.model.Dish;

@Transactional(readOnly = true)
public interface DishRepository extends BaseNamedEntityJpaRepository<Dish> {


}
