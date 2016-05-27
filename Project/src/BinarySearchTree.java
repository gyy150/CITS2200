/**
 * a binary tree used to store vertex value, and assign vertex index to each vertex
 * @author young
 *
 */
public class BinarySearchTree {
	private int data;
	private int VertexIndex;
	private BinarySearchTree left;
	private BinarySearchTree right;
	static 	int IndexCounter = 0;											//initialize the index to be -1

	
	public BinarySearchTree(int num) {
		this.data = num;
		this.left = null;
		this.right = null;
		this.VertexIndex =  IndexCounter;
	}
	public int addNode(int num) {
		if (num < this.data) {
			if (this.left != null) {
				return this.left.addNode(num);
			} 
			else {
				
				IndexCounter = IndexCounter+1;						//increase the vertext index by one when a new vertex come in
				this.left = new BinarySearchTree(num);
				return IndexCounter;
			}

		} 
		else if(num > this.data) {
			if (this.right != null) {
				return this.right.addNode(num);
			}
			else {
				
				IndexCounter = IndexCounter+1;						////increase the vertext index by one when a new vertex come in
				this.right = new BinarySearchTree(num);
				return IndexCounter;
			}

		}
		else{																//if this node is the same as the incoming vertex
			return this.VertexIndex;											//return false if the incomming vertex already exist
		}
	}
	
}
