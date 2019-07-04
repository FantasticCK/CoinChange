package com.CK;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        int amount = 11;
        Solution solution = new Solution();
        System.out.println(solution.coinChange(coins, amount));
    }
}

// Bottom Up
class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        int[] result = new int[amount + 1];
        for (int i = 1; i < amount + 1; i++) {
            int min = -1;
            for (int coin : coins) {
                if (coin <= i && result[i - coin] != -1) {
                    int temp = result[i - coin] + 1;
                    min = min == -1 ? temp : (temp < min ? temp : min);
                }
            }
            result[i] = min;
        }
        return result[amount];
    }
}

//Top Down
class Solution3 {

    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }
}


class Solution2 {
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        int[] dp = new int[amount + 1];
        int sum = 0;

        while (++sum <= amount) {
            int min = -1;
            for (int coin : coins) {
                if (sum >= coin && dp[sum - coin] != -1) {
                    int temp = dp[sum - coin] + 1;
                    min = min < 0 ? temp : (temp < min ? temp : min);
                }
            }
            dp[sum] = min;
        }
        return dp[amount];
    }
}