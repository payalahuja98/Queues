public class QueueSimulation{
	final int MAXTIME = Integer.MAX_VALUE;
	CustomerGenerator custmGen;
	float avgWaitingTime = 0.0f;
	
	public QueueSimulation(int minIAT, int maxIAT, int minSer, int maxSer){
		custmGen = new CustomerGenerator(minIAT, maxIAT, minSer, maxSer);
	}
	public float getAvgWaitingTime(){
		return avgWaitingTime;
	}
	public void simulate(int numQueues, int numCustomers) throws QueueUnderflowException{
		GlassQueue<Customer>[] queues = new GlassQueue[numQueues];
		Customer nextCustm;
		Customer custm;
		int totalWaitingTime = 0;
		int custmInCount = 0;
		int custmOutCount = 0;
		int nextArrTime;
		int nextDepTime;
		int nextQueue;
		int shortest;
		int shortestSize;
		Customer rearCustm;
		int finishTime;
		
		for(int i = 0; i < numQueues; i++){
			queues[i] = new GlassQueue<Customer>();
		}
		custmGen.reset();
		nextCustm = custmGen.nextCustomer();
		while(custmOutCount < numCustomers){
			if(custmInCount != numCustomers){
				nextArrTime = nextCustm.getArrivalTime();
				//System.out.println("Arrival Time " + nextArrTime);
			}
			else{
				nextArrTime = MAXTIME;
			}
			nextDepTime = MAXTIME;
			nextQueue = -1;
			
			for(int j = 0; j < numQueues; j++)
				if(queues[j].size() != 0){
					//System.out.println("queues[" + j + "].size()" + queues[j].size());
					
					custm = queues[j].peekFront();
					if(custm.getFinishTime() < nextDepTime){
						//System.out.println("custm.getFinishTime() " + custm.getFinishTime());
						nextDepTime = custm.getFinishTime();
						nextQueue = j;
					}
				}
			

				if(nextArrTime < nextDepTime){
					shortest = 0;
					shortestSize = queues[0].size();
					for(int k = 1; k < numQueues; k++){
						if(queues[k].size() < shortestSize){
							shortest = k;//mistake
							shortestSize = queues[k].size();
						}
					}
					if(shortestSize == 0){
						finishTime = nextCustm.getArrivalTime() + nextCustm.getServiceTime();
						//System.out.println("finishTime in if loop " + finishTime);
					}
					else{
						rearCustm = queues[shortest].peekRear();
						finishTime = rearCustm.getFinishTime() + nextCustm.getServiceTime();
						//System.out.println("finishTime in else loop " + finishTime);
					}
					nextCustm.setFinishTime(finishTime);
					queues[shortest].enqueue(nextCustm);
					custmInCount++;
					
					if(custmInCount < numCustomers){
						nextCustm = custmGen.nextCustomer();
					}
						
				}
				else{
					custm = queues[nextQueue].dequeue();
					totalWaitingTime = totalWaitingTime + custm.calcWaitingTime();
					//System.out.println("Waiting Time " + custm.calcWaitingTime());
					//System.out.println(totalWaitingTime);
					custmOutCount++;
				}
		}
				avgWaitingTime = totalWaitingTime/(float)numCustomers;
  }
}