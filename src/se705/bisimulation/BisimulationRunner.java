package se705.bisimulation;

import java.io.IOException;

import se705.bisimulation.lts.Process;


public class BisimulationRunner {

	public static void main(String[] args) throws IOException {
		Process a = InputParser.parse("process_p");
		Process b = InputParser.parse("process_q");
		
		System.out.println(a);
		System.out.println(b);
	}
	
	public boolean tap(Process a, Process b) {
		
			
		return true;
	}
}
