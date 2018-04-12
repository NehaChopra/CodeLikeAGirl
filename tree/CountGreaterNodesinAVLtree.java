//https://www.geeksforgeeks.org/count-greater-nodes-in-avl-tree/
package tree;

class Node{
	int data;
	Node leftChild;
	Node rightChild;
	int height;
	int desc;
	Node(int data){
		this.data = data;
		this.leftChild = null;
		this.rightChild = null;
		this.height = 1;
		this.desc = 0;
	}
}

class AVL{
	Node root;
	public static Node insert(Node root, int data){
		if(root == null){
			Node node = new Node(data);
			return node;
		}else{
			if(root.data > data){
				root.leftChild = insert(root.leftChild, data);
				root.desc = root.desc + 1;
			}else if(root.data < data){
				root.rightChild = insert(root.rightChild, data);
				root.desc = root.desc + 1;
			}else{
				return root;
			}
			
			root.height = 1 + Math.max(getHeight(root.rightChild), getHeight(root.leftChild));
			int balanceFactor = getHeight(root.leftChild) - getHeight(root.rightChild);
			
			if(balanceFactor > 1 && root.leftChild.data > data){
				//Left-Left Scenario
				return rotateRight(root);
			}
			if(balanceFactor > 1 && root.leftChild.data < data){
				//Left-right Scenario
				root.leftChild = rotateLeft(root.leftChild);
				return rotateRight(root);
			}
			if(balanceFactor < -1 && root.rightChild.data > data){
				//right- left Scenario
				root.rightChild = rotateRight(root.rightChild);
				return rotateLeft(root);
			}
			if(balanceFactor < -1 && root.rightChild.data < data){
				//right- right Scenario
				return rotateLeft(root);
			}
			
		}
		return root;
	}
	public static int getHeight(Node root){
		if(root==null){
			return 0;
		}
		return root.height;
	}
	public static Node rotateLeft(Node root){
		Node x = root.rightChild;
		Node y = x.leftChild;
		
		root.rightChild = y;
		x.leftChild = root;
		
		root.height = 1 + Math.max(getHeight(root.rightChild), getHeight(root.leftChild));
		x.height = 1 + Math.max(getHeight(root.rightChild), getHeight(root.leftChild));
		
		int yDesc = ( y != null ) ? y.desc : -1;
		root.desc = root.desc - (x.desc + 1) + (yDesc + 1);
		x.desc = x.desc + (root.desc + 1) - (yDesc + 1);
				
		return x;
		
	}
	public static Node rotateRight(Node root){
		Node x = root.leftChild;
		Node y = x.rightChild;
		
		root.leftChild = y;
		x.rightChild = root;
		
		root.height = 1 + Math.max(getHeight(root.rightChild), getHeight(root.leftChild));
		x.height = 1 + Math.max(getHeight(root.rightChild), getHeight(root.leftChild));
		
		int yDesc = ( y != null ) ? y.desc : -1;
		root.desc = root.desc - (x.desc + 1) + (yDesc + 1);
		x.desc = x.desc + (root.desc + 1) - (yDesc + 1);
			
		return x;
	}
}
class CountGreaterNodesinAVLtree {
	public static void main(String args[]){
		AVL obj = new AVL();
		obj.root = obj.insert(obj.root, 9);
		obj.root = obj.insert(obj.root, 5);
		obj.root = obj.insert(obj.root, 10);
		obj.root = obj.insert(obj.root, 0);
		obj.root = obj.insert(obj.root, 6);
		obj.root = obj.insert(obj.root, 11);
		obj.root = obj.insert(obj.root, -1);
		obj.root = obj.insert(obj.root, 1);
		obj.root = obj.insert(obj.root, 2);
		
		preOrderPrint(obj.root);
	}
	public static void preOrderPrint(Node root){
		if(root!=null){
			preOrderPrint(root.leftChild);
			System.out.println(root.data + "............. "+root.desc);
			preOrderPrint(root.rightChild);
		}
	}
	public static int findLargestTheNode(int data, Node root, int count){
		if(root.data == data){
			return root.desc;
		}else if(root.data < data){
			findLargestTheNode(data, root.rightChild, count);
		}else if(root.data > data){
			findLargestTheNode(data, root.leftChild, count);
		}
		return 0;
	}
	public static int countLargerElements(Node root, int key){
		if(root.data == key){
			return root.desc;
		}else if(root.data > key){
			return countLargerElements(root.leftChild, key);
		}else{
			return countLargerElements(root.rightChild, key);
		}
	}
}