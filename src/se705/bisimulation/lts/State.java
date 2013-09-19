package se705.bisimulation.lts;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class State {
	private final String _label;
	private final Map<String, State> _transitions;

	public State(final String label) {
		this._label = label;
		this._transitions = new HashMap<String, State>();
	}

	public State addTransition(final String action, final State destination) {
		this._transitions.put(action, destination);
		return this;
	}

	public State doAction(final String action) {
		if (this._transitions == null)
			return null;

		return this._transitions.get(action);
	}

	public Set<String> getActions() {
		return this._transitions.keySet();
	}

	public String getLabel() {
		return this._label;
	}

	public Map<String, State> getTransitions() {
		return this._transitions;
	}
}
