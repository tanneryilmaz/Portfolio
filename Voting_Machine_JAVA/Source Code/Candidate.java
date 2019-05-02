/**
 * This class represents a candidate in an election. Instances of this class are
 * stored in an ArrayList in the Ballot class. Each candidate in the ArrayList corre-
 * sponding to a candidate being voted for on that ballot.   
 * @author Tanner Yilmaz
 */
public class Candidate implements Comparable<Candidate>
{
	/**
	 * Name of the candidate.
	 */
	private final String NAME;
	
	/**
	 * Party that the candidate is affiliated with. 
	 */
	private String affiliation;
	
	/**
	 * Number of votes the candidate has received.
	 */
	private int voteCount;
	 
	/**
	 * Initializes the name and affiliation of the candidate.
	 * @param name
	 * @param affiliation
	 */
	public Candidate(String name, String affiliation)
	{
		this.NAME = name;
		this.affiliation = affiliation;
	}

	/**
	 * Returns the name of the candidate
	 * @return name of candidate
	 */
	public String getName()
	{
		return NAME;
	}

	/**
	 * Returns the candidate's party affiliation. 
	 * @return candidate's party affiliation.
	 */
	public String getAffiliation()
	{
		 return affiliation;
	}

	/**
	 * Returns the number of votes a candidate has received.
	 * @return candidate's vote count.
	 */
	public int getVoteCount()
	{
		return voteCount;
	}
 
	/**
	 * Adds a vote to the candidate's vote count when the candidate is voted for.
	 */
	public void tallyVote()
	{
		voteCount += 1;
	}
	
	/**
	 * Compares candidates based on how many votes they have received.
	 * Candidates that have recieved more votes are considered to be larger than 
	 * those who have received less votes.
	 */
	@Override
	public int compareTo(Candidate c) {
		if(this.voteCount < c.voteCount) {
			return -1;
		}
		else if(this.voteCount > c.voteCount) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * Returns string representation of the candidate.
	 */
	@Override
	public String toString()
	{
		return NAME + " - " + affiliation;
	}
}
