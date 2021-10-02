package tree;
class TreeNode {
    int data, height;
    TreeNode left, right;

    TreeNode(int d) {
        data = d;
        height = 1;
    }
}

public class AVLTree2 {
    TreeNode root;

    int height(TreeNode N) {
        if (N == null)
            return 0;

        return N.height;
    }

    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Get Balance factor of TreeNode N
    int getBalance(TreeNode N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    void preOrder(TreeNode TreeNode) {
        if (TreeNode != null) {
            System.out.println(TreeNode.data + " Height " + TreeNode.height);
            preOrder(TreeNode.left);
            preOrder(TreeNode.right);
        }
    }

    void inOrder(TreeNode TreeNode) {
        if (TreeNode != null) {
            inOrder(TreeNode.left);

            System.out.println(TreeNode.data);

            inOrder(TreeNode.right);
        }
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    TreeNode insert(TreeNode TreeNode, int data) {

        /* 1. Perform the normal BST insertion */
        if (TreeNode == null)
            TreeNode = new TreeNode(data);
        if (data < TreeNode.data) {
            TreeNode.left = insert(TreeNode.left, data);

        } else if (data > TreeNode.data) {
            TreeNode.right = insert(TreeNode.right, data);

        } else // Duplicate datas not allowed
            return TreeNode;
        TreeNode.height = 1 + max(height(TreeNode.left), height(TreeNode.right));
        int balance = getBalance(TreeNode);
        System.err.println("balance " + balance + " Data " + data);
        // If this TreeNode becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && data < TreeNode.left.data)
            System.out.println("Left Left Case - 1 ");
        // return rightRotate(TreeNode);

        // Right Right Case
        if (balance < -1 && data > TreeNode.right.data)
            System.out.println("Right Right Case - 2 ");
        // return leftRotate(TreeNode);

        // Left Right Case
        if (balance > 1 && data > TreeNode.left.data) {
            System.out.println("Left Right Case - 3 ");
            // TreeNode.left = leftRotate(TreeNode.left);
            // return rightRotate(TreeNode);
        }

        // Right Left Case
        if (balance < -1 && data < TreeNode.right.data) {
            System.out.println("Right Left Case - 4 ");
            // TreeNode.right = rightRotate(TreeNode.right);
            // return leftRotate(TreeNode);
        }
        return TreeNode;
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 60);
        tree.root = tree.insert(tree.root, 55);
        // tree.preOrder(tree.root);
        // tree.root = tree.insert(tree.root,50);
        // tree.insert(tree.root,30);
        // tree.insert(tree.root,20);
        // tree.insert(tree.root,40);
        // tree.insert(tree.root,70);
        // tree.insert(tree.root,60);
        // tree.insert(tree.root,80);
        // tree.inOrder(tree.root);
    }
}
