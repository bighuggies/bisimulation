package se705.bisimulation.lts;

import java.util.HashSet;
import java.util.Set;

public class Process {
	private final String _name;
	private Set<State> _states;
	private final Set<String> _actions;

	private State _currentState;
	private State _initialState;

	public Process(final String processName) {
		this._name = processName;
		this._actions = new HashSet<String>();
	}

	public Process(final String processName, final State initialState) {
		this(processName);
		this._currentState = initialState;
	}

	public Process addAction(final String action) {
		this.getActions().add(action);
		return this;
	}

	public Process addState(final String label) {
		State newState = new State(label);

		if (this.getStates() == null) {
			this._states = new HashSet<State>();
			this._initialState = newState;
			this._currentState = this._initialState;
		}

		this.getStates().add(new State(label));
		return this;
	}

	public Process addTransition(final String source, final String action, final String destination) {
		State src = null, dest = null;

		for (State s : this._states) {
			if (s.getLabel().equalsIgnoreCase(source)) {
				src = s;
			}
		}

		if (src == null) {
			src = new State(source);
		}

		for (State d : this._states) {
			if (d.getLabel().equalsIgnoreCase(destination)) {
				dest = d;
			}
		}

		if (dest == null) {
			dest = new State(destination);
		}

		src.addTransition(action, dest);
		return this;
	}

	public Set<String> getActions() {
		return this._actions;
	}

	public State getCurrentState() {
		return this._currentState;
	}

	public Set<State> getStates() {
		return this._states;
	}

	public void setCurrentState(final State _currentState) {
		this._currentState = _currentState;
	}

	@Override
	public String toString() {
		final StringBuilder b = new StringBuilder();

		b.append(this._name + ":\n");

		b.append("\tStates: [");
		for (final State s : this.getStates()) {
			b.append(s.getLabel() + ", ");
		}

		b.append("]\n\tActions: [");
		for (final String s : this.getActions()) {
			b.append(s + ", ");
		}

		return b.toString();
	}
}
