import java.util.Random;

public class RankCardDeck{
	private static final int NUMCARDS = 52;
	protected int[] cardDeck = new int[NUMCARDS];
	protected int currPos;
	
	public RankCardDeck(){
		for(int i = 0; i < NUMCARDS; i++){
			cardDeck[i] = i / 4;
		}
	}
	public void shuffle(){
		Random rand = new Random();
		for(int j = (NUMCARDS - 1); j > 0; j--){
			int rLoc = rand.nextInt(j);
			int temp = cardDeck[rLoc];
			cardDeck[rLoc] = cardDeck[j];
			cardDeck[j] = temp;
			
		}
		currPos = 0;
	}
	public boolean hasMoreCards(){
		return (currPos != NUMCARDS);
	}
	public int nextCard(){
		currPos++;
		return (cardDeck[currPos - 1]);
	}
}