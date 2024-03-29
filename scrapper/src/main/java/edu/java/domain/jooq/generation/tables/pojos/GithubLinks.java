/*
 * This file is generated by jOOQ.
 */

package edu.java.domain.jooq.generation.tables.pojos;

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
public class GithubLinks implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long linkId;
    private Integer countBranches;

    public GithubLinks() {
    }

    public GithubLinks(GithubLinks value) {
        this.id = value.id;
        this.linkId = value.linkId;
        this.countBranches = value.countBranches;
    }

    @ConstructorProperties({"id", "linkId", "countBranches"})
    public GithubLinks(
        @Nullable Long id,
        @Nullable Long linkId,
        @Nullable Integer countBranches
    ) {
        this.id = id;
        this.linkId = linkId;
        this.countBranches = countBranches;
    }

    /**
     * Getter for <code>GITHUB_LINKS.ID</code>.
     */
    @Nullable
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>GITHUB_LINKS.ID</code>.
     */
    public void setId(@Nullable Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>GITHUB_LINKS.LINK_ID</code>.
     */
    @Nullable
    public Long getLinkId() {
        return this.linkId;
    }

    /**
     * Setter for <code>GITHUB_LINKS.LINK_ID</code>.
     */
    public void setLinkId(@Nullable Long linkId) {
        this.linkId = linkId;
    }

    /**
     * Getter for <code>GITHUB_LINKS.COUNT_BRANCHES</code>.
     */
    @Nullable
    public Integer getCountBranches() {
        return this.countBranches;
    }

    /**
     * Setter for <code>GITHUB_LINKS.COUNT_BRANCHES</code>.
     */
    public void setCountBranches(@Nullable Integer countBranches) {
        this.countBranches = countBranches;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final GithubLinks other = (GithubLinks) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        } else if (!this.id.equals(other.id))
            return false;
        if (this.linkId == null) {
            if (other.linkId != null)
                return false;
        } else if (!this.linkId.equals(other.linkId))
            return false;
        if (this.countBranches == null) {
            if (other.countBranches != null)
                return false;
        } else if (!this.countBranches.equals(other.countBranches))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.linkId == null) ? 0 : this.linkId.hashCode());
        result = prime * result + ((this.countBranches == null) ? 0 : this.countBranches.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GithubLinks (");

        sb.append(id);
        sb.append(", ").append(linkId);
        sb.append(", ").append(countBranches);

        sb.append(")");
        return sb.toString();
    }
}
