package interview;

public class KthLargest {
    public int findKthLargest(int[] nums, int k) {

        for (int i : nums)
            System.out.print(i + " ");
        return quickSort(nums, 0, nums.length - 1, k);
    }

    int quickSort(int[] nums, int start, int end, int k) {
        if (end <= start)
            return nums[k - 1];
        int pivot = pivotIndex(nums, start, end);
        if (pivot == k - 1)
            return nums[pivot];
        System.out.println(start + " " + end + " " + pivot);
        quickSort(nums, start, pivot - 1, k);
        quickSort(nums, pivot + 1, end, k);
        return nums[k - 1];
    }

    int pivotIndex(int[] nums,int start,int end){
        int pivot = nums[end];
        int pivotindex =start;
        for(int i=start;i<end;i++){
            if(nums[i]>pivot){
                int tmp = nums[i];
                nums[i]=nums[pivotindex];
                nums[pivotindex]=tmp;
                pivotindex++;
            }
        }
               int tmp = nums[end];
                nums[end]=nums[pivotindex];
                nums[pivotindex]=tmp;
        return pivotindex;
    }
}
