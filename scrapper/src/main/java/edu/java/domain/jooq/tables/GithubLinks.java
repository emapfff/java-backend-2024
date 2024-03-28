/*
 * This file is generated by jOOQ.
 */

package edu.java.domain.jooq.tables;

import edu.java.domain.jooq.DefaultSchema;
import edu.java.domain.jooq.Keys;
import edu.java.domain.jooq.tables.records.GithubLinksRecord;
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
public class GithubLinks extends TableImpl<GithubLinksRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>GITHUB_LINKS</code>
     */
    public static final GithubLinks GITHUB_LINKS = new GithubLinks();
    private transient Link _link;

    /**
     * The column <code>GITHUB_LINKS.ID</code>.
     */
    public final TableField<GithubLinksRecord, Long> ID =
        createField(DSL.name("ID"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>GITHUB_LINKS.LINK_ID</code>.
     */
    public final TableField<GithubLinksRecord, Long> LINK_ID =
        createField(DSL.name("LINK_ID"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>GITHUB_LINKS.COUNT_BRANCHES</code>.
     */
    public final TableField<GithubLinksRecord, Integer> COUNT_BRANCHES =
        createField(DSL.name("COUNT_BRANCHES"), SQLDataType.INTEGER, this, "");

    private GithubLinks(Name alias, Table<GithubLinksRecord> aliased) {
        this(alias, aliased, null);
    }

    private GithubLinks(Name alias, Table<GithubLinksRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>GITHUB_LINKS</code> table reference
     */
    public GithubLinks(String alias) {
        this(DSL.name(alias), GITHUB_LINKS);
    }

    /**
     * Create an aliased <code>GITHUB_LINKS</code> table reference
     */
    public GithubLinks(Name alias) {
        this(alias, GITHUB_LINKS);
    }

    /**
     * Create a <code>GITHUB_LINKS</code> table reference
     */
    public GithubLinks() {
        this(DSL.name("GITHUB_LINKS"), null);
    }

    public <O extends Record> GithubLinks(Table<O> child, ForeignKey<O, GithubLinksRecord> key) {
        super(child, key, GITHUB_LINKS);
    }

    @Override
    @Nullable
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    @NotNull
    public Identity<GithubLinksRecord, Long> getIdentity() {
        return (Identity<GithubLinksRecord, Long>) super.getIdentity();
    }

    @Override
    @NotNull
    public UniqueKey<GithubLinksRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_3;
    }

    @Override
    @NotNull
    public List<ForeignKey<GithubLinksRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CONSTRAINT_3A);
    }

    /**
     * The class holding records for this type
     */
    @Override
    @NotNull
    public Class<GithubLinksRecord> getRecordType() {
        return GithubLinksRecord.class;
    }

    /**
     * Get the implicit join path to the <code>PUBLIC.LINK</code> table.
     */
    public Link link() {
        if (_link == null)
            _link = new Link(this, Keys.CONSTRAINT_3A);

        return _link;
    }

    @Override
    @NotNull
    public GithubLinks as(String alias) {
        return new GithubLinks(DSL.name(alias), this);
    }

    @Override
    @NotNull
    public GithubLinks as(Name alias) {
        return new GithubLinks(alias, this);
    }

    @Override
    @NotNull
    public GithubLinks as(Table<?> alias) {
        return new GithubLinks(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public GithubLinks rename(String name) {
        return new GithubLinks(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public GithubLinks rename(Name name) {
        return new GithubLinks(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public GithubLinks rename(Table<?> name) {
        return new GithubLinks(name.getQualifiedName(), null);
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
