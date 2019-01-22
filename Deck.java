//Deck class
import java.util.*;


public class Deck {
	//Declare ArrayList for cards in the deck
	public static ArrayList<Cards> deckCardsList = new ArrayList<Cards>();

	public Deck() {
		//Deck class constructor
		//Declare random number generator
		Random numGen = new Random();
		Cards tempCards;
		
		//Create and initialize index variables for card ArrayList
		int intFirstIndex = 0, intSecondIndex = 0;
					
		//x is suits, y is ranks. Adds all ranks and suits to card array
		for (short x = 0; x <= 3; x++)
		      {
		          for (short y = 0; y <= 12; y++)
		           {
		        	  //Adds cards to ArrayList
		              deckCardsList.add(new Cards(x,y));
		           }
		      }
		
		//Randomly "shuffles" all cards
		//Large number choosen to ensure deck is shuffled well
		for(short i = 0; i < 500; i++)
		{
			intFirstIndex = numGen.nextInt(deckCardsList.size());
			intSecondIndex = numGen.nextInt(deckCardsList.size());
			tempCards = (Cards) deckCardsList.get(intSecondIndex);
			deckCardsList.set(intSecondIndex, deckCardsList.get(intFirstIndex));
			deckCardsList.set(intFirstIndex, tempCards);
		}
				
	}
	
	
	public Cards deckDraw() {
		//Draws card from deck
		//Remove card from deck once drawn
		return deckCardsList.remove(deckCardsList.size() - 1);
		
	}
	
	public int getTotal() {
		//getter method
		//returns total number of cards
		return deckCardsList.size();
	}
	

}
