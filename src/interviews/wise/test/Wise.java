/*
package interviews.wise.test;

import java.math.BigDecimal;

*/
/**
 * This is the library communicating with rate service.
 * Rate service is responsible to give back exchange rates for different currencies
 * <p>
 * The following classes/methods are part of an already implemented library, you do no have to implement it
 *//*


public interface RateProvider {
    BigDecimal getRate(String currencyPair);
    */
/** Examples:
     EUR-GBP == 0.88
     GBP-EUR == 1.14
     GBP-USD == 1.23
     We pay 0.01 GBP for each rate call, and it has 98 % availability.
     *//*

}

public class RateClient implements RateProvider {
    public BigDecimal getRate(String currencyPair) {
        */
/** remote I/O operation: HTTPS GET/rate
         this part is already implemented in a library *//*

    }
}

public class RateService {
    public static RateProvider getRateService() {
        */
/** give back the correct rate service *//*

    }
}

*/
/**
 * The task is to implement backend service that should serve Wise customers and give back the exchange rate.
 * We would like to be highly available here and service majority of the customers in less than 100 ms.
 * Customer should see whats the conversation rate if they convert their amount of money to given currency.
 * This service method is called from outside 1000/sec.
 *//*



//- highly available
//- performance: less than 100 ms
//- API call rate: 1000/sec
//valid -> 1 min

// !!! MODIFY CODE BELOW !!!


public class Main {

    private final Map<String, Rate> cache;
    private final RateProvider provider;
    private final LinkedList<String> currencyPairOrder;

    //For test purposes
    public Main(RateProvider rateProvider, ConcurrentHashMap<String, Rate> cache, LinkedList<String> currencyPairOrder) {
        this.provider = rateProvider;
        this.cache = cache;
        this.currencyPairOrder = currencyPairOrder;
    }

    public Main(RateProvider rateProvider) {
        this.provider = rateProvider;
        this.cache = new ConcurrencyHashMap(100000);
        this.currencyPairOrder = new LinkedList(100000);
    }

    public static void main() {
        String currencyPair = "read from frontend";
        if (cache.containsKey(currencyPair)) {
            Rate rate = cache.get(currencyPair);

            if (System.currentTimeMillis() - rate.getTimestamp() < (60 * 60 * 1000)) {
                return rate.getValue();
            } else {
                return getCurrencyRate(currencyPair);
            }
        } else {
            return getCurrencyRate(currencyPair);
        }
    }

    private static BigDecimal getCurrencuRate(String currencyPair) {

        BigDecimal libraryRate = provider.getRate(currencyPair);

        if (libraryRate == null) {
            int retryCount = 0;

            while (retryCount < 3 && libraryRate == null) {
                libraryRate = provider.getRate(currencyPair);
                retryCount++;
            }

            throw new ServiceUnavailableException("Message");
            //UI/UX question - throw an exception??
        }

        String currencyPairAtOrderTail = currencyPairOrder.getLast();
        if (currencyPairAtOrderTail != null) {
            currencyPairOrder.remove(currencyPairOrder.size() - 1);
            cache.remove(currencyPairAtOrderTail);
        }

        cache.put(currencyPair, new Rate(System.currentTimeMillis(), libraryRate));
        currencyPairOrder.addFirst(currencyPair);

        return libraryRate;
    }

    @Data
    class Rate {
        private final long timestamp;
        private final BigDecimal value;
    }
}
*/
