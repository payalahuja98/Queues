public class Palindromes{
	public static boolean test(String candidate){
		char charac;
		int length;
		int numLetters;
		int charCount;
		
		char fromStack;
		char fromQueue;
		boolean stillPal;
		
		BoundedStackInterface<Character> stack;
		BoundedQueueInterface<Character> queue;
		
		length = candidate.length();
		stack = new ArrayStack<Character>(length);
		queue = new ArrayBoundQueue<Character>(length);
		candidate = candidate.toLowerCase();
		for(int i = 0; i < length; i++){
			if(candidate.charAt(i) > 97 || candidate.charAt(i) < 123){
				stack.push(candidate.charAt(i));
				queue.enqueue(candidate.charAt(i));
				
			}
		}
		for(int j = 0; j < length; j++){
			if(stack.pop() == queue.dequeue()){
				stillPal = true;
			}
			else{
				stillPal = false;
			}
		}
		return stillPal;
		
	}
}
