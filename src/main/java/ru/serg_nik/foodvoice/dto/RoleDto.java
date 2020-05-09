package ru.serg_nik.foodvoice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.serg_nik.foodvoice.model.Role;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoleDto extends BaseNamedDto<Role> {

    public RoleDto() {
        super();
    }

    public RoleDto(Role entity) {
        super(entity);
    }

}
