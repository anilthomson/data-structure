package tree;

class RBTreeNode {
    int val;
    RBTreeNode left;
    RBTreeNode right;
    char color;
    RBTreeNode parent;

    RBTreeNode() {
    }

    RBTreeNode(int data) {
        super();
        this.val = data; // only including data. not key
        this.left = null; // left subtree
        this.right = null; // right subtree
        this.color = 'R'; // colour . either 'R' or 'B'
        this.parent = null; // required at time of rechecking.
    }

    @Override
    public String toString() {

        return String.valueOf(val);
    }

    RBTreeNode(int val, RBTreeNode parent) {
        this.val = val;
        this.parent = parent;
    }

    RBTreeNode(int val, RBTreeNode left, RBTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    RBTreeNode getParent() {
        return parent;
    }

    RBTreeNode getLeft() {
        return left;
    }

    RBTreeNode getRight() {
        return right;
    }
}

public class Rotation {
    public static void main(String[] args) {
        int[] in = { 7, 12, 15, 3, 5, 14, 18, 16, 17 };
        RBTree tree = new RBTree();
        RBTreeNode root = new RBTreeNode(4);
        root.color = 'B';
        for (int num : in) {
            RBTreeNode child = tree.insertAt(root, num);
             System.out.println(child.val);
            tree.preorder(root);
             System.out.println();
            tree.adjustAfterInsertion(child);
            tree.preorder(root);
            System.out.println(" After adj");
        }
        tree.inorder(root);
        System.out.println();
        tree.preorder(root);
        // root.right.left = tree.rotateRight(root.right.left);
        System.out.println();
        tree.preorder(root);
        System.out.println();
        // root.right.left = tree.rotateLeft(root.right.left);
        tree.preorder(root);
        System.out.println();
    }

}

class RBTree {

    RBTreeNode insertAt(RBTreeNode node, int val) {
        RBTreeNode child = null;
        if (val < node.val) {
            if (node.left == null) {
                child = new RBTreeNode(val);
                child.parent = node;
                child.color = 'R';
                node.left = child;
            } else {
              return  insertAt(node.left, val);
            }
        } else {
            if (node.right == null) {
                child = new RBTreeNode(val);
                child.parent = node;
                node.right = child;
            } else {
               return  insertAt(node.right, val);
            }
            // at the same time of insertion, we are also assigning parent nodes
            // also we are checking for RED RED conflicts
        }

        // if (colorclash) {
        // adjustAfterInsertion(child);
        // }
        // System.out.println(colorclash + " " + root.val);
        return child;
    }

    public void inorder(RBTreeNode root) { // ^
        if (root == null)
            return;
        RBTreeNode left = root.left;
        RBTreeNode right = root.right;
        inorder(left);
        System.out.print(root.val + " ");
        inorder(right);
    }

    RBTreeNode rotateRight(RBTreeNode A) {
        if (A == null)
            return A;
        RBTreeNode B = A.left;
        if (B != null) {
            A.left = B.right;
            B.right = A;
            B.parent = A.parent;
        }
        if (A.left != null)
            A.left.parent = A;
        A.parent = B;
        return B;
    }

    RBTreeNode rotateLeft(RBTreeNode A) {
        if (A == null)
            return A;
        RBTreeNode B = A.right;
        if (B != null) {
            A.right = B.left;
            B.left = A;
            B.parent = A.parent;
        }
        if (A.right != null)
            A.right.parent = A;
        A.parent = B;
        return B;
    }

    public void preorder(RBTreeNode root) { // <
        if (root == null)
            return;
        RBTreeNode left = root.left;
        RBTreeNode right = root.right;
        System.out.print(root.val + " " + root.color + " ");
        preorder(left);
        preorder(right);

    }

    public void adjustAfterInsertion(RBTreeNode n) {
      
        // Step 2: Correct double red problems, if they exist
        if (n != null && isRed(parentOf(n))) {
            // Step 2a (simplest): Recolor, and move up to see if more work
            // needed
            System.out.println(" RED RED ");
            if (isRed(siblingOf(parentOf(n)))) {
                setColor(parentOf(n), 'B');
                setColor(siblingOf(parentOf(n)), 'B');
                setColor(grandparentOf(n), 'R');
                adjustAfterInsertion(grandparentOf(n));
            }
            // Step 2b: Restructure for a parent who is the left child of the
            // grandparent. This will require a single right rotation if n is
            // also
            // a left child, or a left-right rotation otherwise.
            else if (parentOf(n) == leftOf(grandparentOf(n))) {
                if (n == rightOf(parentOf(n))) {
                    rotateLeft(n = parentOf(n));
                }
                setColor(parentOf(n), 'B');
                setColor(grandparentOf(n), 'R');
                rotateRight(grandparentOf(n));
            }
            // Step 2c: Restructure for a parent who is the right child of the
            // grandparent. This will require a single left rotation if n is
            // also
            // a right child, or a right-left rotation otherwise.
            else if (parentOf(n) == rightOf(grandparentOf(n))) {
                if (n == leftOf(parentOf(n))) {
                    rotateRight(n = parentOf(n));
                }
                RBTreeNode gp =  grandparentOf(n);
                setColor(parentOf(n), 'B');
                setColor(gp, 'R');
                rotateLeft(gp);
            }  
        }

    }

    private char colorOf(RBTreeNode n) {
        return n == null ? 'B' : n.color;
    }

    private boolean isRed(RBTreeNode n) {
        return n != null && colorOf(n) == 'R';
    }

    private boolean isBlack(RBTreeNode n) {
        return n == null || colorOf(n) == 'B';
    }

    private void setColor(RBTreeNode n, char c) {
        if (n != null)
            n.color = c;
    }

    private RBTreeNode parentOf(RBTreeNode n) {
        return n == null ? null : (RBTreeNode) n.getParent();
    }

    private RBTreeNode grandparentOf(RBTreeNode n) {
        return (n == null || n.getParent() == null) ? null : (RBTreeNode) n.getParent().getParent();
    }

    private RBTreeNode siblingOf(RBTreeNode n) {
        return (n == null || n.getParent() == null) ? null
                : (n == n.getParent().getLeft()) ? (RBTreeNode) n.getParent().getRight()
                        : (RBTreeNode) n.getParent().getLeft();
    }

    private RBTreeNode leftOf(RBTreeNode n) {
        return n == null ? null : (RBTreeNode) n.getLeft();
    }

    private RBTreeNode rightOf(RBTreeNode n) {
        return n == null ? null : (RBTreeNode) n.getRight();
    }
}
