package ru.serg_nik.foodvoice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.serg_nik.foodvoice.meta.Meta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = Meta.Restaurant.TABLE_NAME,
        uniqueConstraints = {@UniqueConstraint(columnNames = {Meta.Restaurant.NAME, Meta.Restaurant.ADDRESS})}
)
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"email", "address"})
@ToString(callSuper = true)
public class Restaurant extends BaseNamedEntity {

    @Email
    @NotBlank
    @Size(min = 3, max = 254)
    // https://stackoverflow.com/questions/386294/what-is-the-maximum-length-of-a-valid-email-address
    @Column(name = Meta.Restaurant.EMAIL, nullable = false)
    private String email;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = Meta.Restaurant.ADDRESS)
    private String address;

}
