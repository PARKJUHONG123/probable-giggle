import java.util.Scanner;
import java.io.File;
import java.io.IOException; 

public class Main {
	static int max(int A, int B) {
		if (A > B) {
			return A;
		}
		else {
			return B;
		}
	}

	static int min(int A, int B) {
		if (A < B) {
			return A;
		}
		else {
			return B;
		}
	}

    public static void main(String[] args) throws IOException {
    	String location = "/Users/pjh/probable-giggle/11_App/Application/src/";
    	File input_file = new File(location + "input.txt");
 
    	Scanner scan = new Scanner(input_file);
    	//Scanner scan = new Scanner(System.in);
    	int N = scan.nextInt();
    	int M = scan.nextInt();
    	
    	int[] m_arr = new int[N + 1];
    	int[] c_arr = new int[N + 1];
		
    	for (int i = 1; i <= N; i++) {
    		m_arr[i] = scan.nextInt();
    	}
    	int total_c = 0;
    	for (int i = 1; i <= N; i++) {
    		c_arr[i] = scan.nextInt();
    		total_c += c_arr[i];
    	}
    	    	
    	scan.close();
    	
    	int[][] d_arr = new int[N + 1][total_c + 1];
    	
    	for (int i = 1; i <= N; i++) {
    		for (int j = 0; j <= total_c; j++) {
    			if (j >= c_arr[i]) {
    				d_arr[i][j] = max(d_arr[i - 1][j], d_arr[i - 1][j - c_arr[i]] + m_arr[i]); 
    			}
    			else {
    				d_arr[i][j] = d_arr[i - 1][j];
    			}
    		}
    	}
    	
    	int min_j = total_c;
    	for (int i = 1; i <= N; i++) {
    		for (int j = 0; j <= total_c; j++) {
    			if (d_arr[i][j] >= M) {
    				min_j = min(min_j, j);
    			}
    		}
    	}
    	System.out.println(min_j);
	}
}
