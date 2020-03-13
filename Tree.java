

public class Tree {
	static class Node {
		int data;
		Node left;
		Node right;

		Node(int input) {
			data = input;
		}
	}

	public static void inorder(Node node) { // ^
		if (node == null)
			return;
		Node left = node.left;
		Node right = node.right;
		inorder(left);
		System.out.print(node.data + " ");
		inorder(right);
	}

	public static void postorder(Node node) { // >
		if (node == null)
			return;
		Node left = node.left;
		Node right = node.right;
		postorder(left);
		postorder(right);
		System.out.print(node.data + " ");

	}

	public static void preorder(Node node) { // <
		if (node == null)
			return;
		Node left = node.left;
		Node right = node.right;
		System.out.print(node.data + " ");
		preorder(left);
		preorder(right);

	}

	public static void main(String[] args) {
		Node root = new Node(25);
		Node left = new Node(15);
		Node right = new Node(50);
		root.left = left;
		root.right = right;
		Node lnode = new Node(10);
		Node lnode2 = new Node(22);
		left.left = lnode;
		left.right = lnode2;
		Node rnode = new Node(35);
		Node rnode2 = new Node(70);
		 right.left = rnode;
		right.right = rnode2;
		 Node llnode = new Node(4);
		Node lrnode2 = new Node(12);
		lnode.left = llnode;
		lnode.right = lrnode2;
		 
		Node llnodexy = new Node(18);
		Node lrnode2xy = new Node(24);
		lnode2.left = llnodexy;
		lnode2.right = lrnode2xy;
		
		Node rlnode = new Node(31);
		Node rrnode2 = new Node(44);
		rnode.left = rlnode;
		rnode.right = rrnode2;
		Node rlnodex = new Node(66);
		Node rrnode2x = new Node(90);
		rnode2.left = rlnodex;
		rnode2.right = rrnode2x;
		System.out.println("Inorder");
		inorder(root);
		System.out.println("\nPreorder");
		preorder(root);
		System.out.println("\nPostorder");
		postorder(root);
	}
}
