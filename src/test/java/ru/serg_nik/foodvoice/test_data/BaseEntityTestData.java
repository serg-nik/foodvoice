package ru.serg_nik.foodvoice.test_data;

import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import ru.serg_nik.foodvoice.model.BaseEntity;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
public abstract class BaseEntityTestData<T extends BaseEntity> {

    public abstract T getEmpty();

    public abstract T getNew();

    public abstract T getUpdated();

    public abstract T getFirstActive();

    public abstract T getSecondActive();

    public abstract T getNotActive();

    public abstract T getDeleted();

    public abstract Pageable getPageable();

    public abstract List<T> getAll();

    public abstract List<T> getAllWithNotActive();

    public boolean equals(@NotNull T a, @NotNull T b) {
        return Objects.equals(a, b)
                && Objects.equals(a.getStatus(), b.getStatus());
    }

}
