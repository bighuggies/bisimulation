package se705.bisimulation;

import java.io.IOException;

import se705.bisimulation.lts.Process;


public class BisimulationRunner {

	public static void main(String[] args) throws IOException {
		Process a = InputParser.parse("/home/andrew/Code/bisimulation/process_p");
		System.out.println(a);
//		Process b = InputParser.parse("process_b");
	}
	
	public boolean tap(Process a, Process b) {
		
			
		return true;
	}
}
