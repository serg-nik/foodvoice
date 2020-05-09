package ru.serg_nik.foodvoice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.serg_nik.foodvoice.meta.Meta;
import ru.serg_nik.foodvoice.model.Menu;
import ru.serg_nik.foodvoice.test_data.MenuTestData;

class MenuRepositoryTest extends BaseEntityJpaRepositoryTest<Menu> {

    @Autowired
    MenuRepositoryTest(MenuRepository repository) {
        super(repository, new MenuTestData(), PageRequest.of(0, 10, Sort.by(Meta.Menu.NAME)));
    }

}
