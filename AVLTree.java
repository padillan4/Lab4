  
public class AVLTree {
	Node root = new Node();
	
	@SuppressWarnings("unchecked")
	public boolean search(Comparable data){
		Node curr = root;
		boolean found = false;
		while(!found) {
			if(curr.getData().equals(data)) {
				return true;
			}
			//less than current
			else if(curr.getData().compareTo(data) > 0 ) {
				if(!(curr.getLeftChild() == null)) {
					curr = curr.getLeftChild();
				}
				else {
					return false;
				}
			}
			//greater than/equal to current
			else if(curr.getData().compareTo(data) <= 0) {
				if(!(curr.getRightChild() == null)) {
					curr = curr.getRightChild();
				}
				else {
					return false;
				}
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public void insert(Node addNode) {
		Node curr = root;
		boolean inserted = false;
		//inserting node
		while(!inserted) {
			//tree is empty
			if(root.isEmpty()) {
				root = addNode;
				inserted = true;
			}
			//less than current
			else if(curr.getData().compareTo(addNode.getData()) > 0) {
				if(curr.getLeftChild() == null) {
					curr.setLeftChild(addNode);
					curr.getLeftChild().setParent(curr);
					curr = curr.getLeftChild();
					inserted = true;
				}
				else {
					curr = curr.getLeftChild();
				}
			}
			//greater than/equal to current
			else if(curr.getData().compareTo(addNode.getData()) <= 0) {
				if(curr.getRightChild() == null) {
					curr.setRightChild(addNode);
					curr.getRightChild().setParent(curr);
					curr = curr.getRightChild();
					inserted = true;
				}
				else {
					curr = curr.getRightChild();
				}
			}
		}
		//updating node heights
		System.out.println("inserted node" + addNode.getData());
		int rootCalced = 0;
		while(curr.getParent() != null || (curr.equals(root) && rootCalced == 0)) {
			if(curr.getLeftChild() == null && curr.getRightChild() == null) {
				curr.setHeight(0);
			}
			else if(curr.getLeftChild() != null && curr.getRightChild() == null) {
				curr.setHeight(1 + curr.getLeftChild().getHeight());
			}
			else if(curr.getRightChild() != null && curr.getLeftChild() == null) {
				curr.setHeight(1 + curr.getRightChild().getHeight());
			}
			else {
				curr.setHeight(1 + Math.max(curr.getLeftChild().getHeight(), curr.getRightChild().getHeight()));
			}
			//checking balance factors
			if(curr.getParent() != null){
				//left left case
				if(getBalanceFactor(curr) == -1 && getBalanceFactor(curr.getParent()) == -2){
					rotateRight(curr, curr.getLeftChild(), curr.getParent());
				}
				//left right case
				else if(getBalanceFactor(curr) == -1 && getBalanceFactor(curr.getParent()) == 2){
					Node y = curr.getParent();
					rotateLeft(curr.getRightChild(), , curr);
					rotateRight(curr)
				}
				//right right case
				else if(getBalanceFactor(curr) == 1 && getBalanceFactor(curr.getParent()) == 2){
					rotateLeft(curr, curr.getRightChild(), curr.getParent());
				}
				//right left case
				else if( getBalanceFactor(curr) == 1 && getBalanceFactor(curr.getParent()) == -2){
					
				}
			}
			else{
				if(getBalanceFactor(curr) == -2){
					
				}
				else if(getBalanceFactor(curr) == 2){
					
				}
			}
			if(curr.getParent() == null) {
				rootCalced = 1;
			}
			
		}
	}
	
	public Node delete() {
		return root;
	}
	
	public void inOrder(Node curr) {
		if (curr == null) {
			System.out.print("null ");
		} 
		else {
			inOrder(curr.getLeftChild());
			System.out.print(curr.getData() + " ");
			inOrder(curr.getRightChild());
		}
	}
	
	@SuppressWarnings("unchecked")
	public int count(Node root, Comparable lowerBound, Comparable upperBound) {
		Node curr = root;
		int count = 0;
		
		if(curr.getData().compareTo(lowerBound) >= 0 && curr.getData().compareTo(upperBound) <= 0) {
			count++;
		}
		if(curr.getRightChild() != null) {
			if(curr.getRightChild().getData().compareTo(lowerBound) >= 0 && curr.getRightChild().getData().compareTo(upperBound) <= 0) {
				count = count + count(curr.getRightChild(),lowerBound,upperBound);
			}
		}
		if(curr.getLeftChild() != null) {
			if(curr.getLeftChild().getData().compareTo(lowerBound) >= 0 && curr.getLeftChild().getData().compareTo(upperBound) <= 0) {
				count = count + count(curr.getLeftChild(),lowerBound,upperBound);
			}
		}
		return count;
	}
	
	public Node getRoot() {
		return root;
	}
	
	private Node rotateRight(Node y) {
		Node y = x.right;
        Node T2 = y.left;
        System.out.println("rotating left at: " + x + " " + y + " " + child);
        
        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
	}
	
	private Node rotateLeft(Node x) {
		Node y = x.getRightChild();
        Node child = y.getLeftChild();
        System.out.println("rotating left at: " + x + " " + y + " " + child);
        
        x = y .getLeftChild();
        child = x.getRightChild();
        x.setHeight(Math.max(x.getLeftChild().getHeight(), x.getRightChild().getHeight()) + 1);
        y.setHeight(Math.max(y.getLeftChild().getHeight(), y.getRightChild().getHeight()) + 1);
        return y;
	}
	
	private int getBalanceFactor(Node node) {
		if(node.getRightChild() == null && node.getLeftChild() != null) {
			return -(node.getLeftChild().getHeight() + 1);
		}
		else if(node.getLeftChild() == null && node.getRightChild() != null) {
			return node.getRightChild().getHeight() + 1;
		}
		else if(node.getLeftChild() == null && node.getRightChild() == null) {
			return 0;
		}
		else {
			return node.getRightChild().getHeight() - node.getLeftChild().getHeight();
		}
	}
}
