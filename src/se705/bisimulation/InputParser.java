package se705.bisimulation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import se705.bisimulation.lts.Process;

public class InputParser {
	public static Process parse(final String filePath) throws IOException {
		Process p = new Process(Paths.get(filePath).getFileName().toString());

		BufferedReader reader = new BufferedReader(new FileReader(filePath));

		String line;
		while (!(line = reader.readLine()).equalsIgnoreCase("!")) {
			String[] parts = line.split(",|:");
			InputParser.putLineInProcess(p, parts);
		}

		reader.close();
		return p;
	}

	private static void putLineInProcess(final Process p, final String[] line) {
		p.addState(line[0].trim());
		p.addAction(line[1].trim());
		p.addTransition(line[0].trim(), line[1].trim(), line[2].trim());
	}
}
