public class WarGame{
	protected int maxBattles;
	protected int numBattles = 0;
	boolean gameOK;
	boolean gameOver;
	static final int NUMCARDS = 52;
	BoundedQueueInterface player1;
	BoundedQueueInterface player2;
	BoundedQueueInterface prize;
	RankCardDeck deck;
	
	public WarGame(int maxB){
		maxBattles = maxB;
		deck = new RankCardDeck();
	}
	public boolean play() throws QueueOverflowException{
		player1 = new ArrayBoundQueue<Integer>(NUMCARDS);
		player2 = new ArrayBoundQueue<Integer>(NUMCARDS);
		prize = new ArrayBoundQueue<Integer>(NUMCARDS);
		gameOK = true;
		gameOver = false;
		
		deck.shuffle();
		while(deck.hasMoreCards()){
				player1.enqueue(deck.nextCard());
				if(deck.hasMoreCards()){
					player2.enqueue(deck.nextCard());
				}
		}
		numBattles = 0;
		while(!gameOver){
		  try{
			numBattles++;
			battle();
		  }
		  catch(QueueUnderflowException e){
			  gameOver = true;
			  
		  }
		
		if(numBattles == maxBattles){
			gameOver = true;
			gameOK = false;
		}
	  }
		return gameOK;
	}
	private void battle() throws QueueUnderflowException, QueueOverflowException{
		int p1Card = (Integer) player1.dequeue();
		int p2Card = (Integer) player2.dequeue();
		
		prize.enqueue(p1Card);
		prize.enqueue(p2Card);
		
		if(p1Card > p2Card){
			while(!prize.isEmpty()){
				player1.enqueue(prize.dequeue());
			}
		}
		else{
			if(p2Card > p1Card){
				while(!prize.isEmpty()){
					player2.enqueue(prize.dequeue());
				}
			}
			else{
				for(int k = 0; k < 3; k++){
					prize.enqueue(player1.dequeue());
					prize.enqueue(player2.dequeue());
					battle();
				}
			}
		}
				
			
	}
	public int getNumBattles(){
		return numBattles;
	}
}