public class BitTest {
    public static void main(String[] args) {
        int arr[] = { 12, 12, 14, 90, 14, 14, 14, 90 };
        int x = 12;
        for (int i : arr) {
            x ^= i;
            System.out.println(i + " " + x);
        }
        int k = 512;
        for (int i = 0; i < 10; i++) {
            System.out.println(~k  );
     
        }

    }
}