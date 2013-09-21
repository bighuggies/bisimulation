package se705.bisimulation.lts;

import java.util.Arrays;

public class State {
	public final String label;
	public final Process process;

	public State(final String label, final Process process) {
		this.label = label;
		this.process = process;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(new Object[] {this.label, this.process});
	}

	@Override
	public boolean equals(final Object obj) {
		State other = (State) obj;
		return this.label.equalsIgnoreCase(other.label) && this.process.equals(other.process);
	}
}
