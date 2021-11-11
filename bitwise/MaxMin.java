package bitwise;

public class MaxMin {
    public static void main(String[] args) {

        int x = 12;
        int y = 30;
        int diff = (x - y);
        // If (x-y) is smaller than 0, then (x -y)>>31 will be 1. If (x-y) is greater
        // than or equal to 0, then (x -y)>>31 will be 0.
        int lastbit = diff >> 31;
        // if we & lastbit and diff , we get signed diffrence. if lastbit is 1, the diff is
        // minus otherwise 0;
        int signed = diff & lastbit;

        System.out.println(signed);
     
        System.out.println("x = " + x + " y = " + y + " x-signed= " + (x - signed));
        System.out.println("x = " + x + " y = " + y + " y+signed= " + (y + signed));
//if x is bigger signed i 0, x - 0= x; which is max
//if y is bigger signed is -. So x-(-diff) = x+diff=y which is max
        int max = x-signed;
        int min = y+signed;
    }
}
