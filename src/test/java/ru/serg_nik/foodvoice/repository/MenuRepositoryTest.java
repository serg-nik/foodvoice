package ru.serg_nik.foodvoice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.serg_nik.foodvoice.model.Menu;
import ru.serg_nik.foodvoice.test_data.MenuTestData;

class MenuRepositoryTest extends BaseEntityJpaRepositoryTest<Menu, MenuRepository, MenuTestData> {

    @Autowired
    MenuRepositoryTest(MenuRepository repository) {
        super(repository, new MenuTestData());
    }

}
