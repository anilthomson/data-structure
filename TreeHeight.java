import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TreeHeight {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(5);
        // TreeNode node3 = new TreeNode(4);
        // TreeNode node4 = new TreeNode(3);
        root.left = left;
        root.right = right;
        right.left = node1;
        right.right = node2;
        // left.right.left = node3;
        // left.right.left.right = node4;
        int height = height(root);
        StringBuilder sb = new StringBuilder();

        System.out.println("height " + height);
        for (int i = 0; i <= height; i++) {
            // printGivenLevel(root, i);
            add(root, i, sb);
        }
        sb.deleteCharAt(sb.length() - 1);
        String data = sb.toString();
        System.out.println("data " + data);
        List<String> list = Stream.of(data.split(",")).map(elem -> new String(elem)).collect(Collectors.toList());
        List<TreeNode> parents = new ArrayList<>();
        String val = list.remove(0);
        if (!val.equals("null")) {
            parents.add(new TreeNode(Integer.parseInt(val)));
            build(list, parents);
        }

        for (int i = 0; i <= height; i++) {
            printGivenLevel(parents.get(0), i);
        }
    }

    static void printGivenLevel(TreeNode root, int level) {
        if (root == null)
            System.out.print(" null ");
        else if (level == 1)
            System.out.print(root.val + " ");
        else if (level > 1) {
            printGivenLevel(root.left, level - 1);
            printGivenLevel(root.right, level - 1);
        }
    }

    public String serialize(TreeNode root) {
        int height = height(root);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= height; i++) {
            add(root, i, sb);
        }
        if (sb.length() > 0)
            sb.deleteCharAt(sb.length() - 1);
        System.out.println("Serialize " + sb.toString());
        if (sb.length() == 0)
            return null;
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null)
            return null;
        List<String> list = Stream.of(data.split(",")).map(elem -> new String(elem)).collect(Collectors.toList());
        List<TreeNode> parents = new ArrayList<>();
        String val = list.remove(0);
        if (val.equals("null")) {
            return null;
        } else {
            parents.add(new TreeNode(Integer.parseInt(val)));
            System.out.println(list);
            build(list, parents);
        }
        return parents.get(0);
    }

    private static int height(TreeNode node) {
        if (node == null)
            return 0;
        int lefth = height(node.left);
        int righth = height(node.right);
        // System.out.println(node.data+" "+lefth+" "+righth);
        if (lefth > righth)
            return lefth + 1;
        else
            return righth + 1;
    }

    static void build(List<String> list, List<TreeNode> parents) {
        if (list.isEmpty())
            return;
        List<TreeNode> children = new ArrayList<>();
        for (TreeNode parent : parents) {
            if (list.isEmpty())
                return;
            String data = list.remove(0);
            if (data.equals("null"))
                parent.left = null;
            else {
                parent.left = new TreeNode(Integer.parseInt(data));
                children.add(parent.left);
            }
            if (list.isEmpty())
                return;
            data = list.remove(0);
            if (data.equals("null"))
                parent.right = null;
            else {
                parent.right = new TreeNode(Integer.parseInt(data));
                children.add(parent.right);
            }

        }
        if (children.size() != 0)

            build(list, children);
        return;
    }

    static void add(TreeNode root, int level, StringBuilder sb) {
        if (level == 1) {
            if (root == null)
                sb.append("null,");
            else
                sb.append(root.val + ",");
        }

        else if (level > 1 && root != null) {
            add(root.left, level - 1, sb);
            add(root.right, level - 1, sb);
        }
    }
}