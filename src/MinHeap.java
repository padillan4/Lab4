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
		
		newChild.setParent(insert);
		
		while(newChild.getParent() != null){
			Node parent = newChild.getParent();
			System.out.println(parent);
			if(parent.getData().compareTo(newChild.getData()) > 0){
				swap(parent, newChild);
			}
			else{
				break;
			}
		}
		
		return true;
		
		
		
	}
	
	@SuppressWarnings({ "unchecked" })
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
		if (path.length() == 1){
			if(path.charAt(0) == '0'){
				Node rootLeft = root.getLeftChild();
				Node rootRight = root.getRightChild();
				root = lastNode.getLeftChild();
				root.setLeftChild(rootLeft);
				root.setRightChild(rootRight);
				lastNode.setLeftChild(null);
			}else{
				Node rootLeft = root.getLeftChild();
				Node rootRight = root.getRightChild();
				root = lastNode.getRightChild();
				root.setLeftChild(rootLeft);
				root.setRightChild(rootRight);
				lastNode.setRightChild(null);
			}
			numEntries--;
			Node temp = root;
			while(temp.getLeftChild() != null){
				
				Node leftChild = temp.getLeftChild();
				Node rightChild = temp.getRightChild();
				
				if(rightChild != null && leftChild.getData().compareTo(rightChild.getData()) > 0){
					//right child is smallest else left is
					if(rightChild.getData().compareTo(temp.getData()) < 0){
						swap(temp, rightChild);
					}else{
						break;
					}
				}else{
					if(leftChild.getData().compareTo(temp.getData()) < 0){
						swap(temp, leftChild);
					}else{
						break;
					}
				}
			}
		}else{
			root = new Node();
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
		}else if(key.getLeftChild().getData().compareTo(key.getData()) > 0 || key.getLeftChild().getData().compareTo(key.getData()) > 0){
			Node temp = key;
			while(temp.getLeftChild() != null){
				
				Node leftChild = temp.getLeftChild();
				Node rightChild = temp.getRightChild();
				
				if(rightChild != null && leftChild.getData().compareTo(rightChild.getData()) > 0){
					//right child is smallest else left is
					if(rightChild.getData().compareTo(temp.getData()) < 0){
						swap(temp, rightChild);
					}else{
						break;
					}
				}else{
					if(leftChild.getData().compareTo(temp.getData()) < 0){
						swap(temp, leftChild);
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
			if (path.length() == 1){
			if(path.charAt(0) == '0'){
				Node delLeft = del.getLeftChild();
				Node delRight = del.getRightChild();
				del = lastNode.getLeftChild();
				del.setLeftChild(delLeft);
				del.setRightChild(delRight);
				lastNode.setLeftChild(null);
			}else{
				Node delLeft = del.getLeftChild();
				Node delRight = del.getRightChild();
				del = lastNode.getRightChild();
				del.setLeftChild(delLeft);
				del.setRightChild(delRight);
				lastNode.setRightChild(null);
			}
			
			numEntries--;
			Node temp = del;
			while(temp.getLeftChild() != null){
				
				Node leftChild = temp.getLeftChild();
				Node rightChild = temp.getRightChild();
				
				if(rightChild != null && leftChild.getData().compareTo(rightChild.getData()) > 0){
					//right child is smallest else left is
					if(rightChild.getData().compareTo(temp.getData()) < 0){
						swap(temp, rightChild);
					}else{
						break;
					}
				}else{
					if(leftChild.getData().compareTo(temp.getData()) < 0){
						swap(temp, leftChild);
					}else{
						break;
					}
				}
			}
		}else{
			root = new Node();
			System.out.print("shit");
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
	
	private void swap(Node b, Node c){
		if(b != root){
			Node a = b.getParent();
			
			if (b.getLeftChild() == c){
				Node d = b.getRightChild();
				Node e = c.getLeftChild();
				Node f = c.getRightChild();
				
				if(a.getLeftChild() == b){
					a.setLeftChild(c);
					c.setParent(a);
				}else{
					a.setRightChild(c);
					c.setParent(a);
				}
				
				b.setLeftChild(e);
				b.setRightChild(f);
				
				if(e != null){
					e.setParent(b);
				}
				
				if(f != null){
					f.setParent(b);
				}
				
				b.setParent(c);
				
				c.setLeftChild(b);
				c.setRightChild(d);
				
				if(d != null){
					d.setParent(c);
				}
			}else{
				Node d = b.getLeftChild();
				Node e = c.getLeftChild();
				Node f = c.getRightChild();
				
				if(a.getLeftChild() == b){
					a.setLeftChild(c);
					c.setParent(a);
				}else{
					a.setRightChild(c);
					c.setParent(a);
				}
				
				b.setLeftChild(e);
				b.setRightChild(f);
				
				if(e != null){
					e.setParent(b);
				}
				
				if(f != null){
					f.setParent(b);
				}
				
				b.setParent(c);
				
				c.setRightChild(d);
				c.setLeftChild(b);
				System.out.print(d==c);
				if(d != null){
					d.setParent(c);
				}
			}
		}else{
			if (b.getLeftChild() == c){
				Node d = b.getRightChild();
				Node e = c.getLeftChild();
				Node f = c.getRightChild();
				
				c.setParent(null);
				root = c;
				
				b.setLeftChild(e);
				b.setRightChild(f);
				
				if(e != null){
					e.setParent(b);
				}
				
				if(f != null){
					f.setParent(b);
				}
				
				b.setParent(c);
				
				c.setLeftChild(b);
				c.setRightChild(d);
				
				if(d != null){
					d.setParent(c);
				}
			}else{
				Node d = b.getLeftChild();
				Node e = c.getLeftChild();
				Node f = c.getRightChild();
				
				c.setParent(null);
				root = c;
				
				b.setLeftChild(e);
				b.setRightChild(f);
				
				if(e != null){
					e.setParent(b);
				}
				
				if(f != null){
					f.setParent(b);
				}
				
				b.setParent(c);
				
				c.setRightChild(b);
				c.setLeftChild(d);
				
				if(d != null){
					d.setParent(c);
				}
			}
		}
	}	
}

	
}
