
public class Cards {

	//Create arrays for suits and ranks
	//Decision to make string array for ranks since both letters and numbers are included
	private static String[] strSuit = new String[]{"C", "D", "H", "S"};
	private static String[] strRank = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"}; 
	
	//Create and initialize short variables for suit and rank
	//Use of short data type to save unnecessary use of storage and memory
	private short srtRank = 0, srtSuit = 0;
	
	
	Cards(short srtSuits, short srtRanks)
	{
		//Constructor
		this.srtSuit = srtSuits;
		this.srtRank = srtRanks;
	}
	
	public String toString()
	{
		//Converts short data type numbers into strings
		return strRank[srtRank] + strSuit[srtSuit];
	}
		
	
	
	public static String rankString(int ranks){
		//Create rank string method
		//Return the rank array with index as parameter
		return strRank[ranks].toString();
	}
	
	public short getRank(){
		//Getter method for ranks
		return srtRank;
		
	}
	
	public short getSuit(){
		//Getter method for suits
		return srtSuit;
	}

}
