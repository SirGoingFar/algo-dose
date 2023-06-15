package preparation.random.dynamicProgramming.coinChange;

import java.util.*;

/**
 * <b>[MEDIUM] Question</b>: You are given an integer array of coins representing available coins and an integer amount representing a total amount of money (the change).
 * Return the combination of coins that you need to make up the amount. If that amount of money cannot be made up from the available coins, return empty combination.
 * <p/>
 * Context: Vending machine coin change
 */
public class CoinChangeCombination {
    public static void main(String[] args) {
        System.out.printf("Coin combination for change amount is %s\n", changeCombination(7, new int[]{1, 5, 2, 7, 9}));
    }

    private static List<Integer> changeCombination(int amount, int[] coins) {

        //Sort available coin list for memoization sake
        Arrays.sort(coins);

        //Prepare a coin-to-coinFrequency map
        Map<Integer, Integer> coinFrequencyMap = new HashMap<>();
        for (int coin : coins) {
            coinFrequencyMap.put(coin, coinFrequencyMap.getOrDefault(coin, 0) + 1);
        }

        //create cache for amount[x], x: 0 -> (amount + 1)
        int cacheSize = amount + 1;
        List<Integer>[] resultCache = new List[cacheSize];

        //Set each array element to default
        Arrays.fill(resultCache, new ArrayList<>());

        //Set the base case to allow for dynamic programming technique
        resultCache[0] = Collections.emptyList();

        //Using Depth-first search - backtracking (i.e. for amount: 0 -> (amount + 1)), search for coin combination for change amount.
        //Leverage result cache for memoization
        for (int amt = 1; amt < cacheSize; amt++) {

            for (int currentCoinValue : coins) {
                if (amt - currentCoinValue >= 0) {
                    List<Integer> newCoinCombinationList = new ArrayList<>(List.of(currentCoinValue));
                    newCoinCombinationList.addAll(resultCache[amt - currentCoinValue]);
                    if (!isCombinationAllowed(newCoinCombinationList, new HashMap<>(coinFrequencyMap))) {
                        continue;
                    }
                    resultCache[amt] = newCoinCombinationList;
                }
            }

            if (!resultCache[amount].isEmpty()) {
                break; //a valid coin combination is available for amount, safe to exit
            }
        }

        //If coin combination exist for change, return it. Else an empty combination, i.e. no coin combination for change.
        return resultCache[amount].isEmpty() ? Collections.emptyList() : resultCache[amount];
    }

    private static boolean isCombinationAllowed(List<Integer> newCoinCombination, HashMap<Integer, Integer> coinMap) {
        for (int coin : newCoinCombination) {
            int coinFrequency = coinMap.getOrDefault(coin, 0);
            coinFrequency -= 1;

            if (coinFrequency < 0) {
                return false;
            }

            coinMap.put(coin, coinFrequency);
        }
        return true;
    }

}
