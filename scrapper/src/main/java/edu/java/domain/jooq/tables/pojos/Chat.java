/*
 * This file is generated by jOOQ.
 */

package edu.java.domain.jooq.tables.pojos;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import javax.annotation.processing.Generated;
import org.jetbrains.annotations.Nullable;

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
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long tgChatId;

    public Chat() {
    }

    public Chat(Chat value) {
        this.id = value.id;
        this.tgChatId = value.tgChatId;
    }

    @ConstructorProperties({"id", "tgChatId"})
    public Chat(
        @Nullable Long id,
        @Nullable Long tgChatId
    ) {
        this.id = id;
        this.tgChatId = tgChatId;
    }

    /**
     * Getter for <code>CHAT.ID</code>.
     */
    @Nullable
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>CHAT.ID</code>.
     */
    public void setId(@Nullable Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>CHAT.TG_CHAT_ID</code>.
     */
    @Nullable
    public Long getTgChatId() {
        return this.tgChatId;
    }

    /**
     * Setter for <code>CHAT.TG_CHAT_ID</code>.
     */
    public void setTgChatId(@Nullable Long tgChatId) {
        this.tgChatId = tgChatId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Chat other = (Chat) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        if (this.tgChatId == null) {
            if (other.tgChatId != null) {
                return false;
            }
        } else if (!this.tgChatId.equals(other.tgChatId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.tgChatId == null) ? 0 : this.tgChatId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Chat (");

        sb.append(id);
        sb.append(", ").append(tgChatId);

        sb.append(")");
        return sb.toString();
    }
}