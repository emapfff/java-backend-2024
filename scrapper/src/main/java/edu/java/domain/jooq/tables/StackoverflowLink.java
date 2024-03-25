/*
 * This file is generated by jOOQ.
 */

package edu.java.domain.jooq.tables;

import edu.java.domain.jooq.DefaultSchema;
import edu.java.domain.jooq.Keys;
import edu.java.domain.jooq.tables.records.StackoverflowLinkRecord;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import javax.annotation.processing.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
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
public class StackoverflowLink extends TableImpl<StackoverflowLinkRecord> {

    /**
     * The reference instance of <code>STACKOVERFLOW_LINK</code>
     */
    public static final StackoverflowLink STACKOVERFLOW_LINK = new StackoverflowLink();
    private static final long serialVersionUID = 1L;
    /**
     * The column <code>STACKOVERFLOW_LINK.ID</code>.
     */
    public final TableField<StackoverflowLinkRecord, Long> ID =
        createField(DSL.name("ID"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");
    /**
     * The column <code>STACKOVERFLOW_LINK.LINK_ID</code>.
     */
    public final TableField<StackoverflowLinkRecord, Long> LINK_ID =
        createField(DSL.name("LINK_ID"), SQLDataType.BIGINT, this, "");
    /**
     * The column <code>STACKOVERFLOW_LINK.ANSWER_COUNT</code>.
     */
    public final TableField<StackoverflowLinkRecord, Integer> ANSWER_COUNT =
        createField(DSL.name("ANSWER_COUNT"), SQLDataType.INTEGER, this, "");
    private transient Link _link;

    private StackoverflowLink(Name alias, Table<StackoverflowLinkRecord> aliased) {
        this(alias, aliased, null);
    }

    private StackoverflowLink(Name alias, Table<StackoverflowLinkRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>STACKOVERFLOW_LINK</code> table reference
     */
    public StackoverflowLink(String alias) {
        this(DSL.name(alias), STACKOVERFLOW_LINK);
    }

    /**
     * Create an aliased <code>STACKOVERFLOW_LINK</code> table reference
     */
    public StackoverflowLink(Name alias) {
        this(alias, STACKOVERFLOW_LINK);
    }

    /**
     * Create a <code>STACKOVERFLOW_LINK</code> table reference
     */
    public StackoverflowLink() {
        this(DSL.name("STACKOVERFLOW_LINK"), null);
    }

    public <O extends Record> StackoverflowLink(Table<O> child, ForeignKey<O, StackoverflowLinkRecord> key) {
        super(child, key, STACKOVERFLOW_LINK);
    }

    /**
     * The class holding records for this type
     */
    @Override
    @NotNull
    public Class<StackoverflowLinkRecord> getRecordType() {
        return StackoverflowLinkRecord.class;
    }

    @Override
    @Nullable
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    @NotNull
    public Identity<StackoverflowLinkRecord, Long> getIdentity() {
        return (Identity<StackoverflowLinkRecord, Long>) super.getIdentity();
    }

    @Override
    @NotNull
    public UniqueKey<StackoverflowLinkRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_9;
    }

    @Override
    @NotNull
    public List<ForeignKey<StackoverflowLinkRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CONSTRAINT_93);
    }

    /**
     * Get the implicit join path to the <code>PUBLIC.LINK</code> table.
     */
    public Link link() {
        if (_link == null) {
            _link = new Link(this, Keys.CONSTRAINT_93);
        }

        return _link;
    }

    @Override
    @NotNull
    public StackoverflowLink as(String alias) {
        return new StackoverflowLink(DSL.name(alias), this);
    }

    @Override
    @NotNull
    public StackoverflowLink as(Name alias) {
        return new StackoverflowLink(alias, this);
    }

    @Override
    @NotNull
    public StackoverflowLink as(Table<?> alias) {
        return new StackoverflowLink(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public StackoverflowLink rename(String name) {
        return new StackoverflowLink(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public StackoverflowLink rename(Name name) {
        return new StackoverflowLink(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public StackoverflowLink rename(Table<?> name) {
        return new StackoverflowLink(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row3<Long, Long, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super Long, ? super Long, ? super Integer, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(
        Class<U> toType,
        Function3<? super Long, ? super Long, ? super Integer, ? extends U> from
    ) {
        return convertFrom(toType, Records.mapping(from));
    }
}
