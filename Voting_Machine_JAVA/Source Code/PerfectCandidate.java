import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * "This is a program for a simplified voting machine with a graphical user interface 
 * (GUI). When started up, it reads information about a single race from an input 
 * file, then displays a ballot. The ballot can be used to cast votes indiscriminately
 *  — there's nothing in our program that forces users to "log in" or otherwise 
 *  identify themselves before casting a vote — with the votes being counted 
 *  underneath the covers, but not displayed to the user while the voting is in 
 *  progress. After closing the ballot window, the program saves the results of 
 *  the election into an output file, formatted in a particular way."
 *  
 *  This program allows the user to vote in an election. A GUI pops up and 
 *  allows the user to vote for candidates. At the end of the election, the 
 *  winner's name is written into an output file along with the number of votes each
 *  candidate received
 * @author Tanner Yilmaz
 */
public class PerfectCandidate
{
	public static void main(String[] args)
	{
		Ballot ballot = null;

		do
		{
			FileSelector inputSelector = new FileSelector(
				null, "Select Input File", "Path to input file", true);
			
			inputSelector.setVisible(true);
	
			String inputFilename = inputSelector.getSelectedFile();
	
			if (inputFilename == null)
			{
				return;
			}
	
			try
			{
				ballot = BallotReader.readBallot(inputFilename);
			}
			catch (IOException e)
			{
				JOptionPane.showMessageDialog(null,
					"Could not open and read input file",
					"Input Error",
					JOptionPane.ERROR_MESSAGE);
			}
		}
		while (ballot == null);

		BallotDialog bf = new BallotDialog(null, ballot);
		bf.setVisible(true);

		FileSelector outputSelector = new FileSelector(
			null, "Select Output File", "Path to output file", false);
			
		outputSelector.setVisible(true);
	
		String outputFilename = outputSelector.getSelectedFile();

		if (outputFilename == null)
		{
			return;
		}

		try
		{
			ResultWriter.writeResults(outputFilename, ballot);
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null,
				"Could not write results to output file",
				"Output Error",
				JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}
