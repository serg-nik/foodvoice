package ru.serg_nik.foodvoice.test_data;

import lombok.NoArgsConstructor;
import ru.serg_nik.foodvoice.model.BaseNamedEntity;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@NoArgsConstructor
public abstract class BaseNamedEntityTestData<T extends BaseNamedEntity> extends BaseEntityTestData<T> {

    @Override
    public boolean equals(@NotNull T a, @NotNull T b) {
        return super.equals(a, b) && Objects.equals(a.getName(), b.getName());
    }

}
