package se705.bisimulation;

import java.io.IOException;

import se705.bisimulation.lts.Process;


public class BisimulationRunner {
	public static void main(final String[] args) throws IOException {
		Process a = InputParser.parse("process_p");
		Process b = InputParser.parse("process_q");

		System.out.println(a);
		System.out.println(b);

		BisimilarComputation bsc = new BisimilarComputation(a, b);
		System.out.println(bsc.getResult());
	}
}
