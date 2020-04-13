public class ZeroToEnd {
    public static void main(String[] args) {
        int arr[] = { 4, 5, 0, 2, 0, 1, 0, 7, 7, 0, 8 };
        int high = arr.length;
        int x = 0;
        for (int j = high - 1; j >= 0; j--) {
            if (x == j)
                break;
            if (arr[j] == 0)
                continue;
            for (int i = x; i < j; i++) {
                if (arr[i] == 0) {
                    swap(arr, i, j);
                    x = i;
                    break;
                }
            }
        }
        print(arr);
    }

    static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void swap(int[] arr, int from, int to) {
        int temp = arr[to];
        arr[to] = arr[from];
        arr[from] = temp;
    }
}