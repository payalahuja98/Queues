
//Julia Schmidt & Payal Ahuja
//Brookfield Central HS

public class HelpDesk {
	
	private int time = 0;
	private int wait = -999;
	private boolean notBusy = true;
	Student current = null;
	private ArrayBoundQueue<String> log = new ArrayBoundQueue <String>(15000);
	private ArrayBoundQueue<Student> queueOne = new ArrayBoundQueue <Student>(3);
	private ArrayBoundQueue<Student> queueTwo = new ArrayBoundQueue <Student>(3);
	private ArrayBoundQueue<Student> queueThree = new ArrayBoundQueue <Student>(3);
	private ArrayBoundQueue<Student> queueFour = new ArrayBoundQueue <Student>(3);
	
	//HelpDesk
		public void step() throws QueueUnderflowException //advances time, helps students, and moves through wait stack
, QueueOverflowException
			{
				time++; //advances time
				
				if(!notBusy) //if someone is being helped
				{
					if(current.getHelp() > 0) //and they are not done being helped
					{
						current.helpThem();	//help them (help = help-1)
					}
					else //if the person being helped is done being helped & the wait stack is not empty
					{
						if(!queueOne.isEmpty())
						{
							log.enqueue("Time " + time + ", Finished helping " + current.getName() + " from COSC " + current.getCourse() + "\n"); //log that the person is finished
							current = queueOne.dequeue(); //help the next person from the wait stack
							log.enqueue("Time " + time + ", Started helping " + current.getName() + " from COSC" + current.getCourse() + "\n"); //log that the person is being helpe
							notBusy = false; //set the desk to busy just in case
						}
						else if(!queueTwo.isEmpty())
						{
							log.enqueue("Time " + time + ", Finished helping " + current.getName() + " from COSC " + current.getCourse() + "\n"); //log that the person is finished
							current = queueTwo.dequeue(); //help the next person from the wait stack
							log.enqueue("Time " + time + ", Started helping " + current.getName() + " from COSC" + current.getCourse() + "\n"); //log that the person is being helped
							notBusy = false; //set the desk to busy just in case
						}
						else if(!queueThree.isEmpty())
						{
							log.enqueue("Time " + time + ", Finished helping " + current.getName() + " from COSC " + current.getCourse() + "\n"); //log that the person is finished
							current = queueThree.dequeue(); //help the next person from the wait stack
							log.enqueue("Time " + time + ", Started helping " + current.getName() + " from COSC" + current.getCourse() + "\n"); //log that the person is being helped
							notBusy = false; //set the desk to busy just in case
						}
						else if(!queueFour.isEmpty())
						{
							log.enqueue("Time " + time + ", Finished helping " + current.getName() + " from COSC " + current.getCourse() + "\n"); //log that the person is finished
							current = queueFour.dequeue(); //help the next person from the wait stack
							log.enqueue("Time " + time + ", Started helping " + current.getName() + " from COSC" + current.getCourse() + "\n"); //log that the person is being helped
							notBusy = false; //set the desk to busy just in case
						}
						else
						{
							notBusy = true;
							log.enqueue("Time " + time + ", Finished helping " + current.getName() + " from COSC " + current.getCourse() + "\n"); //log that the person is finished
						}
					}
				}
			}

		public void addStudent(String name, int course, int workload) throws QueueOverflowException{
			
			Student incoming = new Student(name, course, workload); //create the incoming student
			wait = incoming.getWait();
			
			if(notBusy)
			{
				current = incoming;
				notBusy = false;
				log.enqueue("Time " + time + ", Started helping " + current.getName() + " from COSC" + current.getCourse() + "\n"); //log that the person is being helped
			}
			else
			{
			   if(wait < 2000 && queueOne.isFull() == false)
			   {
				//current = incoming;	//set incoming student to current student
				queueOne.enqueue(incoming);  //add new student to queue
				//log.enqueue("Time " + time + ", Started helping " + current.getName() + " from COSC" + current.getCourse() + "\n"); //log that the person is being helped
			   }
			   else if(wait < 2000 && queueOne.isFull())
			   {
				   incoming.incWait();
			   }
			   
			   if(wait >= 2000 && wait < 3000 && queueTwo.isFull() == false)
			   {
				   //current = incoming;	//set incoming student to current student
				   queueTwo.enqueue(incoming);  //add new student to queue
				   //log.enqueue("Time " + time + ", Started helping " + current.getName() + " from COSC" + current.getCourse() + "\n"); //log that the person is being helped
			   }
			   else if(wait >= 2000 && wait < 3000 && queueTwo.isFull())
			   {
				   incoming.incWait();
			   }
			   
			   if(wait >= 3000 && wait < 4000 && queueThree.isFull() == false)
			   {
					//current = incoming;	//set incoming student to current student
					queueThree.enqueue(incoming);  //add new student to queue
				//	log.enqueue("Time " + time + ", Started helping " + current.getName() + " from COSC" + current.getCourse() + "\n"); //log that the person is being helped
			   }
			   else if(wait >= 3000 && wait < 4000 && queueThree.isFull())
			   {
				   incoming.incWait();
			   }
			   
			   if(wait >= 4000 && queueFour.isFull() == false)
			   {
					//current = incoming;	//set incoming student to current student
					queueFour.enqueue(incoming);  //add new student to queue
					//log.enqueue("Time " + time + ", Started helping " + current.getName() + " from COSC" + current.getCourse() + "\n"); //log that the person is being helped
			   }
			   else if(wait >= 4000 && queueFour.isFull())
			   {
				   log.enqueue("Time " + time + ", Turned away " + incoming.getName() + " from COSC" + incoming.getCourse() + "\n"); //if no space in any level higher than course, turn away
			   }
			}
		}
			
		
		public int getTime()
			{
				return time;
			}

		public String toString()
			{
				
				if(notBusy)
				{
					return "Time " + time + ", IDLE";
				}
				else
				{
					return "Time " + time + ", Helping " + current.getName() + " from COSC" + current.getCourse();
				}
			}	
				
		public String getLog() throws QueueUnderflowException
			{
				String report = new String();
				while(!log.isEmpty())
				{
					report += log.dequeue();
				}
				
				return report;
			}

}

