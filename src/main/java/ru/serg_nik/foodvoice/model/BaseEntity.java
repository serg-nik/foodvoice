package ru.serg_nik.foodvoice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

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
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID id;

    @CreatedDate
    @Column(name = "created", updatable = false, nullable = false)
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "updated")
    private LocalDateTime updated;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", insertable = false, nullable = false, columnDefinition = "SMALLINT DEFAULT 1 NOT NULL")
    private Status status;

}
