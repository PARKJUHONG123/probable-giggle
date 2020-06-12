import java.util.Scanner;
import java.io.File;
import java.io.IOException; 

public class Main_Recursive {
	static int min(int A, int B) {
		if (A < B) {
			return A;
		}
		else {
			return B;
		}
	}
	
	static final int INF = 100000000;
	
	// f(i, r) = 1 ~ i 번째 앱까지 고려했을 때 메모리 r이상을 확보하기 위한 최소 비용
	// 1. i를 선택한 경우, r 에서 m_arr[i] 만큼을 빼고, C에 c_arr[i] 만큼을 더한다. r 이 0보다 작아지면 더 이상 진행하지 않고 cost 를 return 한다.
	// 2. i를 선택하지 않은 경우, 그대로 C와 r 의 값을 변경하지 않고 진행한다.	
	static int f(int i, int r, int C, int[] m_arr, int[] c_arr) {
	    if (i < 0) return INF;
	    if (0 >= r) return C;
	    return min(f(i - 1, r, C, m_arr, c_arr), f(i - 1, r - m_arr[i], C + c_arr[i], m_arr, c_arr));
	}

    public static void main(String[] args) throws IOException {
    	String location = "/Users/pjh/probable-giggle/11_App/Application_Recursive/src/";
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

    	for (int i = 1; i <= N; i++) {
    		c_arr[i] = scan.nextInt();
    	}
    	    	    	
    	scan.close();
    	System.out.println(f(N, M, 0, m_arr, c_arr));
	}
}
