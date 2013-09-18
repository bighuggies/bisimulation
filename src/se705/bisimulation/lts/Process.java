package se705.bisimulation.lts;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class Process {
	private HashMap<String, HashMap<String, Set<String>>> _transitions;
	private String _name;
	private Set<String> _states;
	private Set<String> _actions;
	private String _currentState;

	public Process(String processName) {
		_name = processName;
		_transitions = new HashMap<String, HashMap<String, Set<String>>>();
		_states = new HashSet<String>();
		_actions = new HashSet<String>();
	}

	public Process(String processName, String initialState) {
		this(processName);
		this._currentState = initialState;
	}

	public Process addState(String state) {
		this._states.add(state);
		return this;
	}

	public Process addAction(String action) {
		this._actions.add(action);
		return this;
	}

	public Process addTransition(String source, String action,
			String destination) {
		if (!_transitions.containsKey(source)) {
			this._transitions.put(source, new HashMap<String, Set<String>>());
		}

		HashMap<String, Set<String>> transitionsFromSource = _transitions
				.get(source);

		if (!transitionsFromSource.containsKey(action)) {
			transitionsFromSource.put(action, new HashSet<String>());
		}

		transitionsFromSource.get(action).add(destination);

		return this;
	}

	public Set<String> doAction(String action) {
		return this._transitions.get(_currentState).get(action);
	}

	public Process setCurrentState(String state) {
		if (!this._states.contains(state)) {
			throw new IllegalArgumentException("State " + state
					+ " does not exist in this process");
		}

		this._currentState = state;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();

		b.append(this._name + ":\n");

		b.append("\tStates: [");
		for (String s : this._states) {
			b.append(s + ", ");
		}

		b.append("]\n\tActions: [");
		for (String s : this._actions) {
			b.append(s + ", ");
		}

		b.append("]\n\tTransitions: [");
		for (Entry<String, HashMap<String, Set<String>>> x : this._transitions
				.entrySet()) {
			for (Entry<String, Set<String>> y : x.getValue().entrySet()) {
				for (String z : y.getValue()) {
					b.append("{ " + x.getKey() + " - " + y.getKey() + " -> "
							+ z + " }, ");
				}
			}
		}

		return b.toString();
	}
}
