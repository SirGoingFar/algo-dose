package preparation.random.dynamicProgramming.coinChange;

import java.util.Arrays;

/**
 * <b>[MEDIUM] Question</b>: You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 * <p/>
 * See <a href=https://neetcode.io/courses/advanced-algorithms/19>more</a>
 */
public class MinimumNumberOfChangeCoins {
    public static void main(String[] args) {
        System.out.printf("Number of coin required to have amount is %d\n", numberOfCoinRequired(11, new int[]{1,2,5}));
        System.out.printf("Number of coin required to have amount is %d\n", numberOfCoinRequired(3, new int[]{2}));
        System.out.printf("Number of coin required to have amount is %d\n", numberOfCoinRequired(0, new int[]{1}));
    }

    private static int numberOfCoinRequired(int amount, int[] coins) {
        //create cache for dp[x], x: 0 -> amount + 1
        int cacheSize = amount + 1;
        int[] dp = new int[cacheSize];

        //initialize each of dp[x] to the highest value possible
        int highestValuePossible = amount + 1;
        Arrays.fill(dp, highestValuePossible);

        dp[0] = 0; //base case -> takes 0 coin to get coin of amount 0

        for(int amt = 1; amt < cacheSize; amt++){

            for(int coinIdx = 0; coinIdx < coins.length; coinIdx++){

                int currentCoin = coins[coinIdx];

                if(amt - currentCoin >= 0){
                    dp[amt] = Math.min(dp[amt], 1 + dp[amt - currentCoin]);
                }
            }

        }
        return dp[amount] != highestValuePossible ? dp[amount] : -1;
    }
}
