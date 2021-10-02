package interview;
import java.util.HashMap;
import java.util.Map;

class SolutionX {
    public int[] twoSum(int[] nums, int target) {
       Map<Integer,Integer> map=new HashMap();
        int len=nums.length;
        for(int i=0;i<len;i++){
            Integer val=map.get(nums[i]);
            if(val!=null )
                return new int[]{val,i};
            else
                map.put(target - nums[i],i);
            
        }
        return null;
    }
}