package interview;

public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        int[] R = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        // result[nums.length-1] =1;
        R[nums.length - 1] = 1;
        for (int j = nums.length - 2; j >= 0; j--) {
            // result[j] = result[j] * result[j+1];
            R[j] = nums[j + 1] * R[j + 1];
        }
        for (int i = 0; i < nums.length; i++) {
            result[i] = result[i] * R[i];
        }
        System.out.println(result);
        return result;
    }
}
