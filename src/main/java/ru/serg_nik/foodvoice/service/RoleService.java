package ru.serg_nik.foodvoice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.serg_nik.foodvoice.dto.RoleDto;
import ru.serg_nik.foodvoice.model.Role;
import ru.serg_nik.foodvoice.repository.RoleRepository;

@Service
@Slf4j
public class RoleService extends BaseEntityService<Role, RoleRepository> {

    @Autowired
    public RoleService(RoleRepository repository) {
        super(repository);
    }

    public static Role entityOf(RoleDto dto) {
        Role entity = new Role();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

}
