package nfind_problem;
import java.io.*;
import java.util.*;

public class Nfind_problem {
	public static void main(String[] args) {
		char[] string = new char[100];
		char[] pattern = new char[100];
		String location = "C:\\Users\\corallines\\eclipse-workspace\\probable-giggle\\2_Pattern_Matching\\nfind_problem\\src\\nfind_problem\\";

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
		
		find_control(string, pattern);
	}
	
	static void find_control(char[] string, char[] pat) {
		int start = -1;
		int count = 0;
		int lasts = string.length - 1;
		
		while (true) {
			start = find(start, string, pat);
			if (start == -1) break;
			count++;
			System.out.println("matching position " + count + " : " + start);
		}
		System.out.println("the number of pattern matching : " + count);
	}
	
	static int find(int start, char[] string, char[] pat) {
		int fix_start = start + 1;		
		int i, j = 0;
		int lasts = string.length - 1;
		int lastp = pat.length - 1;
		int endmatch = fix_start + lastp;
		
		for (i = fix_start; endmatch <= lasts; endmatch++, fix_start++) {
			if (string[endmatch] == pat[lastp]) {
				for (j=0, i = fix_start; j < lastp && string[i] == pat[j]; i++, j++) {
				}
			}
			
			if (j == lastp) {
				return fix_start;
			}
		}
		return -1;
	}
}
