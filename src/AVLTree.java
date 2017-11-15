
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
		//updating balancing factors
		System.out.println("inserted node" + addNode.getData());
		int rootCalced = 0;
		Node temp = curr;
		while(temp.getParent() != null || (temp.equals(root) && rootCalced == 0)) {
			
			if(temp.getLeftChild() == null && temp.getRightChild() == null) {
				temp.setHeight(0);
				temp = temp.getParent();
			}
			else if(temp.getLeftChild() != null && temp.getRightChild() == null) {
				temp.setHeight(1 + temp.getLeftChild().getHeight());
				temp = temp.getParent();
			}
			else if(temp.getRightChild() != null && temp.getLeftChild() == null) {
				temp.setHeight(1 + temp.getRightChild().getHeight());
				temp = temp.getParent();
			}
			else {
				temp.setHeight(1 + Math.max(temp.getLeftChild().getHeight(), temp.getRightChild().getHeight()));
				temp = temp.getParent();
			}
			if(temp.getParent() == null) {
				rootCalced = 1;
			}
			/**System.out.println("bf " + curr.getData() + " " +getBalanceFactor(curr));
			if(curr.getParent() != null) {
				curr = curr.getParent();
			}
			else {
				rootCalced = 1;
			}**/
		}
		rootCalced = 0;
		while(curr.getParent() != null || (curr.equals(root) && rootCalced == 0)) {
			System.out.println("bf " + curr.getData() + " " + getBalanceFactor(curr));
			if(curr.getParent() != null) {
				curr = curr.getParent();
			}
			else {
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
	
	private void rotateRight(Node r, Node x, Node y) {
		
	}
	
	private void rotateLeft(Node r, Node x, Node y) {
		
	}
	
	private int getBalanceFactor(Node node) {
		//int height = 0;
		if(node.getRightChild() == null && node.getLeftChild() != null) {
			return node.getLeftChild().getHeight();
		}
		else if(node.getLeftChild() == null && node.getRightChild() != null) {
			return node.getRightChild().getHeight();
		}
		else if(node.getLeftChild() == null && node.getRightChild() == null) {
			return 0;
		}
		else {
			return node.getRightChild().getHeight() - node.getLeftChild().getHeight();
		}
		
		/**if(node == null) {
			return height;
		}
		else if(node.getRightChild() == null && node.getLeftChild() == null) {
			return height;
		}
		else {
			height++;
			if(getBalanceFactor(node.getLeftChild()) > getBalanceFactor(node.getRightChild()) || (node.getLeftChild() != null && node.getRightChild() == null)) {
				height = -(height + (getBalanceFactor(node.getLeftChild()) - (getBalanceFactor(node.getRightChild()))));
				if(node.getRightChild() != null && getBalanceFactor(node.getRightChild()) == 0) {
					height++;
				}
			}
			else if(getBalanceFactor(node.getLeftChild()) < getBalanceFactor(node.getRightChild()) || (node.getRightChild() != null && node.getLeftChild() == null)){
				height = height + (getBalanceFactor(node.getRightChild()) - (getBalanceFactor(node.getLeftChild())));
				if(node.getLeftChild() != null && getBalanceFactor(node.getLeftChild()) == 0) {
					height++;
				}
			}
			else {
				height = 0;
			}
		}**/
		/**if(node.getRightChild() == null && node.getLeftChild() == null) {
			height = 0;
		}
		else if(node.getLeftChild() != null && node.getRightChild() == null) {
			height--;
			height = height - getBalanceFactor(node.getLeftChild());
		}
		else if(node.getRightChild() != null && node.getLeftChild() == null) {
			height++;
			height = height + getBalanceFactor(node.getRightChild());
		}
		else if(node.getRightChild() != null && node.getLeftChild() != null) {
			System.out.println(node.getLeftChild().getData() + " " + getBalanceFactor(node.getLeftChild()) + " " + node.getRightChild().getData() + " " + getBalanceFactor(node.getRightChild()));
			height = height + (getBalanceFactor(node.getRightChild()) - getBalanceFactor(node.getLeftChild()));
		}**/
		
	}
}
