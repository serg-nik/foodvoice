package ru.serg_nik.foodvoice.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.serg_nik.foodvoice.model.Restaurant;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseEntityJpaRepository<Restaurant> {
}
