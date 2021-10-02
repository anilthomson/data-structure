package tree;
public class BinarySearchTree2 {
    // Root of BST
    Node root;

    class Node {

        int data;
        Node left, right;

        Node(int d) {
            data = d;
            left = right = null;
        }
    }

    // A utility function to print inorder traversal of a Binary Tree
    void printInorder(Node node) {
        if (node == null) {
            return;
        }
        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }

    // This method mainly calls insertRec()
    void insert(int key) {
        root = insertRec(root, key);
    }

    /* A recursive function to insert a new key in BST */
    Node insertRec(Node root, int key) {

        /* If the tree is empty, return a new node */
        if (root == null) {
            root = new Node(key);
            return root;
        }

        /* Otherwise, recur down the tree */
        if (key < root.data)
            root.left = insertRec(root.left, key);
        else if (key > root.data)
            root.right = insertRec(root.right, key);

        /* return the (unchanged) node pointer */
        return root;
    }

    // A utility function to search a given key in BST
    public Node search(Node root, int key) {
        // Base Cases: root is null or key is present at root
        if (root == null || root.data == key)
            return root;

        // val is greater than root's key
        if (root.data > key)
            return search(root.left, key);

        // val is less than root's key
        return search(root.right, key);
    }

    public static void main(String[] args) {
        BinarySearchTree2 tree = new BinarySearchTree2();
        int arr[] = { 50, 30, 20, 40, 70, 60, 90, 80 };
        /*
         * Let us create following BST 50 / \ 30 70 / \ / \ 20 40 60 80
         */
        for (int element : arr) {
            tree.insert(element);
        }
        Node search = tree.search(tree.root, 91);
        System.out.println(search);
        tree.printInorder(tree.root);
    }
}