package com.atamie.mark.bookstore.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class SupperEntity {


    @Column(name = "created_date", nullable = false)
    private Instant createdDate;
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}
