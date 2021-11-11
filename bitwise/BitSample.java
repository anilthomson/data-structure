package bitwise;

public class BitSample {

    public static void main(String[] args) {
        int n = 64;
        int m = 5;
        System.out.println("n -> " + n);
        System.out.println("Bnnary -> " + Integer.toBinaryString(n));
        System.out.println("-n -> " + -n);
        System.out.println("Bnnary -> " + Integer.toBinaryString(-n));
        System.out.println("~n -> " + ~n);
        System.out.println("Bnnary -> " + Integer.toBinaryString(~n));
        System.out.println("n&n -> " + (n & n));
        System.out.println("n|n -> " + (n | n));
        System.out.println("n^n -> " + (n ^ n));

        System.out.println("n&1 -> " + (n & 1));
        System.out.println("n|1 -> " + (n | 1));
        System.out.println("n^1 -> " + (n ^ 1));

        System.out.println("n&-n -> " + (n & -n));
        System.out.println("n|-n -> " + (n | (-n)));
        System.out.println("n^-n -> " + (n ^ -n));

        System.out.println("n&~n -> " + (n & ~n));
        System.out.println("n|~n -> " + (n | ~n));
        System.out.println("n^~n -> " + (n ^ ~n));

        System.out.println("n = " + n + " m = " + m);
        System.out.println((n ^ m)+" "+2*(m&n));
        n = n ^ m;
        System.out.println(n);
        m = n ^ m;
        System.out.println(m);
        n = m ^ n;
        System.out.println(n);
        System.out.println("n = " + n + " m = " + m);
        int x = 4;
        System.out.println(x + " + " + 1 + " is " + -~x);
        System.out.println( Integer.toBinaryString(x));
        System.out.println( Integer.toBinaryString(~x));
        System.out.println( Integer.toBinaryString(-~x));
        int y = ~x;
        System.out.println((char)('q'|' ' ));
        int Sum = 17;
        int Xor = 9;
//Sum=Xor + 2(a&b);
//2a&b = 8, a&b=4
    }
}
