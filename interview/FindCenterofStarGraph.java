package interview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FindCenterofStarGraph {
    public int findCenter(int[][] edges) {
        Map<Integer,LinkedList<Integer>> adjMap  = new HashMap<Integer,LinkedList<Integer>>();
        
       for (int j = 0; j < edges.length; j++){
           if(adjMap.get(edges[j][0])==null)  adjMap.put(edges[j][0],new LinkedList());
           adjMap.get(edges[j][0]).add(edges[j][1]);
            if(adjMap.get(edges[j][1])==null)  adjMap.put(edges[j][1], new LinkedList());
           adjMap.get(edges[j][1]).add(edges[j][0]);
       }
       int max =   adjMap.keySet().size()-1;
       for(Integer key:adjMap.keySet()){
           if(adjMap.get(key).size() == max)return key;
       }
       return 0;
   }
}
