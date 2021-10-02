package sort;
class MergeSortX {
    public static void mergesort(int[] arr, int left, int right, int[] tmp) {
        if (left >= right)
            return;
        int middle = (left + right) / 2;
        mergesort(arr, left, middle, tmp);
        mergesort(arr, middle + 1, right, tmp);
        merge(arr, left, middle, arr, middle + 1, right, tmp);
    }

    private static void mergeHalves(int[] arr, int leftstart, int leftend, int rightstart, int rightend, int[] tmp) {
        int i = leftstart;
        int j = rightstart;
        int index = leftstart;
        while (i <= leftend && j <= rightend) {
            if (arr[i] < arr[j])
                tmp[index++] = arr[i++];
            else
                tmp[index++] = arr[j++];
        }
    }
    private static void merge(int[] arr1, int leftstart, int leftend, int[] arr2, int rightstart, int rightend,
            int[] tmp) {
        int i = leftstart;
        int j = rightstart;
        int index = leftstart;
        int total = leftend - leftstart + 1 + rightend - rightstart + 1;
        while (i <= leftend && j <= rightend) {
            if (arr1[i] < arr2[j])
                tmp[index++] = arr1[i++];
            else
                tmp[index++] = arr2[j++];
        }

        for (int k = i; k <= leftend; k++)
            tmp[index++] = arr1[k];

        for (int k = j; k <= rightend; k++)
            tmp[index++] = arr2[k];
        for (int x = 0; x < total; x++) {
            arr1[leftstart++] = tmp[x];
        }
        int p=0;
    }

    public static void main(String[] args) {
        int ar1[] = { 59, 67, 76, 78, 79 };
        int ar2[] = { 1, 68, 77, 80, 81, 82, 83 };
        int[] tmpx = new int[6];
        // merge(ar1, 0, 2, ar2, 3,5, tmpx);
        printArray(tmpx);
        int arr[] = { 5, 4, 6, 1, 8, 7, 2, 9, 73 };
        int mid = arr.length / 2;
        int[] tmp = new int[arr.length];
        mergesort(arr, 0, arr.length - 1, tmp);
        // merge(arr, 0, mid, arr, mid+1, arr.length,tmp);
        printArray(tmp);
    }

    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}