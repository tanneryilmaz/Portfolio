import junit.framework.TestCase;

public class ElectionTester extends TestCase {
	
	Ballot tstBallot = new Ballot("Testing Office Name");
	ResultWriter tstWriter = new ResultWriter();
	
	Candidate tstCandidate = new Candidate("Test Name", "Test Party");
	Candidate tstCandidate1 = new Candidate("Test Name 1", "Test Party 1");
	
	public void test() {
		
		tstBallot.addCandidate(tstCandidate);
		tstBallot.addCandidate(tstCandidate1);

		assert tstCandidate.getAffiliation() == "Test Party";
		assert tstCandidate.getName() == "Test Name";
		assert tstCandidate.getVoteCount() == 0;
		
		tstCandidate.tallyVote();
		tstCandidate1.tallyVote();
		
		assert tstCandidate.getVoteCount() == 1;
		assert tstCandidate.compareTo(tstCandidate1) == 0;
		
		tstCandidate1.tallyVote();
		assert tstCandidate1.compareTo(tstCandidate) == 1;
		assert tstCandidate.compareTo(tstCandidate1) == -1;
		
		assert tstBallot.isTie() == false;
		
		tstCandidate.tallyVote();
		assert tstBallot.isTie() == true;	
		
	}

}
