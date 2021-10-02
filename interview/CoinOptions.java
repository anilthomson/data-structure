package interview;

public class CoinOptions {
    public static void main(String[] args) {
        int[] input = { 1, 2  };
        //1,2
        //1,1,1
        int amount = 4;
        int[] memoize = new int[amount];
        int ret = find(input, amount, memoize);
        System.out.println("FINAL " + ret);
    }
    static int find(int[] coins, int amount, int[] memoize) {
        if (amount < 0)
            return -1;
        if (amount == 0)
            return 0;
        // if (memoize[amount - 1] != 0)
        // return memoize[amount - 1];
        int min = -1;
        for (int inum : coins) {
            int newamount = amount - inum;
            int ret = 0;
            
            if (newamount > 0 && memoize[newamount - 1] != 0) {
                ret = memoize[newamount - 1];
            } else {
               
                ret = find(coins, newamount, memoize);
                if (newamount > 0)
                    memoize[newamount - 1] = ret;
            }
            if (min == -1)
                min = ret;
            else if (ret != -1 )
                min += ret;
            System.out.println(inum+" "+min);

        }
        if (min > -1)
            min++;
        memoize[amount - 1] = min;
        return min;
    }
}