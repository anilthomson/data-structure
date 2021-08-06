public class QuickSortTest {
    public static void main(String[] args) {
        int data[] = new int[10000];
        for (int i = 0; i < 10000; i++)
            data[i] = ((int)Math.round(Math.random() * 100));
        System.out.println(System.currentTimeMillis());
        //int data[] = { 9, 4, 8, 2, 0, 5, 7 };
        sort(data, 0, data.length - 1);
        System.out.println(System.currentTimeMillis());
    }

    public static void sort(int[] data, int start, int end) {
        // System.out.println(start + " " + end);

        if (end < start)
            return;
        int pivotIndex = findPivotIndex(data, start, end);
        sort(data, start, pivotIndex - 1);
        sort(data, pivotIndex + 1, end);

    }

    public static int findPivotIndex(int[] data, int start, int end) {
        int pivotIndex = start;
        int pivotValue = data[end];
        for (int i = start; i < end; i++) {
            if (data[i] < pivotValue) {
                swap(data, i, pivotIndex);
                pivotIndex++;
            }
        }
        swap(data, pivotIndex, end);
        return pivotIndex;
    }

    private static void swap(int[] data, int from, int to) {
        int tmp = data[from];
        data[from] = data[to];
        data[to] = tmp;
    }
}