package interview;

public class RotatedArray {
    public static void main(String[] args) {
        int[] arr = { 4, 5, 6, 7, 0, 1, 2 };
        System.out.println(findPivot(arr));
    }

    static int findPivot(int[] nums) {
        int min = nums[0];
        int minIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
    static int findPivot2(int[] nums) {
        int min = nums[0];
        int minIndex = 0;
        int pivot, left = 0, right = nums.length - 1;
        
        return minIndex;
    }
    public int search(int[] nums, int target) {
        int pivot, left = 0, right = nums.length - 1;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            if (nums[pivot] == target)
                return pivot;
            if (target < nums[pivot])
                right = pivot - 1;
            else
                left = pivot + 1;
        }
        return -1;
    }
}
