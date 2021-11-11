package interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class BinaryTreeVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        Map<Integer,Map<Integer,List<Integer>>> map = new TreeMap<Integer,Map<Integer,List<Integer>>>();
         traverse(root,map,0,0);
        // return null;
        // Map  mp = map.values().stream().collect(Collectors.toMap(Function.identity(), Function.identity()));
       //  System.out.println(mp.getClass());
        // List result = map.values().stream().collect(Collectors.toList()); 
         List<Map<Integer,List<Integer>>> list =map.values().stream().collect(Collectors.toList());
          List<List<Integer>>  result = new ArrayList();
         for(Map<Integer,List<Integer>> mp : list)  {
            // System.out.println(mp);
             List  val = mp.values().stream().collect(Collectors.toList());
             if(val.size()==1)result.add((List<Integer>)val.get(0));
             else
             {
                 List<Integer> all = new ArrayList();
                 result.add(all);
                 for(Object k: val){
                     all.addAll((List<Integer>)k);
                     List<Integer> listx = (List<Integer>)k;
                 }
             }
             // System.out.println(val);
         }
          return  result;
              //mp.values().stream().collect(Collectors.toList()); 
     }
     void traverse(TreeNode node,Map<Integer,Map<Integer,List<Integer>>> map,int column,int row){
         if(node==null)return;
         traverse(node.left,map,column-1,row+1);
         Map<Integer,List<Integer>> colMap = map.get(column);
         if(colMap==null){
             colMap = new TreeMap<Integer,List<Integer>>();
             map.put(column,colMap);
         }
         List<Integer> list = colMap.get(row);
         if(list ==null){
             list = new ArrayList<Integer>();
             colMap.put(row,list);
         }
       
         list.add(node.val);
        //  System.out.println(" col "+column +"row "+row+" map "+ map+" "+node.val);
         traverse(node.right,map,column+1,row+1);
     }
}
