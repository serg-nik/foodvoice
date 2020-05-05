package ru.serg_nik.foodvoice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "menu")
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"restaurant", "actual"})
@ToString(callSuper = true)
public class Menu extends BaseNamedEntity {

    @ManyToOne
    @JoinColumn(name = "restaurant_uuid", nullable = false)
    private Restaurant restaurant;

    @NotNull
    @Column(name = "actual")
    private Boolean actual;

}
