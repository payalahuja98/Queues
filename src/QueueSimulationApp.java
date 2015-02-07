import java.util.Scanner;
public class QueueSimulationApp {
	public static void main(String[] args) throws QueueUnderflowException{
		Scanner input = new Scanner(System.in);
		int minIAT;
		int maxIAT;
		int minST;
		int maxST;
		int numQueues;
		int numCustomers;
		
		System.out.println("Enter minimum inter-arrival time");
		minIAT = input.nextInt();
		System.out.println("Enter maximum inter-arrival time");
		maxIAT = input.nextInt();
		System.out.println("Enter minimum service time");
		minST = input.nextInt();
		System.out.println("Enter maximum service time");
		maxST = input.nextInt();
			
		QueueSimulation sim = new QueueSimulation(minIAT, maxIAT, minST, maxST);
		
		do{
			System.out.println("Enter number of queues");
			numQueues = input.nextInt();
			System.out.println("Enter number of customers");
			numCustomers = input.nextInt();
			input.nextLine();
			sim.simulate(numQueues, numCustomers);
			System.out.println("Average Waiting Time: " + sim.getAvgWaitingTime());
			System.out.println("Another simulation?");
			
		}
		while(input.nextLine().equalsIgnoreCase("y"));
		System.out.println("Program completed");
	}
}
