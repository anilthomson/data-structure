package interview;
public class CoinProblem {
    public static void main(String[] args) {
        int[] coins = { 2, 5, 19, 20 };
        System.out.println(makeChange(coins, 29));
    }

    public static long makeChange(int[] coins, int amount) {
        return makeChange(coins, amount, 0);
    }

    public static long makeChange(int[] coins, int money, int index) {
        if (money == 0)
            return 1;
        if (index >= coins.length)
            return 0;
        int amountWIthCoin = 0;
        long ways = 0;
        while (amountWIthCoin <= money) {
            int remaining = money - amountWIthCoin;
            ways = ways + makeChange(coins, remaining, index + 1);
            amountWIthCoin = amountWIthCoin + coins[index];
        }
        return ways;
    }
}