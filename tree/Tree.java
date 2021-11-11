package tree;

import javax.print.attribute.standard.Fidelity;

public class Tree {
	static class Node {
		int data;
		Node left;
		Node right;

		Node() {
		}

		Node(int input) {
			data = input;
		}

		@Override
		public String toString() {

			return "Node " + String.valueOf(data);
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

	static Node FIRST;
	static Node LAST;

	public static Node Convert(Node node) { // ^
		if (node == null)
			return null;

		Node left = node.left;
		Node right = node.right;

		Node leftN = Convert(left);
		System.out.print(node.data + " ");
		Node listNode = new Node(node.data);
		if (LAST != null) {
			LAST.right = listNode;
			listNode.left = LAST;
		} else {
			FIRST = listNode;
		}
		LAST = listNode;
		Node rightN = Convert(right);

		return listNode;
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
		System.out.println("\nLinkedlist");
		Node head = new Node();
		// Node listNode = Convert(root);
		Node listNode = Convert(root, null);
		System.out.println("\n-xxx----");
		// PrintLR(listNode, listNode.left);
		// FIRST.left=LAST;
		// LAST.right=FIRST;
		System.out.println("-----");
		// PrintLR(FIRST, FIRST.left);
		System.out.println("-----");
		// PrintLL(FIRST, FIRST.right);
		System.out.println("-----");
		// inorder(root);
		System.out.println("-----");
		// inorder(FIRST);

	}

	static void PrintLR(Node start, Node node) {
		System.out.print(start.data + " ");
		if (start == node)
			return;
		if (start.right != null) {
			PrintLR(start.right, node);
		}

	}

	static void PrintLL(Node start, Node node) {
		System.out.println(start.data);
		if (start == node)
			return;
		if (start.left != null) {
			PrintLL(start.left, node);
		}
	}

	public static Node Convert(Node node, Node lastCreated) { // ^
		if (node == null)
			return null;

		Node left = node.left;
		Node right = node.right;

		Node leftN = Convert(left, lastCreated);
		// System.out.print(node.data + " ");
		Node listNode = new Node(node.data);
		if (lastCreated == null && leftN != null) {
			lastCreated = leftN.right;
		}
		if (FIRST == null)
			FIRST = listNode;
		if (leftN != null && lastCreated == null) {
			listNode.left = leftN;
			leftN.right = listNode;
		}
		Node rightN = Convert(right, listNode);
		listNode.left = lastCreated;
		listNode.right = rightN;
		if (lastCreated != null)
			lastCreated.right = listNode;
		PrintLR(FIRST, FIRST.left);
		System.out.println();
		return listNode;
	}
}
