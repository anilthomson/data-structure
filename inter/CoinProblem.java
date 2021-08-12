public class CoinsProblem {
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
    }
}