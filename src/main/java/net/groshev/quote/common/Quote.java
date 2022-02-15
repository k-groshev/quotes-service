package net.groshev.quote.common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Quote extends AbstractDomainEntityModel {

    private static final long serialVersionUID = 4567340302195747265L;

    private String text;

    public Quote() {
        super();
    }

    public Quote(final String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public final  boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof Quote)) return false;

        final Quote that = (Quote) o;

        return that.canEqual(this) &&
                new EqualsBuilder()
                        .appendSuper(super.equals(that))
                        .append(this.text, that.text)
                        .isEquals();
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(this.text)
                .toHashCode();
    }

    @Override
    public boolean canEqual(Object other) {
        return other instanceof Quote;
    }
}
