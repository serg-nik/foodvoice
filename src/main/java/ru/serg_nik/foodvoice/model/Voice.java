package ru.serg_nik.foodvoice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "voice", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_uuid", "date"})})
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"user", "date", "menu"})
@ToString(callSuper = true)
public class Voice extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_uuid", updatable = false, nullable = false)
    private User user;

    @Column(name = "date", insertable = false, updatable = false, nullable = false, columnDefinition = "DATE")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "menu_uuid", nullable = false)
    private Menu menu;

}
