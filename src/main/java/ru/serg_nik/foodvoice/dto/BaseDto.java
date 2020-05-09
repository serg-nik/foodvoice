package ru.serg_nik.foodvoice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.serg_nik.foodvoice.model.BaseEntity;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public abstract class BaseDto<E extends BaseEntity> implements Serializable {

    private UUID id;

    public BaseDto(E entity) {
        this.id = entity.getId();
    }

}
