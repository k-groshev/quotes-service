package net.groshev.quote.common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Abstract class to represent a domain entity.
 */
public abstract class AbstractDomainEntityModel implements Serializable {
    private static final long serialVersionUID = -4365626659056851861L;

    private String id;

    //    @CreatedDate
    private Date created;

    private Date removed;

    //    @LastModifiedDate
    private Date updated;

    public AbstractDomainEntityModel() {
        setCreated(new Date());
        setUpdated(getCreated());
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }

    public Date getRemoved() {
        return this.removed;
    }

    public void setRemoved(final Date removed) {
        this.removed = removed;
    }

    public Date getUpdated() {
        return this.updated;
    }

    public void setUpdated(final Date updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof AbstractDomainEntityModel)) {
            return false;
        }

        final AbstractDomainEntityModel that = (AbstractDomainEntityModel) o;

        return that.canEqual(this) &&
                new EqualsBuilder()
                        .append(this.id, that.id)
                        .append(this.created, that.created)
                        .append(this.removed, that.removed)
                        .append(this.updated, that.updated)
                        .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.id)
                .append(this.created)
                .append(this.removed)
                .append(this.updated)
                .toHashCode();
    }

    public boolean canEqual(Object other) {
        return other instanceof AbstractDomainEntityModel;
    }
}