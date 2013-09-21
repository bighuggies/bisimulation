package se705.bisimulation.lts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import se705.bisimulation.util.StringUtil;

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

	public Set<Transition> getTransitions() {
		return this._transitions;
	}

	public static Process parse(final String filePath, final String processName) throws IOException {
		Process p = new Process(processName);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));

		String line;
		while (!(line = reader.readLine()).equalsIgnoreCase("!")) {
			String[] parts = line.split(",|:");
			Process.putLineInProcess(p, parts);
		}

		reader.close();
		return p;
	}

	private static void putLineInProcess(final Process p, final String[] line) {
		p.addState(line[0].trim());
		p.addAction(line[1].trim());
		p.addTransition(line[0].trim(), line[1].trim(), line[2].trim());
	}



	@Override
	public String toString() {
		final StringBuilder b = new StringBuilder();

		b.append(this._name + "\n");

		b.append("S = ");
		Collection<String> stateLabels = new ArrayList<String>();
		for (final String s : this.getStates()) {
			stateLabels.add(String.format("state(%s)", s));
		}
		b.append(StringUtil.join(stateLabels, ",") + "\n");

		b.append("A = ");
		Collection<String> actionLabels = new ArrayList<String>();
		for (final String a : this.getActions()) {
			actionLabels.add(String.format("action(%s)", a));
		}
		b.append(StringUtil.join(actionLabels, ",") + "\n");


		b.append("T = ");
		Collection<String> transitionLabels = new ArrayList<String>();
		for (final Transition t : this.getTransitions()) {
			transitionLabels.add(String.format("(state(%s),action(%s),state(%s))", t.source, t.action, t.destination));
		}
		b.append(StringUtil.join(transitionLabels, ",") + "\n");

		return b.toString();
	}
}
