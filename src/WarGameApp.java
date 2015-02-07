import java.util.Scanner;
public class WarGameApp {
	public static void main(String[] args) throws QueueOverflowException {
		Scanner input = new Scanner(System.in);
		System.out.println("How many games should be simulated?");
		int numGames = input.nextInt();
		System.out.println("What is the maximum number of battles?");
		int maxBat = input.nextInt();
		WarGame game = new WarGame(maxBat);
		
		int numComp = 0;
		int numDiscont = 0;
		int totalBattles = 0;
		
		for(int i = 0; i < numGames; i++){
			if(game.play()){
				numComp++;
				totalBattles = totalBattles + game.getNumBattles();
			}
			else{
				numDiscont++;
			}
		}
		System.out.println("Number of games simulated: " + numGames);
		System.out.println("Number of discontinued games: " + numDiscont);
		System.out.println("Number of completed games: " + numComp);
		
		if(numComp > 0){
			System.out.println("In the completed games ");
			System.out.println("Total number of battles: " + totalBattles);
			System.out.println("Average number of battles: " + (totalBattles/numComp));
		}
		System.out.println("Program completed.");
		
	}

}
