package se705.bisimulation;

import java.util.HashSet;
import java.util.Set;

import se705.bisimulation.lts.Process;
import se705.bisimulation.lts.Transition;

public class BisimilarComputation {
	private HashSet<Set<String>> rho;
	private final Set<Transition> ts;

	public BisimilarComputation(final Process p, final Process q) {
		this.ts = new HashSet<Transition>();
		this.ts.addAll(p.getTransitions());
		this.ts.addAll(q.getTransitions());

		Set<Set<String>> rhoinit = new HashSet<Set<String>>();
		rhoinit.add(p.getStates());
		rhoinit.add(q.getStates());

		Set<String> sigma = new HashSet<String>();
		sigma.addAll(p.getActions());
		sigma.addAll(q.getActions());

		Set<Set<String>> rho = new HashSet<Set<String>>(rhoinit);
		Set<Set<String>> waiting = new HashSet<Set<String>>(rhoinit);

		while (true) {
			Set<String> pprime = this.popStates(waiting);
			Set<Set<String>> matchP = new HashSet<Set<String>>();

			for (String action : sigma) {
				for (Set<String> partition : rho) {
					matchP.add(this.t(partition, pprime, action));
				}

				for (Set<String> partition : matchP) {
					Set<Set<String>> splitP = this.splitP(partition, pprime, action);

					rho.remove(partition);
					rho.addAll(splitP);
				}
			}

			if (waiting.isEmpty()) {
				break;
			}
		}
	}

	HashSet<Set<String>> getResult() {
		return this.rho;
	}

	private Set<String> popStates(final Set<Set<String>> stateSets) {
		Set<String> states = stateSets.iterator().next();
		stateSets.remove(states);
		return states;
	}

	private Set<Set<String>> splitP(final Set<String> partition, final Set<String> pprime, final String action) {
		Set<Set<String>> splitP = new HashSet<Set<String>>();

		Set<String> tap = this.t(partition, pprime, action);
		Set<String> nottap = new HashSet<String>(partition);
		nottap.removeAll(tap);

		splitP.add(tap);
		splitP.add(nottap);

		return splitP;
	}

	private Set<String> t(final Set<String> partition, final Set<String> pprime, final String action) {
		Set<String> acc = new HashSet<String>();

		for (String s : partition) {
			for (String d : pprime) {
				if (this.ts.contains(new Transition(s, action, d))) {
					acc.add(s);
				}
			}
		}

		return acc;
	}
}
