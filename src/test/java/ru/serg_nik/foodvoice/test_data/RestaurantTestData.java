package ru.serg_nik.foodvoice.test_data;

import lombok.NoArgsConstructor;
import ru.serg_nik.foodvoice.model.Restaurant;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static ru.serg_nik.foodvoice.model.Status.ACTIVE;

@NoArgsConstructor
public final class RestaurantTestData extends BaseNamedEntityTestData<Restaurant> {

    public static final Restaurant RESTAURANT_1 = new Restaurant();
    public static final Restaurant RESTAURANT_2 = new Restaurant();
    public static final Restaurant RESTAURANT_3 = new Restaurant();
    public static final Restaurant RESTAURANT_DELETED = new Restaurant();
    public static final Restaurant RESTAURANT_NOT_ACTIVE = new Restaurant();
    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT_3, RESTAURANT_2, RESTAURANT_1);
    public static final List<Restaurant> RESTAURANTS_WITH_NOT_ACTIVE = List.of(
            RESTAURANT_NOT_ACTIVE, RESTAURANT_3, RESTAURANT_2, RESTAURANT_1
    );

    static {
        RESTAURANT_1.setId(UUID.fromString("58b3d8b0-7d08-4274-8aa8-68976d0582ee"));
        RESTAURANT_1.setName("Месье Колбасье");
        RESTAURANT_1.setAddress("Город, Улица, дом");
        RESTAURANT_1.setEmail("monsieur_sausage@foodvoice.ru");

        RESTAURANT_2.setId(UUID.fromString("01f8e5df-ac2e-4898-b72c-ac62123c21ed"));
        RESTAURANT_2.setName("Звери как звери");
        RESTAURANT_2.setAddress("Город, Улица, дом");
        RESTAURANT_2.setEmail("animals_like_animals@foodvoice.ru");

        RESTAURANT_3.setId(UUID.fromString("7c34d5b3-634f-4b75-abf3-49a40fcc18ec"));
        RESTAURANT_3.setName("Звери как звери");
        RESTAURANT_3.setAddress("Адрес №2");
        RESTAURANT_3.setEmail("animals_like_animals@foodvoice.ru");

        RESTAURANT_DELETED.setId(UUID.fromString("8bad8470-06bb-4b15-b98b-95745152875b"));
        RESTAURANT_DELETED.setName("DELETED");
        RESTAURANT_DELETED.setAddress("DELETED");
        RESTAURANT_DELETED.setEmail("DELETED@foodvoice.ru");

        RESTAURANT_NOT_ACTIVE.setId(UUID.fromString("f18d3bcc-492d-4e14-8442-dcf5481d5fae"));
        RESTAURANT_NOT_ACTIVE.setName("NOT_ACTIVE");
        RESTAURANT_NOT_ACTIVE.setAddress("NOT_ACTIVE");
        RESTAURANT_NOT_ACTIVE.setEmail("NOT_ACTIVE@foodvoice.ru");
    }

    @Override
    public Restaurant getEmpty() {
        return new Restaurant();
    }

    @Override
    public Restaurant getNew() {
        Restaurant entity = new Restaurant();
        entity.setName("New");
        entity.setAddress("New address");
        entity.setEmail("new@foodvoice.ru");
        return entity;
    }

    @Override
    public Restaurant getUpdated() {
        Restaurant entity = new Restaurant();
        entity.setId(RESTAURANT_1.getId());
        entity.setName("Updated");
        entity.setStatus(ACTIVE);
        entity.setAddress("Updated address");
        entity.setEmail("updated@foodvoice.ru");
        return entity;
    }

    @Override
    public Restaurant getFirstActive() {
        return RESTAURANT_1;
    }

    @Override
    public Restaurant getSecondActive() {
        return RESTAURANT_2;
    }

    @Override
    public Restaurant getNotActive() {
        return RESTAURANT_NOT_ACTIVE;
    }

    @Override
    public Restaurant getDeleted() {
        return RESTAURANT_DELETED;
    }

    @Override
    public List<Restaurant> getAll() {
        return RESTAURANTS;
    }

    @Override
    public List<Restaurant> getAllWithNotActive() {
        return RESTAURANTS_WITH_NOT_ACTIVE;
    }

    @Override
    public boolean equals(@NotNull Restaurant a, @NotNull Restaurant b) {
        return super.equals(a, b)
                && Objects.equals(a.getAddress(), b.getAddress())
                && Objects.equals(a.getEmail(), b.getEmail());
    }

}
