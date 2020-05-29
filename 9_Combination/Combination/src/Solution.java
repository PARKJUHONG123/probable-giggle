import java.util.*;

public class Solution {
	public static void main(String[] args) {
		System.out.print("n k 입력 : ");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		int i = 0;
		
		scanner.close();
		int[] d_arr = new int[k];
		
		for (i = 0; i < k; i++) {
			d_arr[i] = 0;
		}
		for (i = 0; i < k; i++) {
			if (i == 0) {
				d_arr[i] = n;
			}
			else {
				d_arr[i] = d_arr[i - 1] * (n - i) / (i + 1);
			}
		}
		System.out.println(d_arr[k - 1]);
	}
}
