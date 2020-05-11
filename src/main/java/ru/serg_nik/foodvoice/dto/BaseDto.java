package ru.serg_nik.foodvoice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.serg_nik.foodvoice.model.BaseEntity;
import ru.serg_nik.foodvoice.model.Status;

import java.io.Serializable;
import java.util.UUID;

import static ru.serg_nik.foodvoice.model.Status.ACTIVE;

@NoArgsConstructor
@Data
public abstract class BaseDto<E extends BaseEntity> implements Serializable {

    private UUID id;
    @ApiModelProperty(position = 2, required = true, example = "1", value = "sdfsdfs")
    private Status status = ACTIVE;

    public BaseDto(E entity) {
        this.id = entity.getId();
        this.status = entity.getStatus();
    }

    @ApiModelProperty(position = 1, hidden = true)
    public void setId(UUID id) {
        this.id = id;
    }

}
