package se705.bisimulation;

import java.util.HashSet;
import java.util.Set;

import se705.bisimulation.lts.Process;
import se705.bisimulation.lts.State;

public class BisimilarComputation {
	public BisimilarComputation(final Process p, final Process q) {
		HashSet<Set<State>> rhoinit = new HashSet<Set<State>>();
		rhoinit.add(p.getStates());
		rhoinit.add(q.getStates());

		Set<String> sigma = new HashSet<String>();
		sigma.addAll(p.getActions());
		sigma.addAll(q.getActions());

		HashSet<Set<State>> rho = new HashSet<Set<State>>(rhoinit);
		HashSet<Set<State>> waiting = new HashSet<Set<State>>(rhoinit);

		while(true) {
			Set<State> pprime = this.popStates(waiting);
			Set<Set<State>> matchP = new HashSet<Set<State>>();

			for(String action : sigma) {
				for(Set<State> partition : rho) {
					matchP.add(this.t(partition, pprime, action));
				}

				for (Set<State> partition: matchP) {
					Set<Set<State>> splitP = this.splitP(partition, pprime, action);

					rho.remove(partition);
					rho.addAll(splitP);
				}
			}

			if (waiting.isEmpty()) {
				break;
			}
		}
	}

	private Set<State> popStates(final HashSet<Set<State>> stateSets) {
		Set<State> states = stateSets.iterator().next();
		stateSets.remove(states);
		return states;
	}

	private Set<Set<State>> splitP(final Set<State> partition, final Set<State> pprime, final String action) {
		Set<Set<State>> splitP = new HashSet<Set<State>>();

		Set<State> tap = this.t(partition, pprime, action);
		Set<State> nottap = new HashSet<State>(partition);
		nottap.removeAll(tap);

		splitP.add(tap);
		splitP.add(nottap);

		return splitP;
	}

	private Set<State> t(final Set<State> partition, final Set<State> pprime, final String action) {
		Set<State> acc = new HashSet<State>();

		for (State s : partition) {
			if (pprime.contains(s.doAction(action))) {
				acc.add(s);
			}
		}

		return acc;
	}
}
