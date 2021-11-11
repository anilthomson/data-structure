package tree;

/*package whatever //do not write package name here */

import java.io.*;

// considering that you know what are red-black trees here is the implementation in java for insertion and traversal.
// RedBlackTree class. This class contains subclass for node
// as well as all the functionalities of RedBlackTree such as - rotations, insertion and
// inoredr traversal
public class RedBlackTree
{
	public Node root;//root node
	public RedBlackTree()
	{
		super();
		root = null;
	}
	// node creating sublass
	class Node
	{
		int data;
		Node left;
		Node right;
		char colour;
		Node parent;

		Node(int data)
		{
			super();
			this.data = data; // only including data. not key
			this.left = null; // left subtree
			this.right = null; // right subtree
			this.colour = 'R'; // colour . either 'R' or 'B'
			this.parent = null; // required at time of rechecking.
		}
	}
	// this function performs left rotation
	Node rotateLeft(Node node)
	{
		Node x = node.right;
		Node y = x.left;
		x.left = node;
		node.right = y;
		node.parent = x; // parent resetting is also important.
		if(y!=null)
			y.parent = node;
		return(x);
	}
	//this function performs right rotation
	Node rotateRight(Node node)
	{
		Node x = node.left;
		Node y = x.right;
		x.right = node;
		node.left = y;
		node.parent = x;
		if(y!=null)
			y.parent = node;
		return(x);
	}


	// these are some flags.
	// Respective rotations are performed during traceback.
	// rotations are done if flags are true.
	boolean ll = false;
	boolean rr = false;
	boolean lr = false;
	boolean rl = false;
	// helper function for insertion. Actually this function performs all tasks in single pass only.
	Node insertHelp(Node node, int data)
	{
		// f is true when RED RED conflict is there.
		boolean redred=false;
		
		//recursive calls to insert at proper position according to BST properties.
		if(node==null)
			return(new Node(data));
		else if(data<node.data)
		{
			node.left = insertHelp(node.left, data);
			node.left.parent = node;
			if(node!=this.root)
			{
				if(node.colour=='R' && node.left.colour=='R')
				redred = true;
			}
		}
		else
		{
			node.right = insertHelp(node.right,data);
			node.right.parent = node;
			if(node!=this.root)
			{
				if(node.colour=='R' && node.right.colour=='R')
				redred = true;
			}
		// at the same time of insertion, we are also assigning parent nodes
		// also we are checking for RED RED conflicts
		}

		// now lets rotate.
		if(this.ll) // for left rotate.
		{
			node = rotateLeft(node);
			node.colour = 'B';
			node.left.colour = 'R';
			this.ll = false;
		}
		else if(this.rr) // for right rotate
		{
			node = rotateRight(node);
			node.colour = 'B';
			node.right.colour = 'R';
			this.rr = false;
		}
		else if(this.rl) // for right and then left
		{
			node.right = rotateRight(node.right);
			node.right.parent = node;
			node = rotateLeft(node);
			node.colour = 'B';
			node.left.colour = 'R';

			this.rl = false;
		}
		else if(this.lr) // for left and then right.
		{
			node.left = rotateLeft(node.left);
			node.left.parent = node;
			node = rotateRight(node);
			node.colour = 'B';
			node.right.colour = 'R';
			this.lr = false;
		}
		// when rotation and recolouring is done flags are reset.
		// Now lets take care of RED RED conflict
		if(redred)
		{
			if(node.parent.right == node) // to check which child is the current node of its parent
			{
				if(node.parent.left==null || node.parent.left.colour=='B') // case when parent's sibling is black
				{// perform certaing rotation and recolouring. This will be done while backtracking. Hence setting up respective flags.
					if(node.left!=null && node.left.colour=='R')
						this.rl = true;
					else if(node.right!=null && node.right.colour=='R')
						this.ll = true;
				}
				else // case when parent's sibling is red
				{
					node.parent.left.colour = 'B';
					node.colour = 'B';
					if(node.parent!=this.root)
						node.parent.colour = 'R';
				}
			}
			else
			{ // node is left of parent;
				if(node.parent.right==null || node.parent.right.colour=='B')
				{
					if(node.left!=null && node.left.colour=='R')
						this.rr = true;
					else if(node.right!=null && node.right.colour=='R')
						this.lr = true;
				}
				else
				{
					node.parent.right.colour = 'B';
					node.colour = 'B';
					if(node.parent!=this.root)
						node.parent.colour = 'R';
				}
			}
			redred = false;
		}
		return(node);
	}

	// function to insert data into tree.
	public void insert(int data)
	{
		if(this.root==null)
		{
			this.root = new Node(data);
			this.root.colour = 'B';
		}
		else
			this.root = insertHelp(this.root,data);
	}
	// helper function to print inorder traversal
	void inorderTraversalHelper(Node node)
	{
		if(node!=null)
		{
			inorderTraversalHelper(node.left);
			System.out.printf("%d ", node.data);
			inorderTraversalHelper(node.right);
		}
	}
	//function to print inorder traversal
	public void inorderTraversal()
	{
		inorderTraversalHelper(this.root);
	}
	// helper function to print the tree.
	void printTreeHelper(Node root, int space)
	{
		int i;
		if(root != null)
		{
			space = space + 10;
			printTreeHelper(root.right, space);
			System.out.printf("\n");
			for ( i = 10; i < space; i++)
			{
				System.out.printf(" ");
			}
			System.out.printf("%d", root.data);
			System.out.printf("\n");
			printTreeHelper(root.left, space);
		}
	}
	// function to print the tree.
	public void printTree()
	{
		printTreeHelper(this.root, 0);
	}
	public static void main(String[] args)
	{
		// let us try to insert some data into tree and try to visualize the tree as well as traverse.
		RedBlackTree t = new RedBlackTree();
		int[] arr = {1,4,6,3,5,7,8,2,9};
		for(int i=0;i<9;i++)
		{
			t.insert(arr[i]);
			System.out.println();
			t.inorderTraversal();
		}
		// you can check colour of any node by with its attribute node.colour
		t.printTree();
	}
}
