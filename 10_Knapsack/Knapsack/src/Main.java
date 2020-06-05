import java.util.Scanner;
import java.io.File;
import java.io.IOException; 

public class Main { 
    public static void main(String[] args) throws IOException {
    	String location = "/Users/pjh/probable-giggle/10_Knapsack/Knapsack/src/";
    	File input_file = new File(location + "input.txt");

    	Scanner scanner = new Scanner(input_file);
		int n = scanner.nextInt();
		int w = scanner.nextInt();
		
		int[] w_arr = new int[n + 1];
		int[] v_arr = new int[n + 1];
		int[][] d_arr = new int[n + 1][w + 1];
		//d_arr[i][j] = 첫번째부터 i번째까지 물건을 탐색했고, 탐색해서 담은 물건들의 무게 합을 j라고 할때 가중치의 합

		for (int i = 1; i <= n; i++) {
			w_arr[i] = scanner.nextInt();
			v_arr[i] = scanner.nextInt();
		}
		scanner.close();
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= w; j++) {
				d_arr[i][j] = d_arr[i - 1][j];
				if(j - w_arr[i] >= 0) {
					d_arr[i][j] = max(d_arr[i][j], d_arr[i - 1][j - w_arr[i]] + v_arr[i]);
				}
			}
		}
		System.out.println(d_arr[n][w]);
    }

	private static int max(int i, int j) {
		if (i > j) {
			return i;
		}
		else {
			return j;
		}
	}
}

