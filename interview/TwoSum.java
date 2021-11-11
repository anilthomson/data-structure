package interview;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] result =new int[2];
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
          for (int i=0;i<nums.length;i++) {
               int data = nums[i];
               if(  map.get(data) !=null){
                   result[0]=i;
                   result[1] = map.get(data);
                   return result;
               }else{
                   map.put(target-data,i);
                }
            }
        return result;
    }
}
