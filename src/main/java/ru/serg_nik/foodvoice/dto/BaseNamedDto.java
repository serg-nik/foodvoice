package ru.serg_nik.foodvoice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.serg_nik.foodvoice.model.BaseNamedEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseNamedDto<E extends BaseNamedEntity> extends BaseDto<E> {

    private String name;

    public BaseNamedDto() {
        super();
    }

    public BaseNamedDto(E entity) {
        super(entity);
        this.name = entity.getName();
    }

}
