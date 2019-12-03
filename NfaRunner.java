import java.util.LinkedList;
import java.util.Queue;

class NfaRunner {
	private Queue<Integer> state;
	private Transition[][] transitions;

	private int[] finalStates;

	public NfaRunner(Transition[] transitions, int initialState, int[] finalStates) {
		this.initializeTransitions(transitions);
		this.state = new LinkedList<>();
		this.state.offer(initialState);
		this.finalStates = finalStates;
	}

	private void initializeTransitions(Transition[] newTransitions) {
		this.transitions = new Transition[255][];
		Queue<Transition> tmpStorage = new LinkedList<>();

		for (int t = 0; t < this.transitions.length; t++) {
			for (Transition newTransition : newTransitions) {
				if (newTransition.from == t) {
					tmpStorage.offer(newTransition);
				}
			}

			int len = tmpStorage.size();
			if (len == 0) {
				this.transitions[t] = null;
			} else {
				this.transitions[t] = new Transition[len];
				for (int i = 0; i < len; i++) {
					this.transitions[t][i] = tmpStorage.poll();
				}
			}
		}
	}

	public boolean validate(String input) {
		for (int i = 0; i < input.length(); i++) {
			Queue<Integer> newState = new LinkedList<>();
			while (!state.isEmpty()) {
				int checkState = state.poll();
				for (Transition t : transitions[checkState]) {
					if (t.matches(input.charAt(i))) {
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

