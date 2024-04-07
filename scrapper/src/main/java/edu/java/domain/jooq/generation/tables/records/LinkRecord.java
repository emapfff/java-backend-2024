/*
 * This file is generated by jOOQ.
 */

package edu.java.domain.jooq.generation.tables.records;

import edu.java.domain.jooq.generation.tables.pojos.Link;
import jakarta.validation.constraints.Size;
import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.18.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class LinkRecord extends UpdatableRecordImpl<LinkRecord> implements Record3<Long, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Create a detached LinkRecord
     */
    public LinkRecord() {
        super(edu.java.domain.jooq.generation.tables.Link.LINK);
    }

    /**
     * Getter for <code>LINK.ID</code>.
     */
    @Nullable
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Create a detached, initialised LinkRecord
     */
    @ConstructorProperties({"id", "url", "lastUpdate"})
    public LinkRecord(@Nullable Long id, @NotNull String url, @NotNull LocalDateTime lastUpdate) {
        super(edu.java.domain.jooq.generation.tables.Link.LINK);

        setId(id);
        setUrl(url);
        setLastUpdate(lastUpdate);
        resetChangedOnNotNull();
    }

    /**
     * Getter for <code>LINK.URL</code>.
     */
    @jakarta.validation.constraints.NotNull
    @Size(max = 1000000000)
    @NotNull
    public String getUrl() {
        return (String) get(1);
    }

    /**
     * Create a detached, initialised LinkRecord
     */
    public LinkRecord(Link value) {
        super(edu.java.domain.jooq.generation.tables.Link.LINK);

        if (value != null) {
            setId(value.getId());
            setUrl(value.getUrl());
            setLastUpdate(value.getLastUpdate());
            resetChangedOnNotNull();
        }
    }

    /**
     * Getter for <code>LINK.LAST_UPDATE</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public LocalDateTime getLastUpdate() {
        return (LocalDateTime) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row3<Long, String, LocalDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    @NotNull
    public Row3<Long, String, LocalDateTime> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * Setter for <code>LINK.ID</code>.
     */
    public void setId(@Nullable Long value) {
        set(0, value);
    }

    /**
     * Setter for <code>LINK.URL</code>.
     */
    public void setUrl(@NotNull String value) {
        set(1, value);
    }

    /**
     * Setter for <code>LINK.LAST_UPDATE</code>.
     */
    public void setLastUpdate(@NotNull LocalDateTime value) {
        set(2, value);
    }

    @Override
    @Nullable
    public Long component1() {
        return getId();
    }

    @Override
    @NotNull
    public String component2() {
        return getUrl();
    }

    @Override
    @NotNull
    public LocalDateTime component3() {
        return getLastUpdate();
    }

    @Override
    @Nullable
    public Long value1() {
        return getId();
    }

    @Override
    @NotNull
    public String value2() {
        return getUrl();
    }

    @Override
    @NotNull
    public LocalDateTime value3() {
        return getLastUpdate();
    }

    @Override
    @NotNull
    public LinkRecord value1(@Nullable Long value) {
        setId(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord value2(@NotNull String value) {
        setUrl(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord value3(@NotNull LocalDateTime value) {
        setLastUpdate(value);
        return this;
    }

    @Override
    @NotNull
    public LinkRecord values(@Nullable Long value1, @NotNull String value2, @NotNull LocalDateTime value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Field<Long> field1() {
        return edu.java.domain.jooq.generation.tables.Link.LINK.ID;
    }

    @Override
    @NotNull
    public Field<String> field2() {
        return edu.java.domain.jooq.generation.tables.Link.LINK.URL;
    }

    @Override
    @NotNull
    public Field<LocalDateTime> field3() {
        return edu.java.domain.jooq.generation.tables.Link.LINK.LAST_UPDATE;
    }
}