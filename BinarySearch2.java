class DoBinarySearch {

    public static void main(String[] args) {
        int arr[] = { 2, 3, 4, 10, 40, 42, 43, 44, 50, 60 };
        int x = 2;
        int len = arr.length;

        int search = search(arr, 0, len - 1, x);
        System.out.println(search);
    }

    private static int search(int arr[], int left, int right, int x) {
        if (left > right)
            return -1;
        int mid = (left + right) / 2;
        //System.out.println(mid);
        if (arr[mid] == x)
            return mid;
        else if (arr[mid] > x)
            return search(arr, left, mid - 1, x);
        else
            return search(arr, mid + 1, right, x);
    }
}