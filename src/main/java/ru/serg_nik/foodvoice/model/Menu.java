package ru.serg_nik.foodvoice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.serg_nik.foodvoice.meta.Meta;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = Meta.Menu.TABLE_NAME)
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"restaurant", "actual", "dishes"})
@ToString(callSuper = true, exclude = {"dishes"})
public class Menu extends BaseNamedEntity {

    @ManyToOne
    @JoinColumn(name = Meta.Menu.RESTAURANT_COLUMN, nullable = false)
    private Restaurant restaurant;

    @NotNull
    @Column(name = Meta.Menu.ACTUAL)
    private Boolean actual = FALSE;

    @OneToMany(mappedBy = "menu", fetch = LAZY, cascade = {PERSIST, MERGE, REFRESH})
    @OrderBy("name")
    private List<Dish> dishes = List.of();

}
