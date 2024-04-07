/*
 * This file is generated by jOOQ.
 */

package edu.java.domain.jooq.generation;

import edu.java.domain.jooq.generation.tables.Chat;
import edu.java.domain.jooq.generation.tables.Consists;
import edu.java.domain.jooq.generation.tables.GithubLinks;
import edu.java.domain.jooq.generation.tables.Link;
import edu.java.domain.jooq.generation.tables.StackoverflowLink;
import edu.java.domain.jooq.generation.tables.records.ChatRecord;
import edu.java.domain.jooq.generation.tables.records.ConsistsRecord;
import edu.java.domain.jooq.generation.tables.records.GithubLinksRecord;
import edu.java.domain.jooq.generation.tables.records.LinkRecord;
import edu.java.domain.jooq.generation.tables.records.StackoverflowLinkRecord;
import javax.annotation.processing.Generated;
import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in the
 * default schema.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.18.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ChatRecord> CONSTRAINT_1 =
        Internal.createUniqueKey(Chat.CHAT, DSL.name("CONSTRAINT_1"), new TableField[] {Chat.CHAT.ID}, true);
    public static final UniqueKey<ConsistsRecord> CONSTRAINT_C = Internal.createUniqueKey(Consists.CONSISTS,
        DSL.name("CONSTRAINT_C"),
        new TableField[] {Consists.CONSISTS.CHAT_ID, Consists.CONSISTS.LINK_ID},
        true
    );
    public static final UniqueKey<GithubLinksRecord> CONSTRAINT_3 = Internal.createUniqueKey(GithubLinks.GITHUB_LINKS,
        DSL.name("CONSTRAINT_3"),
        new TableField[] {GithubLinks.GITHUB_LINKS.ID},
        true
    );
    public static final UniqueKey<LinkRecord> CONSTRAINT_2 =
        Internal.createUniqueKey(Link.LINK, DSL.name("CONSTRAINT_2"), new TableField[] {Link.LINK.ID}, true);
    public static final UniqueKey<StackoverflowLinkRecord> CONSTRAINT_9 =
        Internal.createUniqueKey(StackoverflowLink.STACKOVERFLOW_LINK,
            DSL.name("CONSTRAINT_9"),
            new TableField[] {StackoverflowLink.STACKOVERFLOW_LINK.ID},
            true
        );

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<ConsistsRecord, ChatRecord> CONSTRAINT_CC =
        Internal.createForeignKey(Consists.CONSISTS,
            DSL.name("CONSTRAINT_CC"),
            new TableField[] {Consists.CONSISTS.CHAT_ID},
            Keys.CONSTRAINT_1,
            new TableField[] {Chat.CHAT.ID},
            true
        );
    public static final ForeignKey<ConsistsRecord, LinkRecord> CONSTRAINT_CC9 =
        Internal.createForeignKey(Consists.CONSISTS,
            DSL.name("CONSTRAINT_CC9"),
            new TableField[] {Consists.CONSISTS.LINK_ID},
            Keys.CONSTRAINT_2,
            new TableField[] {Link.LINK.ID},
            true
        );
    public static final ForeignKey<GithubLinksRecord, LinkRecord> CONSTRAINT_3A =
        Internal.createForeignKey(GithubLinks.GITHUB_LINKS,
            DSL.name("CONSTRAINT_3A"),
            new TableField[] {GithubLinks.GITHUB_LINKS.LINK_ID},
            Keys.CONSTRAINT_2,
            new TableField[] {Link.LINK.ID},
            true
        );
    public static final ForeignKey<StackoverflowLinkRecord, LinkRecord> CONSTRAINT_93 = Internal.createForeignKey(
        StackoverflowLink.STACKOVERFLOW_LINK,
        DSL.name("CONSTRAINT_93"),
        new TableField[] {StackoverflowLink.STACKOVERFLOW_LINK.LINK_ID},
        Keys.CONSTRAINT_2,
        new TableField[] {Link.LINK.ID},
        true
    );
}