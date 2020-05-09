package ru.serg_nik.foodvoice.test_data;

import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.serg_nik.foodvoice.meta.Meta;
import ru.serg_nik.foodvoice.model.Menu;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static ru.serg_nik.foodvoice.model.Status.*;
import static ru.serg_nik.foodvoice.test_data.DishTestData.*;
import static ru.serg_nik.foodvoice.test_data.RestaurantTestData.*;

@NoArgsConstructor
public final class MenuTestData extends BaseNamedEntityTestData<Menu> {

    public static final Menu MENU_1 = new Menu();
    public static final Menu MENU_2 = new Menu();
    public static final Menu MENU_3 = new Menu();
    public static final Menu MENU_4 = new Menu();
    public static final Menu MENU_DELETED = new Menu();
    public static final Menu MENU_NOT_ACTIVE = new Menu();
    public static final Pageable PAGEABLE = PageRequest.of(0, 10, Sort.by(Meta.Menu.NAME));
    public static final List<Menu> MENUS = List.of(MENU_3, MENU_4, MENU_1, MENU_2);
    public static final List<Menu> MENUS_WITH_NOT_ACTIVE = List.of(MENU_NOT_ACTIVE, MENU_3, MENU_4, MENU_1, MENU_2);

    static {
        MENU_1.setId(UUID.fromString("dd0e6ec6-1532-4219-bab4-2e04a31b6a01"));
        MENU_1.setName("Русская кухня");
        MENU_1.setStatus(ACTIVE);
        MENU_1.setRestaurant(RESTAURANT_1);
        MENU_1.setActual(TRUE);
        MENU_1.setDishes(List.of(DISH_1, DISH_4, DISH_2, DISH_3));

        MENU_2.setId(UUID.fromString("fbb9b1a5-a599-4574-954c-27b52a95be2a"));
        MENU_2.setName("Русское меню");
        MENU_2.setStatus(ACTIVE);
        MENU_2.setRestaurant(RESTAURANT_1);
        MENU_2.setActual(FALSE);

        MENU_3.setId(UUID.fromString("62e1e74e-ecd7-4d82-a41a-325f91aeddf1"));
        MENU_3.setName("Европейская кухня");
        MENU_3.setStatus(ACTIVE);
        MENU_3.setRestaurant(RESTAURANT_2);
        MENU_3.setActual(TRUE);
        MENU_3.setDishes(List.of(DISH_8, DISH_6, DISH_7, DISH_5));

        MENU_4.setId(UUID.fromString("d66a3d54-85b0-4e51-b2f8-372963bbacf7"));
        MENU_4.setName("Европейское меню");
        MENU_4.setStatus(ACTIVE);
        MENU_4.setRestaurant(RESTAURANT_2);
        MENU_4.setActual(FALSE);

        MENU_DELETED.setId(UUID.fromString("7df4e03b-3058-4470-b5df-6ccff74effb6"));
        MENU_DELETED.setName("DELETED");
        MENU_DELETED.setStatus(DELETED);
        MENU_DELETED.setRestaurant(RESTAURANT_DELETED);
        MENU_DELETED.setActual(TRUE);

        MENU_NOT_ACTIVE.setId(UUID.fromString("5d50156e-aec3-4c78-8d34-359a4c909614"));
        MENU_NOT_ACTIVE.setName("NOT_ACTIVE");
        MENU_NOT_ACTIVE.setStatus(NOT_ACTIVE);
        MENU_NOT_ACTIVE.setRestaurant(RESTAURANT_NOT_ACTIVE);
        MENU_NOT_ACTIVE.setActual(TRUE);
    }

    @Override
    public Menu getEmpty() {
        return new Menu();
    }

    @Override
    public Menu getNew() {
        Menu entity = new Menu();
        entity.setName("New");
        entity.setRestaurant(RESTAURANT_1);
        entity.setActual(TRUE);
        entity.setDishes(List.of(new DishTestData().getNew()));
        return entity;
    }

    @Override
    public Menu getUpdated() {
        Menu entity = new Menu();
        entity.setId(MENU_1.getId());
        entity.setName("Updated");
        entity.setStatus(ACTIVE);
        entity.setRestaurant(RESTAURANT_2);
        entity.setActual(FALSE);
        return entity;
    }

    @Override
    public Menu getFirstActive() {
        return MENU_1;
    }

    @Override
    public Menu getSecondActive() {
        return MENU_3;
    }

    @Override
    public Menu getNotActive() {
        return MENU_NOT_ACTIVE;
    }

    @Override
    public Menu getDeleted() {
        return MENU_DELETED;
    }

    @Override
    public Pageable getPageable() {
        return PAGEABLE;
    }

    @Override
    public List<Menu> getAll() {
        return MENUS;
    }

    @Override
    public List<Menu> getAllWithNotActive() {
        return MENUS_WITH_NOT_ACTIVE;
    }

    @Override
    public boolean equals(@NotNull Menu a, @NotNull Menu b) {
        return super.equals(a, b)
                && Objects.equals(a.getRestaurant(), b.getRestaurant())
                && a.getActual() == b.getActual();
    }

}
