public class Customer{
	private int arrTime;
	private int serTime;
	private int finTime;
	
	public Customer(int at, int st){
		arrTime = at;
		serTime = st;
	}
	public void setFinishTime(int ft){
		finTime = ft;
	}
	public int getArrTime(){
		return arrTime;
	}
	public int getSerTime(){
		return serTime;
	}
	public int getFinTime(){
		return finTime;
	}
	public int getWaitTime(){
		return finTime - arrTime - serTime;
	}
}