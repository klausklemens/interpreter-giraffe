/**
 * State transition
 */
class Transition {
	public int from;
	public int to;
	public char[] conditions;
	public boolean inverted;

	public Transition(int from, int to, boolean inverted) {
		this(from, to, (char[]) null, inverted);
	}

	public Transition(int from, int to, String conditions) {
		this(from, to, conditions.toCharArray(), false);
	}

	public Transition(int from, int to, String conditions, boolean inverted) {
		this(from, to, conditions.toCharArray(), inverted);
	}

	public Transition(int from, int to, char[] conditions, boolean inverted) {
		this.from = from;
		this.to = to;
		this.conditions = conditions;
		this.inverted = inverted;
	}

	public boolean matches(char c) {
		boolean match = false;
		for (char condition : this.conditions) {
			if (condition == c) {
				match = true;
				break;
			}
		}
		return (match ^ inverted);
	}
}

