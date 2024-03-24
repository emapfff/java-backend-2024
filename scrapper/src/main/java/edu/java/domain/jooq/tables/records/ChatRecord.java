/*
 * This file is generated by jOOQ.
 */

package edu.java.domain.jooq.tables.records;

import edu.java.domain.jooq.tables.Chat;
import java.beans.ConstructorProperties;
import javax.annotation.processing.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
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
public class ChatRecord extends UpdatableRecordImpl<ChatRecord> implements Record2<Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Create a detached ChatRecord
     */
    public ChatRecord() {
        super(Chat.CHAT);
    }

    /**
     * Create a detached, initialised ChatRecord
     */
    @ConstructorProperties({"id", "tgChatId"})
    public ChatRecord(@Nullable Long id, @Nullable Long tgChatId) {
        super(Chat.CHAT);

        setId(id);
        setTgChatId(tgChatId);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised ChatRecord
     */
    public ChatRecord(edu.java.domain.jooq.tables.pojos.Chat value) {
        super(Chat.CHAT);

        if (value != null) {
            setId(value.getId());
            setTgChatId(value.getTgChatId());
            resetChangedOnNotNull();
        }
    }

    /**
     * Getter for <code>CHAT.ID</code>.
     */
    @Nullable
    public Long getId() {
        return (Long) get(0);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * Setter for <code>CHAT.ID</code>.
     */
    public void setId(@Nullable Long value) {
        set(0, value);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>CHAT.TG_CHAT_ID</code>.
     */
    @Nullable
    public Long getTgChatId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>CHAT.TG_CHAT_ID</code>.
     */
    public void setTgChatId(@Nullable Long value) {
        set(1, value);
    }

    @Override
    @NotNull
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    @Override
    @NotNull
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    @NotNull
    public Row2<Long, Long> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    @NotNull
    public Field<Long> field1() {
        return Chat.CHAT.ID;
    }

    @Override
    @NotNull
    public Field<Long> field2() {
        return Chat.CHAT.TG_CHAT_ID;
    }

    @Override
    @Nullable
    public Long component1() {
        return getId();
    }

    @Override
    @Nullable
    public Long component2() {
        return getTgChatId();
    }

    @Override
    @Nullable
    public Long value1() {
        return getId();
    }

    @Override
    @Nullable
    public Long value2() {
        return getTgChatId();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public ChatRecord value1(@Nullable Long value) {
        setId(value);
        return this;
    }

    @Override
    @NotNull
    public ChatRecord value2(@Nullable Long value) {
        setTgChatId(value);
        return this;
    }

    @Override
    @NotNull
    public ChatRecord values(@Nullable Long value1, @Nullable Long value2) {
        value1(value1);
        value2(value2);
        return this;
    }
}
