 
import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;
/**
 * This class reads the contents of a ballot. 
 * @author Tanner Yilmaz
 */
public class BallotReader
{
	/**
	 * Reads the contents of an input file and creates new candidate objects
	 * given the names of the candidates in the input file. Adds these candidates
	 * to a Ballot instance.
	 * @param filename Name of the input file.
	 * @return A ballot with candidates and an office name. 
	 * @throws IOException 
	 */
	public static Ballot readBallot(String filename)
	throws IOException
	{
		FileReader file = new FileReader(filename);
		Scanner sc = new Scanner(file);
		
		String officeName = sc.nextLine();
		Ballot ballot = new Ballot(officeName);
		sc.nextLine();
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] split = line.split(";");
			ballot.addCandidate(new Candidate(split[0], split[1]));
		}
		sc.close();
		
		return ballot;
	}
}

 