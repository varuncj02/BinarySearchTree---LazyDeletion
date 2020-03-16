/**
 * 
 * @author Varun Joshi
 * CS 3345
 *This class is an implementation of a binary search tree with lazy deletion
 *
 *
 */
public class LazyBinarySearchTree {
	
	
	private static class TreeNode {
		
		private int key;
		private TreeNode leftChild;
		private TreeNode rightChild;
		private boolean deleted;
		
		// Constructors
		TreeNode(int element) { 
			this( element , null, null ); 
		}
		
		TreeNode(int element, TreeNode left, TreeNode right) {
			this.key = element;
			this.leftChild = left;
			this.rightChild = right;
		}
		
		public int getKey() {
			return key;
		}
		
		public TreeNode getLeftChild() {
			return leftChild;
		}
		
		public TreeNode getRightChild() {
			return rightChild;
		}
		
		public boolean isDeleted(){
			return deleted;
		}
		
		public void setLeftChild(TreeNode lt) {
			this.leftChild = lt;
		}
		
		public void setRightChild(TreeNode rt) {
			this.rightChild = rt;
		}
		
		public void setDeleted(boolean deleted) {
			this.deleted = deleted;
		}
		
		
	}
	
	private TreeNode root;

	// Default Constructor 
	public LazyBinarySearchTree() {
		root = null;
	}
	
	/**
	 * 
	 * @param key The number to be inserted in the bst
	 * @return Whether there was a logical insertion
	 * @throws IllegalArgumentException
	 */
	public boolean insert(int key) throws IllegalArgumentException {
		if(key < 1 || key > 99) {
			throw new IllegalArgumentException("Error in Input");
		}
		
		if(this.root != null) {
			return insert(root, key);
		}
		else {
			root = new TreeNode(key);
			return true;
		}
		
	}
	
	private boolean insert (TreeNode cNode, int key) {
		boolean inserted = false;
		
		if(key < cNode.getKey()) {
			if(cNode.getLeftChild() != null) {
				inserted = insert(cNode.getLeftChild(), key);
			}
			else {
				cNode.setLeftChild(new TreeNode(key));
				inserted = true;
			}
		}
		else if(key > cNode.getKey()) {
			if(cNode.getRightChild() != null) {
				inserted = insert(cNode.getRightChild(), key);
			}
			else {
				cNode.setRightChild(new TreeNode(key));
				inserted = true;
			}
		}
		else {
			if(cNode.isDeleted()) {
				cNode.setDeleted(false);
				inserted = true;
			}
			else {
				inserted = false;
			}
		}
		return inserted;
	}
	
	
	
	/**
	 * 
	 * @param key the number to be deleted from the bst
	 * @return Whether the element was deleted from the bst
	 * @throws IllegalArgumentException
	 */
	public boolean delete(int key) throws IllegalArgumentException {
		if(key < 1 || key > 99) {
            throw new IllegalArgumentException("Error in Input");
		}
		
		if(this.root != null) {
			return delete(this.root, key);
		}
		else {
			return false;
		}
	      	
	}
	
	private boolean delete (TreeNode cNode, int key) {
		boolean deleted = false;
		
		if(key < cNode.getKey()) {
			if(cNode.getLeftChild() != null) {
				deleted = delete(cNode.getLeftChild(), key);
			}
			else {
				deleted = false;
			}
		}
		else if (key > cNode.getKey()) {
			if(cNode.getRightChild() != null) {
				deleted = delete(cNode.getRightChild(), key);
			}
			else {
				deleted = false;
			}
		}
		else {
			if(cNode.isDeleted()) {
				deleted = false;
			}
			else {
				cNode.setDeleted(true);
				deleted = true;
			}
		}
		return deleted;
	}
	
	/**
	 * 
	 * @return The minimum value in the BST or -1 if it doesnt exist
	 */
	public int findMin() {
		if(this.root == null) {
			return -1;
        }
		else {
			return findMin(root);
		}
	}
	
	private int findMin(TreeNode cNode) {
		if(cNode == null) {
			return -1;
		} 
		else if(leftSubTreeExists(cNode)) {
			return findMin(cNode.getLeftChild());
		} 
		else if(!cNode.isDeleted()) {
			return cNode.getKey();
		} 
		else if(rightSubTreeExists(cNode)) {
			return findMin(cNode.getRightChild());
		} 
		return -1;
	}
	
	/**
	 * 
	 * @return The maximum value in the BST or -1 if it doesn't exist
	 */
	public int findMax() {
		if(this.root == null) {
			return -1;
        }
		else {
			return findMax(root);
		}
	}
	
	private int findMax(TreeNode cNode) {
		if(cNode == null) {
			return -1;
		} 
		else if(rightSubTreeExists(cNode)) {
			return findMin(cNode.getLeftChild());
		} 
		else if(!cNode.isDeleted()) {
			return cNode.getKey();
		} 
		else if(leftSubTreeExists(cNode)) {
			return findMin(cNode.getRightChild());
		} 
		return -1;
	}
	
	// Traversal of the leftSubTree
	private boolean leftSubTreeExists(TreeNode cNode) {
		boolean exists = false;
			
		if(cNode == null) {
			exists = false;
		} 
		if(cNode.getLeftChild() != null) {
			if(!cNode.getLeftChild().isDeleted()) {
				exists = true;
			} 
			else if(leftSubTreeExists(cNode.getLeftChild()) || rightSubTreeExists(cNode.getLeftChild())){
				exists = true;
			} 
			else {
				exists = false;
			}
		
		}
		return exists;
	}
		
	// Traversal of the rightSubTree
	private boolean rightSubTreeExists(TreeNode cNode) {
		boolean exists = false;
			
		if(cNode == null) {
			exists = false;
		} 
		if(cNode.getRightChild() != null) {
			if(!cNode.getRightChild().isDeleted()) {
				exists = true;
			} 
			else if(rightSubTreeExists(cNode.getRightChild()) || leftSubTreeExists(cNode.getRightChild())){
				exists = true;
			} 
			else {
				exists = false;
			}
		}
		return exists;
	}
	
	/**
	 * 
	 * @param key The value that is to be checked for in the BST
	 * @return Whether the BST contains that particular value or not
	 * @throws IllegalArgumentException
	 */
	public boolean contains(int key) throws IllegalArgumentException {
		if(key < 1 || key > 99) {
			throw new IllegalArgumentException("Error in Input");
		}
		if(root != null) {
			return contains(this.root, key);
		}
		return false;
	}
	
	private boolean contains(TreeNode cNode,int key) {
		boolean found = false;
		
		if (key < cNode.getKey()) {
            if (cNode.getLeftChild() != null) {
                found = contains(cNode.getLeftChild(), key);
            }
            else {
                found = false;
            }
        } 
		else if (key > cNode.getKey()) {
            if (cNode.getRightChild() != null) {
                found = contains(cNode.getRightChild(), key);
            }
            else {
                found = false;
            }
        } 
		else { 
            if (cNode.isDeleted()) {
                found = false;
            }    
            else
                found = true;
        }
		
        return found;
	}
	
	/**
	 * @return pre-order traversal of the BST
	 */
	public String toString() {		
		return toString(root);
	}
	
	private String toString(TreeNode cNode) {
		String output = "";
		if(cNode == null) {
			return "";
		}
		if(cNode.isDeleted()) {
			output += "*" + cNode.getKey() + " ";
		}
		else {
			output += cNode.getKey() + " ";
		}
		String leftHalf = "";
		String rightHalf = "";
		leftHalf = toString(cNode.getLeftChild());
		rightHalf = toString(cNode.getRightChild());
		return output + leftHalf + rightHalf;
	}
	
	/**
	 * 
	 * @return the maximum height of a subtree including the deleted elements
	 */
	public int height() {
		if(root == null) {
			return 0;
		}
		else {
			 return Math.max(height(root.getLeftChild()),height(root.getRightChild()));
		}
	}
	
	private int height(TreeNode cNode) {
		if(cNode == null) {
			return -1;
		}
		
		int leftHeight = height(cNode.getLeftChild());
		int rightHeight = height(cNode.getRightChild());
		
		if(leftHeight > rightHeight) {
			return leftHeight + 1;
		} 
		else {
			return rightHeight + 1;
		}
	}
	
	/**
	 * 
	 * @return the number of elements in the bst including the deleted elements
	 */
	public int size() {
		if(root != null) {
			return size(this.root, 0);
		}
		else {
			return 0;
		}
	}
	
	
	
	private int size(TreeNode cNode, int counter) {
		if(cNode != null) {
			counter += 1;
		}
		
		counter += size(cNode.getLeftChild(), counter);
		counter += size(cNode.getRightChild(), counter);
		
		return counter;
	}
}


