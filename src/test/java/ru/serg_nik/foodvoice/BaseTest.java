package ru.serg_nik.foodvoice;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public abstract class BaseTest {

    protected <T> void equalsWithSorting(List<T> expected, List<T> actual) {
        assertEquals(expected.size(), actual.size(), "Размеры списков не совпадают");

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i), "Нарушена сортировка списка");
        }
    }

}