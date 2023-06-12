package interviews.wise.preparation.currencyConverter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

public class Money implements Serializable, Cloneable {

    private final BigDecimal amount;
    private final Currency currency;

    private Money() {
        throw new UnsupportedOperationException();
    }

    private Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money of(Currency currency, BigDecimal value) {
        if (currency == null) {
            throw new IllegalArgumentException("Currency is required");
        }

        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Invalid amount value");
        }

        return new Money(value, currency);
    }

    public static Money of(Currency currency, int value) {
        return of(currency, BigDecimal.valueOf(value));
    }

    public static Money of(Currency currency, double value) {
        return of(currency, BigDecimal.valueOf(value));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public Money clone() {
        //Read more: https://www.digitalocean.com/community/tutorials/java-clone-object-cloning-java
        try {
            Money clone = (Money) super.clone(); //Shallow cloning (ignored) using reflection API - https://www.digitalocean.com/community/tutorials/java-reflection-example-tutorial
            clone = Money.of(Currency.valueOf(this.currency.name()), BigDecimal.valueOf(this.getAmount().doubleValue())); //deep cloning
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
