package interview;

import java.util.HashMap;
import java.util.Map;

public class SingleNumber {
    public int singleNumber(int[] nums) {
        Map<Integer,Boolean> map=new HashMap<Integer,Boolean>();
        for(int num:nums){
            if(map.get(num)==null)
                map.put(num,true);
            else
                map.put(num,false);
        }
        for(int num:nums){
            if(map.get(num))return num;
        }
        return -1;
    }
}
