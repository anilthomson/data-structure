package interview;

public class TrappingRainWater {
    public int trap(int[] height) {
        int ans = 0;
        int len = height.length;
        int[] left_max = new int[len];
        int[] right_max = new int[len];
        left_max[0] = height[0];
        right_max[len - 1] = height[len - 1];
        int j = len - 2;
        for (int i = 1; i < len; i++) {
            left_max[i] = Math.max(height[i], left_max[i - 1]);
            right_max[j] = Math.max(height[j], right_max[j + 1]);
            System.out.println(left_max[i] + "  " + right_max[j]);
            j--;
        }
        for (int i = 1; i < len; i++) {
            ans += Math.min(left_max[i], right_max[i]) - height[i];
        }
        return ans;
    }
}
