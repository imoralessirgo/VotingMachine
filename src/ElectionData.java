
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

class ElectionData {
	private LinkedList<String> ballot = new LinkedList<String>();
	private LinkedList<String> votes = new LinkedList<String>();
	private Scanner keyboard = new Scanner(System.in);

	private HashMap<String, Integer> FirstChoice;
	private HashMap<String, Integer> SecondChoice;
	private HashMap<String, Integer> ThirdChoice;

	public ElectionData() {
		//this.ballot.add("Gompei");
		//this.ballot.add("Husky");

		this.FirstChoice = new HashMap<String, Integer>();
		this.SecondChoice = new HashMap<String, Integer>();
		this.ThirdChoice = new HashMap<String, Integer>();
	}



	//	public int countVotes(String forcand) {
	//		int numvotes = 0;
	//		for (String s : votes) {
	//			if (s.equals(forcand))
	//				numvotes = numvotes+1;
	//		}
	//		return numvotes;
	//	}
	
	/**
	 * ProcessVote
	 * Method takes in user input votes and processes the info
	 * 
	 * @param FChoice First Choice candidate
	 * @param SChoice Second Choice candidate
	 * @param TChoice Third Choice candidate
	 * @throws DuplicateVotesException 
	 * exception thrown when same candidate is entered more than once
	 * @throws UnknownCandidateException
	 * exception thrown when candidate entered is not on the ballot
	 */
	public void processVote(String FChoice, String SChoice, String TChoice) throws DuplicateVotesException, UnknownCandidateException{
		if (!ballot.contains(FChoice)) {throw new UnknownCandidateException(FChoice);} 
		else if (!ballot.contains(SChoice)) {throw new UnknownCandidateException(SChoice);} 
		else if (!ballot.contains(TChoice)) {throw new UnknownCandidateException(TChoice);} 
		else if (FChoice.equals(SChoice) || FChoice.equals(TChoice)) {throw new DuplicateVotesException(FChoice);}
		else if (SChoice.equals(TChoice)) {throw new DuplicateVotesException(SChoice);}
		else {
			int firstVoteCount = FirstChoice.get(FChoice);
			FirstChoice.replace(FChoice, firstVoteCount + 1);

			int secondVoteCount = SecondChoice.get(SChoice);
			SecondChoice.replace(SChoice, secondVoteCount + 1);

			int thirdVoteCount = ThirdChoice.get(TChoice);
			ThirdChoice.replace(TChoice, thirdVoteCount + 1);
		}
	}

	/**
	 * addCandidate
	 *adds a candidate to the ballot
	 * 
	 * @param candidate String (Name of candidate)
	 * @throws CandidateExistsException
	 */
	public void addCandidate(String candidate) throws CandidateExistsException {
		if (!ballot.contains(candidate)) {
			ballot.add(candidate);
			FirstChoice.put(candidate, 0);
			SecondChoice.put(candidate, 0);
			ThirdChoice.put(candidate, 0);
		} else {
			throw new CandidateExistsException(candidate);
		}
	}

	/**
	 * findWinnerMostVotes
	 * Method finds candidate with at least 50% of the first choice votes
	 * 
	 * @return candidate name
	 */
	public String findWinnerMostFirstVotes() {
			String winnerName = "";
			int firstVotes = 0;
			for (String candidate : ballot) {
				if (FirstChoice.get(candidate) > firstVotes) {
					winnerName = candidate;
					firstVotes = FirstChoice.get(candidate);
				}
			}
			return winnerName;
	}

	/**
	 * findWinnerMostPoints
	 * method looks for the candidate with the largest amount of points
	 * 
	 * @return candidate name
	 */
	public String findWinnerMostPoints() {
		String winner = "";
		int winnerVotes = 0;
		for (String candidate : ballot) {
			if (countTotalPoints(candidate) > winnerVotes) {
				winner = candidate;
				winnerVotes = countTotalPoints(candidate);
			}
		}
		return winner;
	}

	/**
	 * countTotalPoints
	 * 
	 * @param candidate name
	 * @return sum of all points
	 */
	private int countTotalPoints(String candidate) {
		return FirstChoice.get(candidate) * 3 + SecondChoice.get(candidate) * 2 + ThirdChoice.get(candidate);
	}
	
	public LinkedList<String> getBallot(){
		return this.ballot;
	}

	public LinkedList<String> getVotes(){
		return this.votes;
	}
}
