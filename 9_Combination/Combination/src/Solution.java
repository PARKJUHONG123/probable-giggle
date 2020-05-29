import java.util.*;
import java.math.BigInteger;

public class Solution {
	public static void main(String[] args) {
		System.out.print("n k 입력 : ");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		int i = 0;
		
		scanner.close();
		BigInteger[] d_arr = new BigInteger[k];
		
		for (i = 0; i < k; i++) {
			d_arr[i] = new BigInteger(String.valueOf(0));
		}
		for (i = 0; i < k; i++) {
			if (i == 0) {
				d_arr[i] = new BigInteger(String.valueOf(n));
			}
			else {
				d_arr[i] = d_arr[i - 1].multiply(new BigInteger(String.valueOf(n - i))).divide(new BigInteger(String.valueOf(i + 1)));
			}
		}
		System.out.println(d_arr[k - 1]);
	}
}
