import java.util.Collections;
import java.util.ArrayList;

/**
 * This class represents a voting ballot. Each ballot has candidates that 
 * are running for office.
 * @author Tanner Yilmaz
 */
public class Ballot
{	
	/**
	 * The name of the office users voted for.
	 */
	private String officeName;
	
	/**
	 * List of candidates running for office.
	 */
	private ArrayList<Candidate> candidates = new ArrayList<Candidate>(0);
	
	/**
	 * Winner of the election.
	 */
	private Candidate winner;
 
	/**
	 * Initializes the name of the office being run for.
	 * @param officeName
	 */
	public Ballot(String officeName)
	{
		this.officeName = officeName;
	}

	/**
	 * Returns the name of the office being run for.
	 * @return name of the office being run for.
	 */
	public String getOfficeName()
	{
		return officeName;
	}
	
	/**
	 * Adds a candidate to the candidates running for office.
	 * @param c Candidate to be added.
	 */
	public void addCandidate(Candidate c)
	{
		candidates.add(c);
	}

	/**
	 * Returns the ArrayList of candidates running for office.
	 * @return ArrayList of candidates running for office.
	 */
	public ArrayList<Candidate> getCandidates()
	{
		return candidates;
	}
	
	/**
	 * Determines the winner of the election.
	 * @return Winner of the election.
	 */
	public Candidate determineWinner() {
		Collections.sort(candidates);
		return candidates.get(candidates.size() - 1);
	}
	
	/**
	 * Determines if the result of the election is a tie.
	 * @return Returns true if the election was a tie. Returns false otherwise.
	 */
	public boolean isTie() {
		Collections.sort(candidates);
		
		Candidate firstCandidate = candidates.get(candidates.size() - 1);
		Candidate secondCandidate = candidates.get(candidates.size() - 2);
		
		if(firstCandidate.compareTo(secondCandidate) == 0){
			return true;
		}
		else {
			return false;
		}
	}
}
