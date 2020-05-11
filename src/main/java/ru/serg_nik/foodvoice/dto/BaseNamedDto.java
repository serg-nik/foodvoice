package ru.serg_nik.foodvoice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.serg_nik.foodvoice.model.BaseNamedEntity;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseNamedDto<E extends BaseNamedEntity> extends BaseDto<E> {

    @ApiModelProperty(position = 2, required = true, example = "name")
    private String name;

    public BaseNamedDto() {
        super();
    }

    public BaseNamedDto(E entity) {
        super(entity);
        this.name = entity.getName();
    }

}
