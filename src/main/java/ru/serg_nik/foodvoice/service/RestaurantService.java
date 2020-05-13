package ru.serg_nik.foodvoice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.serg_nik.foodvoice.dto.RestaurantDto;
import ru.serg_nik.foodvoice.model.Restaurant;
import ru.serg_nik.foodvoice.repository.RestaurantRepository;

import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class RestaurantService extends BaseEntityService<Restaurant, RestaurantRepository> {

    private static final String RESTAURANTS_CACHE_NAME = "restaurantsWithActualMenus";

    private final MenuService menuService;

    @Autowired
    public RestaurantService(RestaurantRepository repository, MenuService menuService) {
        super(repository);
        this.menuService = menuService;
    }

    public Restaurant entityOf(RestaurantDto dto) {
        Restaurant entity = new Restaurant();
        BeanUtils.copyProperties(dto, entity, "menus");
        entity.setMenus(
                dto.getMenus().stream()
                        .map(menuDto -> menuService.entityOf(menuDto, entity))
                        .collect(toList())
        );
        return entity;
    }

    @Override
    @CacheEvict(value = RESTAURANTS_CACHE_NAME, allEntries = true)
    public Restaurant create(Restaurant entity) {
        return super.create(entity);
    }

    @Override
    @CacheEvict(value = RESTAURANTS_CACHE_NAME, allEntries = true)
    public Restaurant update(UUID id, Restaurant entity) {
        return super.update(id, entity);
    }

    @Override
    @CacheEvict(value = RESTAURANTS_CACHE_NAME, allEntries = true)
    public void delete(UUID id) {
        super.delete(id);
    }

    @Cacheable(RESTAURANTS_CACHE_NAME)
    public Page<Restaurant> getAllWithActualMenus(Pageable pageable) {
        Page<Restaurant> page = repository.findAllWithActualMenus(pageable);
        log.info("Найдено {} ресторана(ов) с меню дня", page.getTotalElements());
        return page;
    }

}
