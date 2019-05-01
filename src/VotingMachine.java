import java.util.Scanner;

public class VotingMachine {
	private ElectionData ED;
	private Scanner keyboard = new Scanner(System.in);
	
	public void printBallot() {
		System.out.println("The candidates are ");
		for (String s : this.ED.getBallot()) {
			System.out.println(s);
		}
	}

	public void screen() {
		this.printBallot();
		System.out.println("Who do you want to vote for?");
		String candidate = keyboard.next();
		ED.getVotes().add(candidate);
		System.out.println("You voted for " + candidate);
	}
	
}
