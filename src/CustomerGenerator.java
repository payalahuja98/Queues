import java.util.Random;
public class CustomerGenerator{
	private int minIATime;
	private int maxIATime;
	private int minSerTime;
	private int maxSerTime;
	private int currTime;
	
	public CustomerGenerator(int minia, int maxia, int mins, int maxs){
		minIATime = minia;
		maxIATime = maxia;
		minSerTime = mins;
		maxSerTime = maxs;
	}
	public Customer nextCustomer(){
		Random num = new Random();
		int iat = minIATime + num.nextInt(maxIATime);
		int st = minSerTime + num.nextInt(maxSerTime);
		Customer newC = new Customer(iat,st);
		newC.setFinishTime(iat + st);
		return newC;
	}
	public void reset(){
		currTime = 0;
	}
}