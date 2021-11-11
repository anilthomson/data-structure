package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs.length==0) return new ArrayList();
        Map<String,List> map = new HashMap();
        for(String s : strs){
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            if(map.containsKey(key)){
                map.get(key).add(s);
            }else{
                ArrayList list =new ArrayList();
                list.add(s);
                map.put(key,list);
            }
        }
        return new ArrayList(map.values());
    }
}
