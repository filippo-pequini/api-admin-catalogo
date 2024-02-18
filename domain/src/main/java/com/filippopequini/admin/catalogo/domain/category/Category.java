package com.filippopequini.admin.catalogo.domain.category;

import com.filippopequini.admin.catalogo.domain.AggregateRoot;
import com.filippopequini.admin.catalogo.domain.validation.ValidationHandler;

import javax.crypto.KeyAgreement;
import java.time.Instant;
import java.util.UUID;


public class Category extends AggregateRoot<CategoryID> {

    private String name;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Category(
            final CategoryID anId,
            final String aName,
            final String aDescription,
            final boolean isActive,
            final Instant aCreateDate,
            final Instant aUpdateDate,
            final Instant aDeleteDate
    ) {
        super(anId);
        this.name = aName;
        this.description = aDescription;
        this.active = isActive;
        this.createdAt = aCreateDate;
        this.updatedAt = aUpdateDate;
        this.deletedAt = aDeleteDate;
    }


    public static Category newCategory(
            final String aName,
            final String aDescription,
            final boolean isActive
    ){
        final var id = CategoryID.unique();
        final var now = Instant.now();
        final var deletedAt = isActive? null : now;
        return new Category(id, aName, aDescription, isActive,now, now, deletedAt);
    }

    public CategoryID getId() {
        return id;
    }

    @Override
    public void validate(ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public Category deactivate(){
        if(getDeletedAt() == null) {
            this.deletedAt = Instant.now();
        }
        this.active = false;
        this.updatedAt = Instant.now();
        return this;
    }

    public Category activate(){
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = Instant.now();
        return this;
    }

    public Category update(
            final String aName,
            final String aDescription,
            final boolean isActive
    ){
        if (isActive) activate();
        else deactivate();

        this.name = aName;
        this.description = aDescription;
        this.updatedAt = Instant.now();
        return this;
    }

}