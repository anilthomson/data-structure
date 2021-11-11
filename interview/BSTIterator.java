package interview;

import java.util.ArrayList;

public class BSTIterator {
    TreeNode current;
        int pos;
        ArrayList<Integer> list = new ArrayList<Integer>();
        public BSTIterator(TreeNode root) {
             Traverse(root);
        }
        
        public int next() {
            
            return list.get(pos++);
        }
        
        public boolean hasNext() {
            return pos<list.size();
        }
       
        public void Traverse(TreeNode node){
            if(node==null)return;
            Traverse(node.left);
            list.add(node.val);
            Traverse(node.right);
        }
    }
    
  