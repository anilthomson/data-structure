package interview;

public class LowestCommonAncistor {
    TreeNode ans;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int x = find(root, p, q);
        return ans;
    }

    int find(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null)
            return 0;
        int current = (node == p || node == q) ? 1 : 0;
        int left = find(node.left, p, q);
        int right = find(node.right, p, q);
        if (left + right + current >= 2 & ans == null) {
            ans = node;
        }
        return left + right + current;
    }
}
