import java.util.LinkedList;
import java.util.Queue;

class NfaRunner {
	private Queue<Integer> state;
	private Transition[] transitions; // TODO: Make array of transitions originating from a specific state to speed up

	private int[] finalStates;

	public NfaRunner(Transition[] transitions, int initialState, int[] finalStates) {
		this.transitions = transitions;
		this.state = new LinkedList<>();
		this.state.offer(initialState);
		this.finalStates = finalStates;
	}

	public boolean validate(String input) {
		for (int i = 0; i < input.length(); i++) {
			Queue<Integer> newState = new LinkedList<>();
			while (!state.isEmpty()) {
				int checkState = state.poll();
				for (Transition t : transitions) {
					if (checkState == t.from && t.matches(input.charAt(i))) {
						newState.offer(t.to);
					}
				}
			}
			this.state = newState;
			if (state.isEmpty()) {
				return false;
			}
		}

		while (!state.isEmpty()) {
			int endState = state.poll();
			for (int finalState : this.finalStates) {
				if (endState == finalState) {
					return true;
				}
			}
		}
		return false;
	}
}

