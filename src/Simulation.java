public class Simulation{
	final int MAXTIME = Integer.MAX_VALUE;
	float avgWaitTime = 0.0f;
	CustomerGenerator custGen;
	public Simulation(int minIAT, int maxIAT, int minSer, int maxSer){
		custGen = new CustomerGenerator(minIAT, maxIAT, minSer, maxSer);
	}
	public float getAvgWaitTime(){
		return avgWaitTime;
	}
	public void simulate(int numQueues, int numCustomers) throws QueueUnderflowException{
		GlassQueue<Customer>[] custQueues = new GlassQueue[numQueues];
		Customer nextCust;
		Customer temp;
		int totalWaitTime = 0;
		int custInCount = 0;
		int custOutCount = 0;
		int nextArrTime;
		int nextDepTime;
		int nextQueue;
		int shortestQueue = 0;
		int shortestSize = 0;
		Customer rearCust = null;
		int finishTime;
		
		for(int i = 0; i < numQueues; i++){
			custQueues[i] = new GlassQueue<Customer>();	 
		}
		custGen.reset();
		nextCust = custGen.nextCustomer();
		while(custOutCount < numCustomers){
			if(custInCount != numCustomers){
				nextArrTime = nextCust.getArrTime();
			}
			else{
				nextArrTime = MAXTIME;
			}
			nextDepTime = MAXTIME;
			nextQueue = -1;
			for(int i = 0; i < numQueues; i++)
				if(custQueues[i].size() != 0){
					temp = custQueues[i].peekFront();
					if(temp.getFinTime() < nextDepTime){
						nextDepTime = temp.getFinTime();
						nextQueue = i;
					}
				}
				if(nextArrTime < nextDepTime){
					//shortestQueue = 0;
					//shortestSize = 0;
					for(int i = 0; i < numQueues; i++){
						if(custQueues[i].size() < shortestSize){
							shortestSize = custQueues[i].size();
							shortestQueue = i;
						}
					}
				   if(shortestSize == 0){
					finishTime = nextCust.getArrTime() + nextCust.getSerTime();
				   }
				   else{
					rearCust = custQueues[shortestQueue].peekRear();
					finishTime = rearCust.getFinTime() + nextCust.getSerTime();
				   }
				   nextCust.setFinishTime(finishTime);
				   custQueues[shortestQueue].enqueue(nextCust);
				
				   if(custInCount < numCustomers){
					   nextCust = custGen.nextCustomer();
				   }
				}
				else{
					temp = custQueues[nextQueue].dequeue();
					totalWaitTime = totalWaitTime + temp.getWaitTime();
					custOutCount++;
				}
		}
		avgWaitTime = totalWaitTime/(float) numCustomers;
	}
}
