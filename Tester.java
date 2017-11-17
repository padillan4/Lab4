
public class Tester {

	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		
		tree.insert(new Node(new Integer(5)));
		System.out.println(tree.delete().getData());
		tree.insert(new Node(new Integer(4)));
		tree.insert(new Node(new Integer(4)));
		System.out.println(tree.search(new Integer(5)));
		tree.insert(new Node(new Integer(7)));
		tree.insert(new Node(new Integer(15)));
		tree.insert(new Node(new Integer(2)));
		tree.insert(new Node(new Integer(14)));
		tree.insert(new Node(new Integer(9)));
		System.out.println(tree.count(tree.getRoot(), 7, 15));
		System.out.println(tree.search(new Integer(14)));
		tree.inOrder(tree.getRoot());
		
		System.out.println();
	}
}
