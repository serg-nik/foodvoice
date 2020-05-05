package ru.serg_nik.foodvoice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.serg_nik.foodvoice.model.Role;
import ru.serg_nik.foodvoice.test_data.RoleTestData;

class RoleRepositoryTest extends BaseNamedEntityJpaRepositoryTest<Role> {

    @Autowired
    RoleRepositoryTest(RoleRepository repository) {
        super(repository, new RoleTestData());
    }

}