package interviews.wise.preparation.currencyConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StaticRateCurrencyConverter implements CurrencyConverter {

    private final Map<Currency, BigDecimal> currencyRateMap = new ConcurrentHashMap<>();

    public StaticRateCurrencyConverter() {
        populateCurrencyRateMap();
    }

    @Override
    public Money convert(final Money money, final Currency destinationCurrency) {

        if (money == null) {
            throw new IllegalArgumentException("Money is invalid");
        }

        if (destinationCurrency == null) {
            throw new IllegalArgumentException("Destination currency is invalid");
        }

        if (money.getCurrency() == destinationCurrency) {
            return money;
        }

        //Assume the currency rate (and as expected) are relative to a base currency; say USD
        final BigDecimal sourceCurrencyToBaseCurrencyRate = getCurrencyRate(money.getCurrency());
        final BigDecimal destinationCurrencyToBaseCurrencyRate = getCurrencyRate(money.getCurrency());

        //Formula: (sourceCurrencyAmount / sourceCurrencyToBaseCurrencyRate) * destinationCurrencyToBaseCurrencyRate
        BigDecimal conversionEquivalent = (money.getAmount().divide(sourceCurrencyToBaseCurrencyRate, RoundingMode.HALF_DOWN))
                .multiply(destinationCurrencyToBaseCurrencyRate);

        return Money.of(destinationCurrency, conversionEquivalent);

    }

    private BigDecimal getCurrencyRate(Currency currency) {
        if (!currencyRateMap.containsKey(currency)) {
            throw new IllegalArgumentException("Currency rate does not exists");
        }
        return currencyRateMap.get(currency);
    }

    private void populateCurrencyRateMap() {
        currencyRateMap.put(Currency.USD, BigDecimal.valueOf(1.0));
        currencyRateMap.put(Currency.EUR, BigDecimal.valueOf(0.8));
        currencyRateMap.put(Currency.NGN, BigDecimal.valueOf(500));
    }

}
