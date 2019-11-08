class BinaryTree {
	Container item;
	BinaryTree leftTree;
	BinaryTree rightTree;

	// Construcotors
	public BinaryTree(Container item, BinaryTree leftTree, BinaryTree rightTree) {
		this.leftTree = leftTree;
		this.rightTree = rightTree;
		this.item = item;
	}

	public BinaryTree(BinaryTree leftTree, BinaryTree rightTree) {
		this(null, leftTree, rightTree);
	}

	public BinaryTree(Container item) {
		this(item, null, null);
	}

	public BinaryTree() {
		this(null, null, null);
	}

	// Methods
	public Container getItem() {
		return this.item;
	}

	public void setItem(Container item) {
		this.item = item;
	}

	public BinaryTree getLeftTree() {
		return this.leftTree;
	}

	public void setLeftTree(BinaryTree tree) {
		this.leftTree = tree;
	}

	public BinaryTree getRightTree() {
		return this.rightTree;
	}

	public void setRightTree(BinaryTree tree) {
		this.rightTree = tree;
	}
}

