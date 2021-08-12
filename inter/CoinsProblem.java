public class CoinsProblem {
    public static void main(String[] args) {
        int[] coins = { 1, 2 };
        System.out.println(makeChange(coins, 4));
    }

    public static long makeChange(int[] coins, int amount) {
        return findChange(coins, amount, 0);
    }

    public static int findChange(int[] coins, int amount, int index) {
        if (amount == 0)
            return 1;
        if (amount < 0)
            return 0;
        int ways = 0;
        for (int i = index; i < coins.length; i++) {
            int res = findChange(coins, amount - coins[i], i);
            if (res >= 1)
                System.out.print( coins[i]+" ");
            ways = ways + res;
        }
        System.out.println("\n"+amount + " ways  " + ways);
        return ways;
    }
}