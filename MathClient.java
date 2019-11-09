class MathClient {
	public static void main(String[] args) {
		EquationSolver input = new EquationSolver();

		System.out.println(input.evaluate("1 + 2 ^ ( 3 + 4 )"));
		while (true) {
			String read = System.console().readLine();
			if (read.equals("exit")) { break; }
			System.out.println(input.evaluate(read));
		}
	}
}

