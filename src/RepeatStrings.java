import java.util.Scanner;
public class RepeatStrings{
	public static void main(String[] args) throws QueueOverflowException, QueueUnderflowException {
		Scanner input = new Scanner(System.in);
		BoundedQueueInterface<String> message = new ArrayBoundQueue<String>(3);
		for(int i = 0; i < 3; i++){
			message.enqueue(input.nextLine());
		}
		while(message.isEmpty() == false){
			System.out.println(message.dequeue());
		}

	}

}
