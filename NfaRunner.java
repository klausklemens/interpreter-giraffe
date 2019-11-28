import java.util.LinkedList;
import java.util.Queue;

class NfaRunner {
	private Queue<Integer> state;
	private Transition[] transitions; // TODO: Make array of transitions originating from a specific state to speed up

	private int target;

	public NfaRunner(Transition[] transitions, int initialState, int finalState) {
		this.transitions = transitions;
		this.state = new LinkedList<>();
		this.state.offer(initialState);
		this.target = finalState;
	}

	public boolean validate(String input) {
		for (int i = 0; i < input.length(); i++) {
			Queue<Integer> newState = new LinkedList<>();
			while (!state.isEmpty()) {
				int checkState = state.poll();
				for (int t = 0; t < transitions.length; t++) {
					if (checkState == transitions[t].from && transitions[t].matches(input.charAt(i))) {
						newState.offer(transitions[t].to);
					}
				}
			}
			this.state = newState;
			if (state.isEmpty()) {
				return false;
			}
		}

		while (!state.isEmpty()) {
			if (state.poll() == target) {
				return true;
			}
		}
		return false;
	}
}

