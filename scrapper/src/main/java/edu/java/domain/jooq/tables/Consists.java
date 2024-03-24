/*
 * This file is generated by jOOQ.
 */

package edu.java.domain.jooq.tables;

import edu.java.domain.jooq.DefaultSchema;
import edu.java.domain.jooq.Keys;
import edu.java.domain.jooq.tables.records.ConsistsRecord;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import javax.annotation.processing.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

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
public class Consists extends TableImpl<ConsistsRecord> {

    /**
     * The reference instance of <code>CONSISTS</code>
     */
    public static final Consists CONSISTS = new Consists();
    private static final long serialVersionUID = 1L;
    /**
     * The column <code>CONSISTS.CHAT_ID</code>.
     */
    public final TableField<ConsistsRecord, Long> CHAT_ID =
        createField(DSL.name("CHAT_ID"), SQLDataType.BIGINT.nullable(false), this, "");
    /**
     * The column <code>CONSISTS.LINK_ID</code>.
     */
    public final TableField<ConsistsRecord, Long> LINK_ID =
        createField(DSL.name("LINK_ID"), SQLDataType.BIGINT.nullable(false), this, "");
    private transient Chat _chat;
    private transient Link _link;

    private Consists(Name alias, Table<ConsistsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Consists(Name alias, Table<ConsistsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>CONSISTS</code> table reference
     */
    public Consists(String alias) {
        this(DSL.name(alias), CONSISTS);
    }

    /**
     * Create an aliased <code>CONSISTS</code> table reference
     */
    public Consists(Name alias) {
        this(alias, CONSISTS);
    }

    /**
     * Create a <code>CONSISTS</code> table reference
     */
    public Consists() {
        this(DSL.name("CONSISTS"), null);
    }

    public <O extends Record> Consists(Table<O> child, ForeignKey<O, ConsistsRecord> key) {
        super(child, key, CONSISTS);
    }

    /**
     * The class holding records for this type
     */
    @Override
    @NotNull
    public Class<ConsistsRecord> getRecordType() {
        return ConsistsRecord.class;
    }

    @Override
    @Nullable
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    @NotNull
    public UniqueKey<ConsistsRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_C;
    }

    @Override
    @NotNull
    public List<ForeignKey<ConsistsRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CONSTRAINT_CC, Keys.CONSTRAINT_CC9);
    }

    /**
     * Get the implicit join path to the <code>PUBLIC.CHAT</code> table.
     */
    public Chat chat() {
        if (_chat == null) {
            _chat = new Chat(this, Keys.CONSTRAINT_CC);
        }

        return _chat;
    }

    /**
     * Get the implicit join path to the <code>PUBLIC.LINK</code> table.
     */
    public Link link() {
        if (_link == null) {
            _link = new Link(this, Keys.CONSTRAINT_CC9);
        }

        return _link;
    }

    @Override
    @NotNull
    public Consists as(String alias) {
        return new Consists(DSL.name(alias), this);
    }

    @Override
    @NotNull
    public Consists as(Name alias) {
        return new Consists(alias, this);
    }

    @Override
    @NotNull
    public Consists as(Table<?> alias) {
        return new Consists(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Consists rename(String name) {
        return new Consists(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Consists rename(Name name) {
        return new Consists(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Consists rename(Table<?> name) {
        return new Consists(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function2<? super Long, ? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function2<? super Long, ? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
