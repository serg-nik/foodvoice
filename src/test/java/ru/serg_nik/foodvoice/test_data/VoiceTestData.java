package ru.serg_nik.foodvoice.test_data;

import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.serg_nik.foodvoice.meta.Meta;
import ru.serg_nik.foodvoice.model.Voice;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static ru.serg_nik.foodvoice.model.Status.*;
import static ru.serg_nik.foodvoice.test_data.MenuTestData.*;
import static ru.serg_nik.foodvoice.test_data.UserTestData.*;

@NoArgsConstructor
public final class VoiceTestData extends BaseEntityTestData<Voice> {

    public static final Voice VOICE_ADMIN = new Voice();
    public static final Voice VOICE_USER = new Voice();
    public static final Voice VOICE_DELETED = new Voice();
    public static final Voice VOICE_NOT_ACTIVE = new Voice();
    public static final Pageable PAGEABLE = PageRequest.of(0, 10, Sort.by(Meta.Voice.DATE).descending());
    public static final List<Voice> VOICES = List.of(VOICE_USER, VOICE_ADMIN);
    public static final List<Voice> VOICES_WITH_NOT_ACTIVE = List.of(VOICE_USER, VOICE_ADMIN, VOICE_NOT_ACTIVE);

    static {
        VOICE_ADMIN.setId(UUID.fromString("46acb07f-a6ca-42c0-9c7c-44a3e3aad71b"));
        VOICE_ADMIN.setStatus(ACTIVE);
        VOICE_ADMIN.setUser(UserTestData.ADMIN);
        VOICE_ADMIN.setDate(LocalDate.parse("2020-05-08"));
        VOICE_ADMIN.setMenu(MenuTestData.MENU_1);

        VOICE_USER.setId(UUID.fromString("00813cff-8e50-4a74-9c85-bbbf90d026ae"));
        VOICE_USER.setStatus(ACTIVE);
        VOICE_USER.setUser(USER);
        VOICE_USER.setDate(LocalDate.parse("2020-05-09"));
        VOICE_USER.setMenu(MENU_2);

        VOICE_DELETED.setId(UUID.fromString("db248cbb-7c6a-4fd9-9424-2678d6db4a1c"));
        VOICE_DELETED.setStatus(DELETED);
        VOICE_DELETED.setUser(USER_DELETED);
        VOICE_DELETED.setDate(LocalDate.parse("2020-05-05"));
        VOICE_DELETED.setMenu(MENU_DELETED);

        VOICE_NOT_ACTIVE.setId(UUID.fromString("a723c2c0-38a2-46fd-b283-0a2be67a7417"));
        VOICE_NOT_ACTIVE.setStatus(NOT_ACTIVE);
        VOICE_NOT_ACTIVE.setUser(USER_NOT_ACTIVE);
        VOICE_NOT_ACTIVE.setDate(LocalDate.parse("2020-05-05"));
        VOICE_NOT_ACTIVE.setMenu(MENU_NOT_ACTIVE);
    }

    @Override
    public Voice getEmpty() {
        return new Voice();
    }

    @Override
    public Voice getNew() {
        Voice entity = new Voice();
        entity.setUser(ADMIN);
        entity.setMenu(MENU_2);
        return entity;
    }

    @Override
    public Voice getUpdated() {
        Voice entity = new Voice();
        entity.setId(VOICE_ADMIN.getId());
        entity.setStatus(ACTIVE);
        entity.setMenu(MENU_2);
        return entity;
    }

    @Override
    public Voice getFirstActive() {
        return VOICE_ADMIN;
    }

    @Override
    public Voice getSecondActive() {
        return VOICE_USER;
    }

    @Override
    public Voice getNotActive() {
        return VOICE_NOT_ACTIVE;
    }

    @Override
    public Voice getDeleted() {
        return VOICE_DELETED;
    }

    @Override
    public Pageable getPageable() {
        return PAGEABLE;
    }

    @Override
    public List<Voice> getAll() {
        return VOICES;
    }

    @Override
    public List<Voice> getAllWithNotActive() {
        return VOICES_WITH_NOT_ACTIVE;
    }

    @Override
    public boolean equals(@NotNull Voice a, @NotNull Voice b) {
        return super.equals(a, b)
                && Objects.equals(a.getDate(), b.getDate());
    }

}
