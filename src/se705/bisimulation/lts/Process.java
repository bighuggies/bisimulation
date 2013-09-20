package se705.bisimulation.lts;

import java.util.HashSet;
import java.util.Set;

public class Process {
	private final String _name;
	private final Set<String> _states;
	private final Set<String> _actions;
	private final Set<Transition> _transitions;

	public Process(final String processName) {
		this._name = processName;
		this._states = new HashSet<String>();
		this._actions = new HashSet<String>();
		this._transitions = new HashSet<Transition>();
	}

	public Process addAction(final String action) {
		this.getActions().add(action);
		return this;
	}

	public Process addState(final String state) {
		this._states.add(state);
		return this;
	}

	public Process addTransition(final String source, final String action, final String destination) {
		this._transitions.add(new Transition(source, action, destination));
		return this;
	}

	public Set<String> getActions() {
		return this._actions;
	}

	public Set<String> getStates() {
		return this._states;
	}



	@Override
	public String toString() {
		final StringBuilder b = new StringBuilder();

		b.append(this._name + ":\n");

		b.append("\tStates: [");
		for (final String s : this.getStates()) {
			b.append(s + ", ");
		}

		b.append("]\n\tActions: [");
		for (final String s : this.getActions()) {
			b.append(s + ", ");
		}

		b.append("]\n\tTransitions: [");
		for (final Transition t : this.getTransitions()) {
			b.append(t.toString() + ", ");
		}

		b.append("]");

		return b.toString();
	}

	public Set<Transition> getTransitions() {
		return this._transitions;
	}
}
