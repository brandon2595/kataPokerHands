/*Name: Brandon Watkins
 * Program: Kata Poker Hands 
 * Purpose: To compare several poker hand pairs and indicate higher rank and winner
 * Date: 12-14-2018
 */


/*
 * Hands Class - CONTAINS MAIN METHOD
 */
public class Hands {
	//Declare variables for cards in hand and numbers drawn
	public Cards[] cards = new Cards[5];
	private int[] values = new int[6];

	
	Hands(Deck deckCards) {
		//Hands class constructor
		//Declare boolean values for flush and straight conditions
		//int arrays for ranks and ordered ranks separate
		boolean bolStraight = false, bolFlush = true;
		int[] rankArray = new int[14];
		int[] orderedRankArray = new int[5];

		
		//Loops top of deck until 5 cards drawn
		for(int n = 0; n < 5; n++)
		{
			cards[n] = deckCards.deckDraw();
		}
		
		//Declare variables for player 1 and 2 same cards, ranks and values
		@SuppressWarnings("unused")
		int intSameCard = 1, intSameCard_2 = 1, intLargeRank = 0, intSmallRank = 0,
				index = 0, intStraightValue = 0;
		
		//Loops through rank array until all clear
		//Second loop to cycle through suits and gets rank for each card
		//Third loop to get suits for each card
		for(int i = 0; i <= 13; i++)
		{
			rankArray[i] = 0;
		}
		for(int n = 0; n <= 4; n++)
		{
			rankArray[cards[n].getRank()]++;
		}
		for(int x = 0; x < 4; x++)
		{
			if(cards[x].getSuit() != cards[x + 1].getSuit())
			{
				//if suits are not a match, not a flush
				bolFlush = false;
			}
		}
		
		//Loops down through ranks to determine which rank is higher and lower
		for(int i = 13; i >= 1; i--)
		{
			if(rankArray[i] > intSameCard)
			{
				if(intSameCard != 1)
				{
					intSameCard_2 = intSameCard;
					intSmallRank = intLargeRank;
				}
				
				intSameCard = rankArray[i];
				intSmallRank = i;
				
			}else if (rankArray[i] > intSameCard_2)
			{
				intSameCard_2 = rankArray[i];
				intSmallRank = i;
			}
		}
		
		//If a card is an ace, run through rest of cards
		if(rankArray[1] == 1)
		{
			orderedRankArray[index] = 14;
			index++;
		}
		
		for(int n = 13; n >= 2; n--)
		{
			if(rankArray[n] == 1)
			{
				orderedRankArray[index] = n;
				index++;
			}
		}
		
		//Determines if it is a straight hand or not
		//breaks cycle if straight hand is determined
		for(int n = 1; n <= 9; n++)
		{
			if(rankArray[n] == 1 && rankArray[n + 1] == 1 && rankArray[n + 2] == 1
					&& rankArray[n + 3] == 1 && rankArray[n + 4] == 1)
			{
				bolStraight = true;
				intStraightValue = n + 4;
				break;
			}
		}
		
		//Determines high card and straight	
			if(rankArray[10] == 1 && rankArray[11] == 1 && rankArray[12] == 1 &&
			
				rankArray[13] == 1 && rankArray[1] == 1)
		{
			//if all cards are same suit..
			bolStraight = true;
			intStraightValue = 14;
			
		} 
		
		for(int n = 0; n <= 5; n++)
		{
			values[n] = 0;
		}
		
		//Initiate evaluation
		if(intSameCard == 1)
		{
			values[0] = 1;
			values[1] = orderedRankArray[0];
			values[2] = orderedRankArray[1];
			values[3] = orderedRankArray[2];
			values[4] = orderedRankArray[3];
			values[5] = orderedRankArray[4];
		}
		
		//Determines pair
		if(intSameCard == 2 && intSameCard_2 == 1)
		{
			values[0] = 2;
			values[1] = intLargeRank;
			values[2] = orderedRankArray[0];
			values[3] = orderedRankArray[1];
			values[4] = orderedRankArray[2];
		}
		
		//Determines two pairs
		if(intSameCard == 2 && intSameCard_2 == 2)
		{
			values[0] = 3;
			
			values[1] = intLargeRank > intSmallRank ? intLargeRank : intSmallRank;
			values[2] = intLargeRank < intSmallRank ? intLargeRank : intSmallRank;
			values[3] = orderedRankArray[0];
		}
		
		
		
		if(intSameCard == 3 && intSameCard_2 != 2)
		{
			values[0] = 4;
			values[1] = intLargeRank;
			values[2] = orderedRankArray[0];
			values[3] = orderedRankArray[1];
		}
		
		//if hand is straight but not flush, change first value in values array
		if(bolStraight && !bolFlush)
		{
			values[0] = 5;
			values[1] = 0;
		}
		
		//if hand is flush but not straight, same as previous
		if(bolFlush && !bolStraight)
		{
			values[0] = 6;
			values[1] = orderedRankArray[0];
			values[2] = orderedRankArray[1];
			values[3] = orderedRankArray[2];
			values[4] = orderedRankArray[3];
			values[5] = orderedRankArray[4];
		}
		
		if(intSameCard == 3 && intSameCard_2 == 2)
		{
			values[0] = 7;
			values[1] = intLargeRank;
			values[2] = intSmallRank;
		}
		
		if(intSameCard == 4)
		{
			values[0] = 8;
			values[1] = intLargeRank;
			values[2] = orderedRankArray[0];
		}
		
		//if Straight and flush, determine higher
		if(bolStraight && bolFlush)
		{
			values[0] = 9;
			values[1] = 0;
		}
			
	}
	
	void display()
	{
		//Declare and initialize string for win conditions
		String strCondition = null;
		
		//switch/case algorithm for each case of win conditions
		switch(values[0])
		{
		//Condition 1: high card
			case 1: strCondition = "High Card";
			break;
		//Condition 2: Pair of cards	
			case 2: strCondition = "Single Pair ";
			break;
		//Condition 3: Two Pairs	
			case 3: strCondition = "Two pair " + Cards.rankString(values[1]) + " " + 
												 Cards.rankString(values[2]);
			break;
		//Condition 4: Three of a Kind
			case 4: strCondition = "Three of a Kind ";
			break;
		//Condition 5: High Straight
			case 5: strCondition = Cards.rankString(values[1]) + " High Straight";
			break;
		//Condition 6: Flush
			case 6: strCondition = "Flush";
			break;
		//Condition 7: Full House
			case 7: strCondition = "Full house" + Cards.rankString(values[1]) + " over " + 
												  Cards.rankString(values[2]);
			break;
		//Condition 8: Four of a Kind
			case 8: strCondition = "Four of a Kind " + Cards.rankString(values[1]);
			break;
		//Condition 9: Straight Flush
			case 9: strCondition = "Straight flush " + Cards.rankString(values[1]) + " high";
			break;
		//Default condition if others are not met
			default: strCondition = "Error in Hands/display: values[0] contains invalid value";
			
			
		}
		//Format how string will look on console
		strCondition = "             " + strCondition;
		System.out.println(strCondition);
	}
	
	void displayProgram()
	{
		//Displays program and hands
		for(int n = 0; n < 5; n++)
		{
			System.out.println(cards[n]);
		}
	}
	
	 String compareHands(Hands secondHandValue)
	{
		 //Declare and initialize strings to determine winner
		 String strBlackWinner = "Black Wins!", 
				strWhiteWinner = "White Wins!",
				strTie = "Its a Tie!";
		 
		//Compares hands
		//if first values is greater than second, white wins
		//if second values are greater, black wins
		for(int n = 0; n < 5; n++)
		{
			if(this.values[n] > secondHandValue.values[n])
			{
				return "\n" + strWhiteWinner;
			}
			else if (this.values[n] < secondHandValue.values[n])
			{
				return "\n" + strBlackWinner;
			}
		}
				//Else, its a tie
				return strTie;
		}
	
	public static void main(String[] args) {
		
		//Main method to carry out program execution
		//Create instance of deck class to produce deck and hand to players
		//Output results
		Deck deck = new Deck();
		Hands firstHands = new Hands(deck);
		Hands secondHands = new Hands(deck);
		System.out.println("Player White");
		firstHands.display();
		firstHands.displayProgram();
		System.out.println("\nPlayer Black");
		secondHands.display();
		secondHands.displayProgram();
			
		//Create new separate instance of Hands class
		//Output program
		Hands obj = new Hands(deck);
		System.out.println(obj.compareHands(secondHands));
	
	}
}
