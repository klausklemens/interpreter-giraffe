class Container {
	public boolean isOperator;
	public String element;

	public Container(String element, boolean isOperator) {
		this(element);
		this.isOperator = isOperator;
	}

	public Container(String element) {
		this.element = element;
	}
}

