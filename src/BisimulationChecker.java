import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import se705.bisimulation.BisimilarComputation;
import se705.bisimulation.lts.Process;

public class BisimulationChecker {
	private Process processP;
	private Process processQ;
	private BisimilarComputation compute;

	public static void main(final String[] args) throws IOException {
		BisimulationChecker bisim = new BisimulationChecker();
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please enter the file name of the first process: ");
		String fileP = stdin.readLine();
		fileP = bisim.checkFileName(fileP);

		System.out.println("Please enter the file name of the second process: ");
		String fileQ = stdin.readLine();
		fileQ = bisim.checkFileName(fileQ);

		String output = "";

		while (output.isEmpty()) {
			System.out.println("Please enter the file name of the output file: ");
			output = stdin.readLine();
		}

		bisim.readInput(fileP, fileQ);
		bisim.performBisimulation();
		bisim.writeOutput(output);
	}

	/**
	 * This method reads the contents of the files pointed to by the Strings
	 * fileP and fileQ. If any argument is null (or a file with that name does
	 * not exist), the user is (repeatedly) asked to enter a filename until a
	 * valid file is found.
	 * 
	 * @param fileP
	 * @param fileQ
	 */
	public void readInput(final String fileP, final String fileQ) {
		try {
			this.processP = Process.parse(fileP, "Process P");
			this.processQ = Process.parse(fileQ, "Process Q");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String checkFileName(String fileName) {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			if (fileName != null && new File(fileName).exists()) {
				break;
			}

			System.out.println("File \"" + fileName + "\" not found, please try again: ");

			try {
				fileName = stdin.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return fileName;
	}

	/**
	 * performs partition-based LTS bisimulation. This method must check if
	 * valid input files (us- ing the readInput() method) have been read by the
	 * BisimulationChecker object. If files have not yet been read, display an
	 * error message on the console.
	 */
	public void performBisimulation() {
		this.compute = new BisimilarComputation(this.processP, this.processQ);
	}

	/**
	 * Saves the results of the bisimulation by overwriting the contents of the
	 * file pointed to by the String filename. If the filename is null then user
	 * is asked to enter a filename on the console.
	 * 
	 * @param filename
	 */
	public void writeOutput(final String filename) {
		try {
			FileWriter out = new FileWriter(new File(filename));

			out.write(this.processP.toString());
			out.write(this.processQ.toString());
			out.write(this.compute.toString());

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
