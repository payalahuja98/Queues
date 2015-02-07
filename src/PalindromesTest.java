import java.util.Scanner;
public class PalindromesTest {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		Palindromes pal = new Palindromes();
		System.out.print(pal.test(input.nextLine()));
	}
}
