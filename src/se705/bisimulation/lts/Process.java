package se705.bisimulation.lts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

	private Process addAction(final String action) {
		this.getActions().add(action);
		return this;
	}

	private Process addState(final String state) {
		this._states.add(state);
		return this;
	}

	private Process addTransition(final String source, final String action, final String destination) {
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


	@Override
	public String toString() {
		final StringBuilder b = new StringBuilder();

		b.append(this._name + "\n");

		b.append("S = ");
		b.append(StringUtil.join(StringUtil.wrapEach(this.getStates(), "state(%s)"), ",") + "\n");

		b.append("A = ");
		b.append(StringUtil.join(StringUtil.wrapEach(this.getActions(), "action(%s)"), ",") + "\n");

		b.append("T = ");
		b.append(StringUtil.join(this.getTransitions(), ",") + "\n");

		return b.toString();
	}
}
