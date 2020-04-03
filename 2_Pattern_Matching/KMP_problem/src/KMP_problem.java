import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KMP_problem {
	public static void main(String[] args) {
		char[] string = new char[100];
		char[] pattern = new char[100];
		int[] failure = new int[100];
		
		String location = "C:\\Users\\corallines\\eclipse-workspace\\probable-giggle\\2_Pattern_Matching\\KMP_problem\\src\\";

		File string_file = new File(location + "string.txt");
		File pattern_file = new File(location + "pattern.txt");
		
		try {
			Scanner scanner = new Scanner(string_file);
			string = (scanner.nextLine()).toCharArray();
			scanner.close();
			
			scanner = new Scanner(pattern_file);
			pattern = (scanner.nextLine()).toCharArray();
			scanner.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File Error");
		}
		
		KMP(pattern, string, failure);
	}
	
	static void initializeF(char[] P, int[] F) {
		int m = P.length;
		int k = -1;
		F[0] = -1;

		for (int i = 1; i <m; i++) {
			k = F[i-1];
			while(k >= 0 && P[k] != P[i-1]) {
				k = F[k];
			}
			if (P[i] == P[k+1]) {
				F[i] = k + 1;				
			}
			else {
				F[i] = -1;
			}
		}
		System.out.print("f:");
		for (int i = 0; i < m; i++) {
			System.out.print(" " + F[i]);
		}
		System.out.println("");
	}

	static void KMP(char[] P, char[] T, int[] F) {
		int count = 0;
		int j = 0;
		int n = T.length;
		int m = P.length;
		
		initializeF(P, F);
		
		for (int i = 0; i < n; i++) {
			while (j > 0 && T[i] != P[j]) {
				j = F[j - 1] + 1;
			}
			
			if (T[i] == P[j]) {
				if (j == m - 1) {
					count++;
					System.out.println("matching position " + count + ": " + (i - m + 1));
					j = F[j] + 1;
				}
				else {
					j++;
				}
			}
		}
		System.out.println("the number of pattern matching : " + count);
	}
}
