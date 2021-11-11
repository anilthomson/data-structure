package interview;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeSerialize {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(root,sb);
      
        return sb.toString().substring(0,sb.length()-1);
    }
    void dfs(TreeNode treeNode,StringBuilder sb){
        Queue<TreeNode> queue=new LinkedList<TreeNode>();
        queue.add(treeNode);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
             if(node==null){
                sb.append("null,");
                 continue;
                }
            sb.append(node.val+",");
           queue.add(node.left);
            queue.add(node.right);
             
        }
    }
 
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
          System.out.println(data);
        String[] split = data.split(",");
         TreeNode node = build(split,0);
        return node;
    }
    TreeNode build(String[] data,int index)
    {
        TreeNode root = buildNode(data[index++]);
        Queue<TreeNode> queue=new LinkedList<TreeNode>();
        queue.add(root);
         TreeNode node=null;
        while((!queue.isEmpty()) && index<data.length-1){ 
            node = queue.poll();
            if(node== null)continue;
             node.left=buildNode(data[index++]);
             node.right=buildNode(data[index++]);
             queue.add(node.left);
             queue.add(node.right);
        }
       
        return root;
    }
    TreeNode buildNode(String data){
          if(data == null || data.equals("null"))return null;
         TreeNode node = new TreeNode(Integer.parseInt(data));
        return node;
    }
}
