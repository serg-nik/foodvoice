package ru.serg_nik.foodvoice.test_data;

import lombok.NoArgsConstructor;
import ru.serg_nik.foodvoice.model.Role;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

import static ru.serg_nik.foodvoice.model.Status.ACTIVE;

@NoArgsConstructor
public final class RoleTestData extends BaseNamedEntityTestData<Role> {

    public static final Role ROLE_ADMIN = new Role();
    public static final Role ROLE_USER = new Role();
    public static final Role ROLE_DELETED = new Role();
    public static final Role ROLE_NOT_ACTIVE = new Role();
    public static final List<Role> ROLES = List.of(ROLE_ADMIN, ROLE_USER);
    public static final List<Role> ROLES_WITH_NOT_ACTIVE = List.of(ROLE_ADMIN, ROLE_USER, ROLE_NOT_ACTIVE);

    static {
        ROLE_ADMIN.setId(UUID.fromString("2a9cf406-7f34-4cc7-a4d0-d4f12056a9d9"));
        ROLE_ADMIN.setName("ADMIN");

        ROLE_USER.setId(UUID.fromString("1c99e5d3-0bc3-4607-b867-1cde3ce160ce"));
        ROLE_USER.setName("USER");

        ROLE_DELETED.setId(UUID.fromString("9876f5b5-f342-4045-8092-6896fbbf0a09"));
        ROLE_DELETED.setName("DELETED");

        ROLE_NOT_ACTIVE.setId(UUID.fromString("6eb53eb7-df90-4c3e-8b99-7019174cc06b"));
        ROLE_NOT_ACTIVE.setName("NOT_ACTIVE");
    }

    @Override
    public Role getEmpty() {
        return new Role();
    }

    @Override
    public Role getNew() {
        Role entity = new Role();
        entity.setName("New");
        return entity;
    }

    @Override
    public Role getUpdated() {
        Role entity = new Role();
        entity.setId(ROLE_ADMIN.getId());
        entity.setName("Updated");
        entity.setStatus(ACTIVE);
        return entity;
    }

    @Override
    public Role getFirstActive() {
        return ROLE_ADMIN;
    }

    @Override
    public Role getSecondActive() {
        return ROLE_USER;
    }

    @Override
    public Role getNotActive() {
        return ROLE_NOT_ACTIVE;
    }

    @Override
    public Role getDeleted() {
        return ROLE_DELETED;
    }

    @Override
    public List<Role> getAll() {
        return ROLES;
    }

    @Override
    public List<Role> getAllWithNotActive() {
        return ROLES_WITH_NOT_ACTIVE;
    }

    @Override
    public boolean equals(@NotNull Role a, @NotNull Role b) {
        return super.equals(a, b);
    }

}
