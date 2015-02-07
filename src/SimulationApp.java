import java.util.Scanner;
public class SimulationApp{
	public static void main(String[] args) throws QueueUnderflowException{
		Scanner input = new Scanner(System.in);
		String skip;
		System.out.println("Enter minIAT");
		int minIAT = input.nextInt();
		System.out.println("Enter maxIAT");
		int maxIAT = input.nextInt();
		System.out.println("Enter minST");
		int minST = input.nextInt();
		System.out.println("Enter maxST");
		int maxST = input.nextInt();
		Simulation sim = new Simulation(minIAT, maxIAT, minST, maxST);
		System.out.println("Enter numQueues");
		int numQueues = input.nextInt();
		System.out.println("Enter numCustomers");
		int numCust = input.nextInt();
		skip = input.nextLine();
		sim.simulate(numQueues, numCust);
		System.out.println("Average Waiting Time: " + sim.getAvgWaitTime());
	}
}
