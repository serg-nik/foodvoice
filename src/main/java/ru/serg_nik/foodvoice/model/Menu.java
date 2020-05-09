package ru.serg_nik.foodvoice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.serg_nik.foodvoice.meta.Meta;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = Meta.Menu.TABLE_NAME)
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"restaurant", "actual"})
@ToString(callSuper = true)
public class Menu extends BaseNamedEntity {

    @ManyToOne
    @JoinColumn(name = Meta.Menu.RESTAURANT_COLUMN, nullable = false)
    private Restaurant restaurant;

    @NotNull
    @Column(name = Meta.Menu.ACTUAL)
    private Boolean actual;

}
