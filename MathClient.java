class MathClient {
	public static void main(String[] args) {
		EquationSolver input = new EquationSolver();
		Transition[] t = {
				new Transition(0, 1, "a"),
				new Transition(1, 1, true),
				new Transition(1, 2, "b"),
		};
		NfaRunner stateMachine = new NfaRunner(t, 0, 2);

		System.out.println(input.evaluate("1 + 2 ^ ( 3 + 4 )"));

		System.out.println(stateMachine.validate(args[0]));
		while (args.length > 1) {
			String read = System.console().readLine();
			if (read.equals("exit")) { break; }
			System.out.println(input.evaluate(read));
			System.out.println(stateMachine.validate(read));
		}
	}
}

