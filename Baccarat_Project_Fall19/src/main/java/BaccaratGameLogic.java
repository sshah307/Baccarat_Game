import java.util.ArrayList;


public class BaccaratGameLogic extends BaccaratDealer
{

   public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) //comparing the hands and seeing who won the round
    {
        double pScore = handTotal(hand1);    //Player's score
        double bScore = handTotal(hand2);    //Banker's score

        if(pScore  ==  bScore)
			return ("Tied");

        else if (pScore > bScore)
			return ("Player Wins");                 
        
		else
			return ("Banker Wins");                    
    }

	
	public int handTotal(ArrayList<Card> hand)  
    {
        int i = 0, calcScore = 0, scoreTemp = 0;
		int sizeTemp = hand.size();
        
        while(i < sizeTemp)         //until hand doesn't reach the size
        {
            scoreTemp = scoreTemp + hand.get(i).getValue(); //adding score of every card
            i=i+1;               
			calcScore = scoreTemp % 10;   //dividing it by 10 to get last number
        }

        return calcScore;
    }

     public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard)
    {
		//Banker has total of 7: Banker always stands.
		if(handTotal(hand) == 7)
            return false;
        
		else if(
		(playerCard.getValue() != 8 && handTotal(hand) == 3) ||                                   //        Banker draws if Banker has total of 3 and Player's Third Card is 1-2-3-4-5-6-7-9-0 (not 8)
		((playerCard.getValue() > 1 && playerCard.getValue() < 8) && handTotal(hand) == 4) ||     //        Banker draws if Banker has total of 4 and Player's Third Card is 2-3-4-5-6-7
		((playerCard.getValue() > 3 && playerCard.getValue() < 8) && handTotal(hand) == 5) ||     //        Banker draws if Banker has total of 5 and Player's Third Card is 4-5-6-7
		((playerCard.getValue() == 6 || playerCard.getValue() == 7) && handTotal(hand) == 6) ||   //        Banker draws if Banker has total of 6 and Player's Third Card is of 6-7
		 handTotal(hand) < 3)                                                                     //        Banker draws if Banker  0, 1, 2 and Banker always draws a third card.                                          
            return true;
        
		else
			return false;
    }

    public boolean evaluatePlayerDraw(ArrayList<Card> hand)
    {
        if(handTotal(hand) >= 6)
            return false;

		return true;
    }
	
    public boolean checkWinIntDraw(double pScore, double bScore) 
    {
		//if there is winner when Dealer has dealt first 2 hands
        if(((pScore == 9 && bScore == 9) || (bScore == 8 && pScore == 8)) ||    //Natural hand and also a tie
		(pScore == 9 || pScore == 8) ||                                         //Natural hand
		(bScore == 9 || bScore == 8) ||                                         //Natural hand
		(bScore == 7 && pScore == 7))                                           //If both got 7 they both must stay and therefore it will be a tie
            return true;
        
		//dealer will draw a card
        return false; 
    }
}
