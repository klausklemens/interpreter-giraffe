class MathClient {
	public static void main(String[] args) {
		EquationSolver input = new EquationSolver();
		int FS = 255;
		Transition[] t = {
				new Transition(0, 0, "0123456789", true),
				new Transition(0, 1, "0"),
				new Transition(1, FS, "x"),
				new Transition(0, FS, "0123456789"),
				new Transition(FS, FS, "0123456789"),
		};
		NfaRunner stateMachine = new NfaRunner(t, 0, (new int[] {FS}));

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

