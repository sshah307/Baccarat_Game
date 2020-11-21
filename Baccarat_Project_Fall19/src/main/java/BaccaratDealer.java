import java.util.ArrayList;
import java.util.Collections;

class BaccaratDealer{
    ArrayList<Card> deck = new ArrayList<Card>(); //initializing the deck

    public void generateDeck()
    {
        String suite;
		
        //4 suites and 13 car each
        for (int i = 0; i <= 3; i++)
        {
            for (int j = 1; j <= 13; j++)
            {
                if (i == 1) 
                    suite = "C";   //heart

				else if (i == 2) 
                    suite = "D";    //spade

				else if (i == 3)
                    suite = "H";  //diamond

				else
                    suite = "S";     //club

                deck.add(new Card(suite, j));  //these are inputting the suite and number Not the value
            }
        }
    }
    
	public ArrayList<Card> dealHand()
    {
        ArrayList<Card> hand = new ArrayList<>(2); 
		hand.add(drawOne());  //adding random cards to the array of player or banker
		hand.add(drawOne());  //adding random cards to the array of player or banker
        return hand;
    }
	
	void emptyDeck()
    {
        deck.clear();
    }
	
    public Card drawOne()
    {
        return deck.remove(0); 
    }

    public void shuffleDeck()
    {
        Collections.shuffle(deck); 
    }

    public int deckSize()
    {
        return deck.size(); 
    }
}
