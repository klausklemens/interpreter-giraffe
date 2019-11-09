import java.util.LinkedList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

class EquationSolver {
	public EquationSolver() {
	}

	public int evaluate(String equationString) {
		BinaryTree tree = equationToBinTree(equationString);
		System.out.println(getBinString(tree));
		return 0;
	}

	private String getBinString(BinaryTree tree) {
		String ret = "";
		if (tree.leftTree != null) { ret += getBinString(tree.leftTree); }
		if (tree.rightTree != null) { ret += getBinString(tree.rightTree); }
		ret += tree.item.element;
		return ret;
	}

	// e for equation
	private BinaryTree equationToBinTree(String eString) {
		Queue<String> eComponents = new LinkedList<>(Arrays.asList(eString.split(" ")));
		Stack<Container> eShuntingLine = new Stack<>();
		Stack<BinaryTree> eTreeStorage = new Stack<>();

		// Shunting-yard algorithm
		while (!eComponents.isEmpty()) {
			Container c = new Container(eComponents.poll());
			c.isOperator = !c.element.matches("[0-9]+");
			
			if (!c.isOperator) { // number
				eTreeStorage.push(new BinaryTree(c));
			} else if (!c.element.matches("[()]")) { // operator
				while (!eShuntingLine.empty()) {
					if (eShuntingLine.peek().element.equals("(")) {
						break;
					} else if (moveOperator(eShuntingLine.peek(), c)) {
						BinaryTree rightTree = eTreeStorage.pop();
						eTreeStorage.push(new BinaryTree(eShuntingLine.pop(), eTreeStorage.pop(), rightTree));
					} else {
						break;
					}
				}
				eShuntingLine.push(c);
			} else { // paranthesis
				if (c.element.equals("(")) {
					eShuntingLine.push(c);
				} else {
					while (!eShuntingLine.empty()) {
						if (!eShuntingLine.peek().element.equals("(")) {
							BinaryTree rightTree = eTreeStorage.pop();
							eTreeStorage.push(new BinaryTree(eShuntingLine.pop(), eTreeStorage.pop(), rightTree));
						} else {
							eShuntingLine.pop();
							break;
						}
					}
				}
			}
		}
		while (!eShuntingLine.empty()) {
			BinaryTree rightTree = eTreeStorage.pop();
			eTreeStorage.push(new BinaryTree(eShuntingLine.pop(), eTreeStorage.pop(), rightTree));
		}

		return eTreeStorage.pop();
	}

	int precedence(Container c) {
		if (c.element.matches("[+-]")) { return 2; }
		if (c.element.matches("[*/]")) { return 3; }
		if (c.element.matches("[\\^]")) { return 4; }
		System.out.println("==> ERROR? " + c.element);
		return 0;
	}

	// does a have higher precedence than b or
	// do a and b have equal precedence but a is left associative
	private boolean moveOperator(Container a, Container b) {
		int aPrec = precedence(a);
		int bPrec = precedence(b);

		if (aPrec > bPrec) {
			return true;
		} else {
			if (aPrec == bPrec && aPrec < 4) {
				return true;
			} else {
				return false;
			}
		}
	}
}

