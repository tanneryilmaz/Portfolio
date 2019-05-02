 

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * This class writes the outputs of an election to an output file. Each candidate
 * on a ballot along with the number of votes received by each candidate is written
 * to the output file. The winner of the election is also written to the output 
 * file.
 * @author Tanner Yilmaz
 */
public class ResultWriter
{
	/**
	 * Writes the results of an election into an output file.
	 * @param filename Name of the file to be written to.
	 * @param ballot The ballot whose results are to be written to the output file.
	 * @throws IOException
	 */
	public static void writeResults(String filename, Ballot ballot)
	throws IOException
	{
		
		BufferedWriter file = new BufferedWriter(new FileWriter(filename));
		
		int longCandidate = longestCandidate(ballot);
		String column1Format = "%-" + longCandidate + "s";
		String column2Format = "%12d"; //column for vote count
		String columnsFormat = column1Format + " " + column2Format;
		
		file.write("RESULTS - " + ballot.getOfficeName());
		file.newLine();
		
		int dashes = 10 + ballot.getOfficeName().length();
		
		for(int i = 0; i < dashes; i++) {
			file.write("-");
		}
		
		file.newLine();
		
		for(Candidate candidate : ballot.getCandidates()) {
			file.write(String.format(columnsFormat, candidate.toString(), candidate.getVoteCount()));
			file.newLine();
		}
		
		file.newLine();
		
		if(!ballot.isTie()) {
			Candidate winner = ballot.determineWinner();
			file.write("WINNER: " + winner.getName() + " - " + winner.getAffiliation());
		}
		else {
			file.write("NO WINNER");
		}
		
		file.close();
	}
	
	/**
	 * @param ballot The ballot 
	 * @return Returns the length of the longest candidate's tag on the ballot. A tag is
	 * the string resulting from the concatenation of a candidate's name with their party 
	 * affiliation.
	 */
	public static int longestCandidate(Ballot ballot) {
		
		Candidate longestCandidate = ballot.getCandidates().get(0);
		
		for(Candidate currCandidate : ballot.getCandidates()) {
			
			if(currCandidate.toString().length() > longestCandidate.toString().length()) {
				longestCandidate = currCandidate;
			}
		}
		return longestCandidate.toString().length();
	}
}
