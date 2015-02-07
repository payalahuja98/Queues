public class LinkedUnboundedQueue<T> implements UnboundedQueueInterface<T> {
	protected LLNode<T> front;
	protected LLNode<T> rear;
	
	public LinkedUnboundedQueue(){
		front = null;
		rear = null;
	}
	public void enqueue(T element){
		LLNode<T> newNode = new LLNode<T>(element);
		if(rear == null){
			front = newNode;
		}
		else{
			rear.setLink(newNode);
		}
		rear = newNode;
	}
	public T dequeue(){
		if(isEmpty()){
			throw new QueueUnderflowException("Dequeue attempted on empty queue");
		}
		else{
			T element;
			element = front.getLink();
			front = front.getLink();
			if(front == null){
				rear = null;
			}
			return element;
		}
	}
}
