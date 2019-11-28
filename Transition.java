/**
 * State transition
 */
class Transition {
    public int from;
    public int to;
    public char[] conditions;
    public boolean alwaysMatch;

    public Transition(int from, int to, String conditions) {
        this(from, to, conditions.toCharArray());
    }

    public Transition(int from, int to, char[] conditions) {
        this.from = from;
        this.to = to;
        this.conditions = conditions;
        this.alwaysMatch = false;
    }

    public Transition(int from, int to, boolean alwaysMatch) {
        this.from = from;
        this.to = to;
        this.alwaysMatch = alwaysMatch;
    }

    public boolean matches(char c) {
        if (this.alwaysMatch) {
            return true;
        } else {
            for (char condition : this.conditions) {
                if (condition == c) {
                    return true;
                }
            }
        }
        return false;
    }
}

