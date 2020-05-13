package ru.serg_nik.foodvoice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.serg_nik.foodvoice.meta.Meta;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import static ru.serg_nik.foodvoice.model.Status.ACTIVE;

@MappedSuperclass
@Data
@EqualsAndHashCode(exclude = {"created", "updated", "status"})
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator", parameters = {
            @Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy")
    })
    @Column(name = Meta.BaseEntity.ID_COLUMN, updatable = false, nullable = false)
    private UUID id;

    @CreatedDate
    @Column(name = Meta.BaseEntity.CREATED, updatable = false, nullable = false,
            columnDefinition = "SMALLINT DEFAULT now() NOT NULL"
    )
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = Meta.BaseEntity.UPDATED)
    private LocalDateTime updated;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = Meta.BaseEntity.STATUS, insertable = false, nullable = false,
            columnDefinition = "SMALLINT DEFAULT 1 NOT NULL"
    )
    private Status status = ACTIVE;

}
