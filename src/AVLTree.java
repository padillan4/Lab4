  
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
		//backtracking in tree
		System.out.println("inserted node" + addNode.getData());
		int rootCalced = 0;
		while(curr.getParent() != null || (curr.equals(root) && rootCalced == 0)) {
			//setting height
			setHeight(curr);
			//checking balance factor
			if(curr.getParent() != null){
				//left left case
				if(getBalanceFactor(curr) == -1 && getBalanceFactor(curr.getParent()) == -2){
					rotateRight(curr.getParent());
				}
				//left right case
				else if(getBalanceFactor(curr) == 1 && getBalanceFactor(curr.getParent()) == -2){
					Node y = curr.getParent();
					
					rotateLeft(curr);
					rotateRight(y);
				}
				//right right case
				else if(getBalanceFactor(curr) == 1 && getBalanceFactor(curr.getParent()) == 2){
					rotateLeft(curr.getParent());
				}
				//right left case
				else if( getBalanceFactor(curr) == -1 && getBalanceFactor(curr.getParent()) == 2){
					Node y = curr.getParent();
					
					rotateRight(curr);
					rotateLeft(y);
				}
				else {
					curr = curr.getParent();
				}
			}
			if(curr.equals(root)) {
				rootCalced = 1;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void delete(Node deleteNode) {
		Node curr = root;
		boolean removed = false;
		//removing node
		while(!removed) {
			//found delete node
			if(curr.getData().equals(deleteNode.getData())) {
				Comparable temp;
				
				if(curr.getRightChild() != null) {
					
					temp = curr.getRightChild().getData();
					curr.getRightChild().setData(curr.getData());
					curr.setData(temp);
					curr = curr.getRightChild();
				}
				else if(curr.getRightChild() == null && curr.getLeftChild() != null) {
					temp = curr.getLeftChild().getData();
					curr.getLeftChild().setData(curr.getData());
					curr.setData(temp);
					curr = curr.getLeftChild();
				}
				else {
					Node tempNode = curr;
					curr = curr.getParent();
					if(curr.getRightChild() != null) {
						if(curr.getRightChild().equals(tempNode)) {
							curr.setRightChild(null);
						}
						else {
							curr.setLeftChild(null);
						}
					}
					else {
						curr.setLeftChild(null);
					}
					tempNode.setParent(null);
					removed = true;
				}
				
			}
			//less than current
			else if(curr.getData().compareTo(deleteNode.getData()) > 0) {
				if(curr.getLeftChild() != null) {
					curr = curr.getLeftChild();
				}
				else {
					removed = true;
				}
			}
			//greater than/equal to current
			else if(curr.getData().compareTo(deleteNode.getData()) <= 0) {
				if(curr.getRightChild() != null) {
					curr = curr.getRightChild();
				}
				else {
					removed = true;
				}
			}
		}
		//backtracking in tree
		int rootCalced = 0;
		while(curr.getParent() != null || (curr.equals(root) && rootCalced == 0)) {
			//setting height
			setHeight(curr);
			//checking balance factor
			if(curr.getParent() != null){
				if(getBalanceFactor(curr) == 2) {
					//right right case
					if(getBalanceFactor(curr.getRightChild()) == 0 || getBalanceFactor(curr.getRightChild()) == 1) {
						rotateLeft(curr);
					}
					//right left case
					else if(getBalanceFactor(curr.getRightChild()) == -1) {
						rotateRight(curr.getRightChild());
						rotateLeft(curr);
					}
				}
				else if(getBalanceFactor(curr) == -2) {
					//left left case
					if(getBalanceFactor(curr.getLeftChild()) == 0 || getBalanceFactor(curr.getLeftChild()) == -1) {
						rotateRight(curr);
					}
					//left right case
					else if(getBalanceFactor(curr.getLeftChild()) == 1) {
						rotateLeft(curr.getRightChild());
						rotateRight(curr);
					}
				}
				//left left case
				if(getBalanceFactor(curr) == -1 && getBalanceFactor(curr.getParent()) == -2){
					rotateRight(curr.getParent());
				}
				//left right case
				else if(getBalanceFactor(curr) == 1 && getBalanceFactor(curr.getParent()) == -2){
					Node y = curr.getParent();
					
					rotateLeft(curr);
					rotateRight(y);
				}
				//right right case
				else if(getBalanceFactor(curr) == 1 && getBalanceFactor(curr.getParent()) == 2){
					rotateLeft(curr.getParent());
				}
				//right left case
				else if( getBalanceFactor(curr) == -1 && getBalanceFactor(curr.getParent()) == 2){
					Node y = curr.getParent();
					
					rotateRight(curr);
					rotateLeft(y);
				}
				else {
					curr = curr.getParent();
				}
			}
			if(curr.equals(root)) {
				rootCalced = 1;
			}
		}
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
	
	private void rotateRight(Node y) {
		Node x = y.getLeftChild();
        Node child = x.getRightChild();
        
        x.setParent(y.getParent());
        if(y.getParent() != null) {
        	if(y.getParent().getRightChild().equals(y)) {
        		y.getParent().setRightChild(x);
        	}
        	else {
        		y.getParent().setLeftChild(x);
        	}
        }
        y.setParent(x);
        x.setRightChild(y);
        y.setLeftChild(child);
        if(child != null) {
        	child.setParent(x);
        }
        if(x.getParent() == null) {
        	root = x;
        }
        setHeight(x);
        setHeight(y);
	}
	
	private void rotateLeft(Node x) {
		Node y = x.getRightChild();
		Node child = y.getLeftChild();
        
        y.setParent(x.getParent());
        if(x.getParent() != null) {
        	if(x.getParent().getLeftChild().equals(x)) {
        		x.getParent().setLeftChild(y);
        	}
        	else{
        		x.getParent().setRightChild(y);
        	}
        }
        x.setParent(y);
        y.setLeftChild(x);
        x.setRightChild(child);
        if(child != null) {
        	child.setParent(x);
        }
        if(y.getParent() == null) {
        	root = y;
        }
        setHeight(x);
        setHeight(y);
        
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
	
	private void setHeight(Node n) {
		if(n != null) {
			if(n.getLeftChild() == null && n.getRightChild() == null) {
				n.setHeight(0);
			}
			else if(n.getLeftChild() != null && n.getRightChild() == null) {
				n.setHeight(1 + n.getLeftChild().getHeight());
			}
			else if(n.getRightChild() != null && n.getLeftChild() == null) {
				n.setHeight(1 + n.getRightChild().getHeight());
			}
			else {
				n.setHeight(1 + Math.max(n.getLeftChild().getHeight(), n.getRightChild().getHeight()));
			}
		}
	}
}
