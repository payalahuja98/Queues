public class GlassQueue<T> extends ArrayUnboundedQueue<T>{
	public GlassQueue(){
		super();
	}
	public GlassQueue(int origCap){
		super(origCap);
	}
	public int size(){
		return numElements;
	}
	public T peekFront(){
		return queue[front];
	}
	public T peekRear(){
		return queue[rear];
	}
}