public class ArrayUnboundedQueue<T> implements UnboundedQueueInterface<T>{
	protected final int DEFCAP = 100;
	protected T[] queue;
	protected int origCap;
	protected int numElements = 0;
	protected int front = 0;
	protected int rear = -1;
	
	public ArrayUnboundedQueue(){
		queue = (T[]) new Object[DEFCAP];
		rear = DEFCAP - 1;
		origCap = DEFCAP;
	}
	public ArrayUnboundedQueue(int size){
		queue = (T[]) new Object[size];
		rear = size - 1;
		origCap = size;
	}
	private void enlarge(){
		T[] larger = (T[]) new Object[queue.length + origCap];
		int currSmaller = front;
		for(int currLarger = 0; currLarger < numElements; currLarger++){
			larger[currLarger] = queue[currSmaller];
			currSmaller = (currSmaller + 1) % queue.length;
		}
		queue = larger;
		front = 0;
		rear = numElements - 1;
	}
	public void enqueue(T element){
		if(numElements == queue.length){
			enlarge();
		}
		rear = (rear + 1) % queue.length;
		queue[rear] = element;
		numElements++;
	}
	public T dequeue() throws QueueUnderflowException{
		if(isEmpty()){
			throw new QueueUnderflowException("Dequeue attempted on empty queue");
		}
		else{
			T toReturn = queue[front];
			queue[front] = null;
			front = (front + 1) % queue.length;
			numElements--;
			return toReturn;
			
		}
	}
	public boolean isEmpty(){
		return (numElements == 0);
	}
}
