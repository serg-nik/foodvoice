package ru.serg_nik.foodvoice.test_data;

import lombok.NoArgsConstructor;
import ru.serg_nik.foodvoice.model.Dish;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static ru.serg_nik.foodvoice.model.Status.ACTIVE;
import static ru.serg_nik.foodvoice.test_data.MenuTestData.*;

@NoArgsConstructor
public final class DishTestData extends BaseNamedEntityTestData<Dish> {

    public static final Dish DISH_1 = new Dish();
    public static final Dish DISH_2 = new Dish();
    public static final Dish DISH_3 = new Dish();
    public static final Dish DISH_4 = new Dish();
    public static final Dish DISH_5 = new Dish();
    public static final Dish DISH_6 = new Dish();
    public static final Dish DISH_7 = new Dish();
    public static final Dish DISH_8 = new Dish();
    public static final Dish DISH_DELETED = new Dish();
    public static final Dish DISH_NOT_ACTIVE = new Dish();
    public static final List<Dish> DISHES = List.of(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, DISH_7, DISH_8);
    public static final List<Dish> DISHES_WITH_NOT_ACTIVE = List.of(
            DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, DISH_7, DISH_8, DISH_NOT_ACTIVE
    );

    static {
        DISH_1.setId(UUID.fromString("a29de34f-82cb-4fc3-a3f4-7fc37398b987"));
        DISH_1.setName("Блины");
        DISH_1.setMenu(MENU_1);
        DISH_1.setPrice(100_00L);
        DISH_2.setId(UUID.fromString("e43f72a2-0ee4-4f1f-9f61-ff3d185438ce"));
        DISH_3.setId(UUID.fromString("3007713f-223d-4d66-8d77-8ce0b9d97147"));
        DISH_4.setId(UUID.fromString("e8cf21e1-4de1-46a3-8f2c-bae6fc87c579"));

        DISH_5.setId(UUID.fromString("61c07aa6-3099-4e33-a29c-493e04f969f3"));
        DISH_5.setName("Цезарь");
        DISH_5.setMenu(MENU_2);
        DISH_5.setPrice(100_00L);
        DISH_6.setId(UUID.fromString("d15c5f85-cfdf-43d1-a648-d9560031e452"));
        DISH_7.setId(UUID.fromString("1e471d70-e992-41d2-a98f-490a9dc916b0"));
        DISH_8.setId(UUID.fromString("46a22c10-d830-456c-b62c-7f8395cfaf3d"));

        DISH_DELETED.setId(UUID.fromString("07a09099-de81-4eb6-886e-18332dbdafa9"));
        DISH_DELETED.setName("DELETED");
        DISH_DELETED.setMenu(MENU_DELETED);
        DISH_DELETED.setPrice(100_00L);

        DISH_NOT_ACTIVE.setId(UUID.fromString("9d75bedd-3f32-43b2-b3aa-62a8b66f2556"));
        DISH_NOT_ACTIVE.setName("NOT_ACTIVE");
        DISH_NOT_ACTIVE.setMenu(MENU_NOT_ACTIVE);
        DISH_NOT_ACTIVE.setPrice(100_00L);
    }

    @Override
    public Dish getEmpty() {
        return new Dish();
    }

    @Override
    public Dish getNew() {
        Dish entity = new Dish();
        entity.setName("New");
        entity.setMenu(MENU_1);
        entity.setPrice(200_00L);
        return entity;
    }

    @Override
    public Dish getUpdated() {
        Dish entity = new Dish();
        entity.setId(DISH_1.getId());
        entity.setName("Updated");
        entity.setStatus(ACTIVE);
        entity.setMenu(MENU_2);
        entity.setPrice(200_00L);
        return entity;
    }

    @Override
    public Dish getFirstActive() {
        return DISH_1;
    }

    @Override
    public Dish getSecondActive() {
        return DISH_5;
    }

    @Override
    public Dish getNotActive() {
        return DISH_NOT_ACTIVE;
    }

    @Override
    public Dish getDeleted() {
        return DISH_DELETED;
    }

    @Override
    public List<Dish> getAll() {
        return DISHES;
    }

    @Override
    public List<Dish> getAllWithNotActive() {
        return DISHES_WITH_NOT_ACTIVE;
    }

    @Override
    public boolean equals(@NotNull Dish a, @NotNull Dish b) {
        return super.equals(a, b)
                && Objects.equals(a.getMenu(), b.getMenu())
                && Objects.equals(a.getPrice(), b.getPrice());
    }

}
