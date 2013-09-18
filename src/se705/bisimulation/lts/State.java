package se705.bisimulation.lts;

import java.util.Map;
import java.util.Set;

public class State {
	private final String _label;
	private final Map<String, State> _transitions;

	public State(String label) {
		this(label, null);
	}

	public State(String label, Map<String, State> transitions) {
		this._label = label;
		this._transitions = transitions;
	}

	public String getLabel() {
		return _label;
	}

	public Set<String> getActions() {
		return this._transitions.keySet();
	}

	public State transition(String action) {
		if (this._transitions == null)
			return null;

		return this._transitions.get(action);
	}
}
