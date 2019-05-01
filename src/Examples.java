import static org.junit.Assert.*;

import org.junit.Test;

public class Examples {

	 ElectionData ED = new ElectionData();
	 ElectionData ED1 = new ElectionData();
	 
	 public Examples() {
		try {
			ED.addCandidate("gompei");
			ED.addCandidate("husky");
			ED.addCandidate("ziggy");
		 }catch(Exception e){}
	    
		
		try {
	      ED.processVote("gompei", "husky", "ziggy");
	      ED.processVote("gompei", "ziggy", "husky");
	      ED.processVote("husky", "gompei", "ziggy");
	    } catch (Exception e) {}
		
		try {
			ED1.addCandidate("a");
			ED1.addCandidate("b");
			ED1.addCandidate("c");
			ED1.addCandidate("d");
		 }catch(Exception e){}
	    
		
		try {
	      ED1.processVote("a", "b", "d");
	      ED1.processVote("d", "b", "a");
	      ED1.processVote("c", "d", "a");
	      ED1.processVote("c", "d", "a");
	    } catch (Exception e) {}
		
		
		
		
	 }

	  // now run a test on a specific election
	  @Test
	  public void testMostFirstWinner () {
	    assertEquals ("gompei", ED.findWinnerMostFirstVotes());
	  }
	  
	  @Test
	  public void testMostFirstWinnerFail () {
	    assertFalse (ED1.findWinnerMostFirstVotes().equals("d"));
	  }
	  
	  @Test
	  public void testMostPointsWinner () {
		    assertEquals ("gompei", ED.findWinnerMostPoints());
	  }
	  
	  @Test
	  public void testMostPointsWinner2 () {
		    assertEquals ("d", ED1.findWinnerMostPoints());
	  }
		  

}
