public class MinHeap implements MyHeap {
	Node root = new Node();
	int numEntries = 0;
	
	@SuppressWarnings("rawtypes")
	public Node makeHeap(Comparable value){
		root.setData(value);
		numEntries++;
		return root;
	}
	
	public boolean isEmpty(){
		return root.getData() == null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean insert( Comparable value){
		Node newChild = new Node(value);
		Node insert = root;
		numEntries++;
		String path = Integer.toBinaryString(numEntries);
		path = path.substring(1);
		
		while(path.length() > 1){
			if(path.charAt(0) == '1'){
				insert = insert.getRightChild();
			} else{
				insert = insert.getLeftChild();
			}
			path = path.substring(1);	
		}
		
		if(path.charAt(0) == '0'){
			insert.setLeftChild(newChild);
		}else{
			insert.setRightChild(newChild);
		}
		
		while(newChild != root){
			Node parent = newChild.getParent();
			if(parent.getData().compareTo(newChild.getData()) > 0){
				//parent greater than new child
				Comparable swapSpace;
				swapSpace = parent.getData();
				parent.setData(newChild.getData());
				newChild.setData(swapSpace);
				
			}
			else{
				break;
			}
		}
		
		return true;
		
		
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public boolean deleteMin(){
		Node lastNode = root;
		
		String path = Integer.toBinaryString(numEntries);
		path = path.substring(1);
		
		while(path.length() > 1){
			if(path.charAt(0) == '1'){
				lastNode = lastNode.getRightChild();
			} else{
				lastNode = lastNode.getLeftChild();
			}
			path = path.substring(1);	
		}
		
		if(path.charAt(0) == '0'){
			root.setData(lastNode.getLeftChild().getData());
			lastNode.setLeftChild(null);
		}else{
			root.setData(lastNode.getRightChild().getData());
			lastNode.setRightChild(null);
		}
		numEntries--;
		Node temp = root;
		while(temp.getLeftChild() != null){
			Node leftChild = temp.getLeftChild();
			Node rightChild = temp.getRightChild();
			
			if(leftChild.getData().compareTo(rightChild.getData()) > 0){
				//right child is smallest else left is
				if(rightChild.getData().compareTo(temp.getData()) > 0){
					swap(temp, rightChild);
					temp = rightChild;
				}else{
					break;
				}
			}else{
				if(leftChild.getData().compareTo(temp.getData()) > 0){
					swap(temp, leftChild);
					temp = leftChild;
				}else{
					break;
				}
			}
		}
		
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean decreaseKey(Node key, Comparable updateValue){
		key.setData(updateValue);
		Node parent = key.getParent();
		if ((parent != null) && parent.getData().compareTo(key.getData()) > 0){
			while(key != root){
				
				if(parent.getData().compareTo(key.getData()) > 0){
					//parent greater than new child
					swap(parent, key);
					
				}
				else{
					break;
				}
				parent = key.getParent();
			}
		}else if(key.getLeftChild().getData().compareTo(key) > 0 || key.getLeftChild().getData().compareTo(key) > 0){
			Node temp = key;
			while(temp.getLeftChild() != null){
				Node leftChild = temp.getLeftChild();
				Node rightChild = temp.getRightChild();
				
				if(leftChild.getData().compareTo(rightChild.getData()) > 0){
					//right child is smallest else left is
					if(rightChild.getData().compareTo(temp.getData()) > 0){
						swap(temp, rightChild);
						temp = rightChild;
					}else{
						break;
					}
				}else{
					if(leftChild.getData().compareTo(temp.getData()) > 0){
						swap(temp, leftChild);
						temp = leftChild;
					}else{
						break;
					}
				}
			}

		}
		return true;
	}
	@SuppressWarnings("unchecked")
	public boolean delete(Node del){
		Node lastNode = root;
		
		String path = Integer.toBinaryString(numEntries);
		path = path.substring(1);
		
		while(path.length() > 1){
			if(path.charAt(0) == '1'){
				lastNode = lastNode.getRightChild();
			} else{
				lastNode = lastNode.getLeftChild();
			}
			path = path.substring(1);	
		}
		
		if(path.charAt(0) == '0'){
			del.setData(lastNode.getLeftChild().getData());
			lastNode.setLeftChild(null);
		}else{
			del.setData(lastNode.getRightChild().getData());
			lastNode.setRightChild(null);
		}
		numEntries--;
		Node temp = del;
		while(temp.getLeftChild() != null){
			Node leftChild = temp.getLeftChild();
			Node rightChild = temp.getRightChild();
			
			if(leftChild.getData().compareTo(rightChild.getData()) > 0){
				//right child is smallest else left is
				if(rightChild.getData().compareTo(temp.getData()) > 0){
					swap(temp, rightChild);
					temp = rightChild;
				}else{
					break;
				}
			}else{
				if(leftChild.getData().compareTo(temp.getData()) > 0){
					swap(temp, leftChild);
					temp = leftChild;
				}else{
					break;
				}
			}
		}
		return true;
	}
	public boolean union(MyHeap heap){
		// insert from one into other
		while(!heap.isEmpty()){
			insert(heap.findMin());
			heap.deleteMin();
		}
		
		return true;
	}
	
	@SuppressWarnings("rawtypes")
	public Comparable findMin(){
		return root.getData();
	}
	
	@SuppressWarnings("rawtypes")
	private void swap(Node leftHand, Node rightHand){
		Comparable swapSpace;
		swapSpace = leftHand.getData();
		leftHand.setData(rightHand.getData());
		rightHand.setData(swapSpace);
	}
	

	
}
