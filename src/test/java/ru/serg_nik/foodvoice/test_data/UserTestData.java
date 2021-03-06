package ru.serg_nik.foodvoice.test_data;

import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.serg_nik.foodvoice.meta.Meta;
import ru.serg_nik.foodvoice.model.User;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static java.util.Collections.singleton;
import static ru.serg_nik.foodvoice.model.Status.*;
import static ru.serg_nik.foodvoice.test_data.RoleTestData.*;
import static ru.serg_nik.foodvoice.test_data.VoiceTestData.*;

@NoArgsConstructor
public final class UserTestData extends BaseNamedEntityTestData<User> {

    public static final User ADMIN = new User();
    public static final User USER = new User();
    public static final User USER_DELETED = new User();
    public static final User USER_NOT_ACTIVE = new User();
    public static final Pageable PAGEABLE = PageRequest.of(
            0, 10, Sort.by(Meta.User.NAME).and(Sort.by(Meta.User.ID_FIELD))
    );
    public static final List<User> USERS = List.of(ADMIN, USER);
    public static final List<User> USERS_WITH_NOT_ACTIVE = List.of(USER_NOT_ACTIVE, ADMIN, USER);
    public static final String NEW_USER_NAME = "New";
    public static final String NEW_USER_PASSWORD = "New password";
    public static final String NEW_USER_EMAIL = "new@foodvoice.ru";

    static {
        ADMIN.setId(UUID.fromString("0151e05d-3185-4304-b9dd-9fc8f97db290"));
        ADMIN.setName("Администратор");
        ADMIN.setStatus(ACTIVE);
        ADMIN.setEmail("admin@foodvoice.ru");
        ADMIN.setPassword("$2a$10$hyWbYTkvbBw7EvI0q9VMyuwvzMfaYtYO1SR01z4fLcPlfLpGpfuzS");
        ADMIN.setRoles(Set.of(ROLE_ADMIN, ROLE_USER));
        ADMIN.setVoices(List.of(VOICE_ADMIN));

        USER.setId(UUID.fromString("f16eb5ea-14bc-42b0-809a-333639464730"));
        USER.setName("Пользователь");
        USER.setStatus(ACTIVE);
        USER.setEmail("user@foodvoice.ru");
        USER.setPassword("$2a$10$hyWbYTkvbBw7EvI0q9VMyuwvzMfaYtYO1SR01z4fLcPlfLpGpfuzS");
        USER.setRoles(Set.of(ROLE_USER));
        USER.setVoices(List.of(VOICE_USER));

        USER_DELETED.setId(UUID.fromString("dfa59084-ff1d-4386-a801-fa6f75ccc2c7"));
        USER_DELETED.setName("DELETED");
        USER_DELETED.setStatus(DELETED);
        USER_DELETED.setEmail("DELETED@foodvoice.ru");
        USER_DELETED.setPassword("$2a$10$hyWbYTkvbBw7EvI0q9VMyuwvzMfaYtYO1SR01z4fLcPlfLpGpfuzS");
        USER_DELETED.setRoles(Set.of(ROLE_DELETED));
        USER_DELETED.setVoices(List.of(VOICE_DELETED));

        USER_NOT_ACTIVE.setId(UUID.fromString("acd6aa59-c7f8-4c0f-9b94-18ecc1381cc5"));
        USER_NOT_ACTIVE.setName("NOT_ACTIVE");
        USER_NOT_ACTIVE.setStatus(NOT_ACTIVE);
        USER_NOT_ACTIVE.setEmail("NOT_ACTIVE@foodvoice.ru");
        USER_NOT_ACTIVE.setPassword("$2a$10$hyWbYTkvbBw7EvI0q9VMyuwvzMfaYtYO1SR01z4fLcPlfLpGpfuzS");
        USER_NOT_ACTIVE.setRoles(Set.of(ROLE_NOT_ACTIVE));
        USER_NOT_ACTIVE.setVoices(List.of(VOICE_NOT_ACTIVE));

    }

    @Override
    public User getEmpty() {
        return new User();
    }

    @Override
    public User getNew() {
        User entity = new User();
        entity.setName(NEW_USER_NAME);
        entity.setEmail(NEW_USER_EMAIL);
        entity.setPassword(NEW_USER_PASSWORD);
        entity.setRoles(singleton(ROLE_USER));
        return entity;
    }

    @Override
    public User getUpdated() {
        User entity = new User();
        entity.setId(ADMIN.getId());
        entity.setName("Updated");
        entity.setStatus(ACTIVE);
        entity.setEmail("updated@foodvoice.ru");
        entity.setPassword("Updated password");
        entity.setRoles(singleton(ROLE_ADMIN));
        return entity;
    }

    @Override
    public User getFirstActive() {
        return ADMIN;
    }

    @Override
    public User getSecondActive() {
        return USER;
    }

    @Override
    public User getNotActive() {
        return USER_NOT_ACTIVE;
    }

    @Override
    public User getDeleted() {
        return USER_DELETED;
    }

    @Override
    public Pageable getPageable() {
        return PAGEABLE;
    }

    @Override
    public List<User> getAll() {
        return USERS;
    }

    @Override
    public List<User> getAllWithNotActive() {
        return USERS_WITH_NOT_ACTIVE;
    }

    @Override
    public boolean equals(@NotNull User a, @NotNull User b) {
        return super.equals(a, b)
                && Objects.equals(a.getEmail(), b.getEmail())
                && Objects.equals(a.getPassword(), b.getPassword());
    }

    public boolean equalsWithRoles(@NotNull User a, @NotNull User b) {
        return equals(a, b)
                && a.getRoles().size() == b.getRoles().size()
                && a.getRoles().containsAll(b.getRoles());
    }

    public boolean equalsWithVoices(@NotNull User a, @NotNull User b) {
        return equals(a, b)
                && a.getVoices().size() == b.getVoices().size()
                && a.getVoices().containsAll(b.getVoices());
    }

}
