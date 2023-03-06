package com.freightos.snackvendingmachine.helpers;

import com.freightos.snackvendingmachine.models.emuns.MoneyDenomination;
import com.freightos.snackvendingmachine.vendingmachine.SnackVendingMachine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MoneyChangeHelper {

    private Map<Double, Integer> valueToCountMap;

    private SnackVendingMachine snackVendingMachine;

    public MoneyChangeHelper(SnackVendingMachine snackVendingMachine) {
        this.snackVendingMachine = snackVendingMachine;
        valueToCountMap = snackVendingMachine.getDenominationToSlotMap().values().stream().collect(Collectors.toMap(moneySlot -> moneySlot.getMoneyDenomination().getValue() * 10, moneySlot -> moneySlot.getCount()));
    }

    //This solution can be enhanced by using the previous db array.
    //Just update the dp when needed
    public Map<MoneyDenomination, Integer> getChange(List<MoneyDenomination> money, double change) {
        money.forEach(moneyDenomination -> {
            Integer count = valueToCountMap.getOrDefault(moneyDenomination.getValue() * 10, 0);
            valueToCountMap.put(moneyDenomination.getValue() * 10, count + 1);
        });
        int amount = (int) Math.round(change * 10);
        int coins[] = valueToCountMap.keySet().stream().mapToInt(value -> (int) value.doubleValue()).toArray();
        int counts[] = valueToCountMap.values().stream().mapToInt(count -> count).toArray();

        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        int[][] prev = new int[n + 1][amount + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= amount; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                prev[i][j] = -1;

                for (int k = 1; k <= counts[i - 1] && k * coins[i - 1] <= j; k++) {
                    if (dp[i - 1][j - k * coins[i - 1]] != Integer.MAX_VALUE && dp[i - 1][j - k * coins[i - 1]] + k < dp[i][j]) {
                        dp[i][j] = dp[i - 1][j - k * coins[i - 1]] + k;
                        prev[i][j] = k;
                    }
                }
            }
        }

        if (dp[n][amount] == Integer.MAX_VALUE) {
            return null;
        }

        Map<MoneyDenomination, Integer> result = new HashMap<>();

        int i = n, j = amount;
        while (i > 0 && j > 0) {
            int count = prev[i][j];
            if (count > 0) {
                for (int k = 0; k < count; k++) {
                    int denominationCount = result.getOrDefault(MoneyDenomination.getByValue(coins[i - 1] / 10.0), 0);
                    result.put(MoneyDenomination.getByValue(coins[i - 1] / 10.0), denominationCount + 1);
                }
                j -= count * coins[i - 1];
            }
            i--;
        }

        return result;


    }

}
