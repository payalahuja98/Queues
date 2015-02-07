import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class HelpDeskRunner
{
	public static void main(String[] args) throws FileNotFoundException, QueueOverflowException, QueueUnderflowException
	{
		FileReader file = new FileReader("c:/Input2.txt");
		Scanner tokenizer = new Scanner(file);

		int rounds  = tokenizer.nextInt();
		int time    = 0;
		String name = null;
		int course  = 0;
		int work    = 0;

		HelpDesk hd = new HelpDesk();

		if (tokenizer.hasNext())
		{
			time    = tokenizer.nextInt();
			name    = tokenizer.next();
			course  = tokenizer.nextInt();
			work    = tokenizer.nextInt();
		}

		do
		{
			while (time == hd.getTime())
			{
				//System.out.println("Time " + time + ",\t" + name + " from COSC" + course + " for " + work + " minutes.");
				hd.addStudent(name, course, work);
				if (tokenizer.hasNext())
				{
					time    = tokenizer.nextInt();
					name    = tokenizer.next();
					course  = tokenizer.nextInt();
					work    = tokenizer.nextInt();
				}
				else
				{
					time = -1;
				}
			}

			System.out.println(hd);
			hd.step();
		}
		while (hd.getTime() < rounds);

		
//			System.out.println("Time " + time + ",\t" + name + " from COSC" + course + " for " + work + " minutes.");

//		System.out.println("Rounds = " + rounds);
		System.out.println();
		System.out.println("LOG:");
		System.out.println(hd.getLog());

		
	}
}
