import java.util.*;
import java.io.IOException;
public class Main { 
    public static void main(String[] args) throws IOException { 
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        ArrayList<ArrayList<Integer>> A = new ArrayList<ArrayList<Integer>>(n);
        ArrayList<ArrayList<Integer>> B = new ArrayList<ArrayList<Integer>>(n);
        ArrayList<Integer> temp_al;

        for (int i = 0; i < n; i++){
            temp_al = new ArrayList<Integer>();
            temp_al.add(scanner.nextInt());
            A.add(temp_al);

            temp_al = new ArrayList<Integer>();
            temp_al.add(scanner.nextInt());
            B.add(temp_al);
        }
        scanner.close();
        for (int i = 0; i < n; i++){  
            for (int j = i; j < n; j++){
                if (A.get(i).get(0) > A.get(j).get(0)) {
                    temp_al = new ArrayList<Integer>();
                    temp_al = A.get(i);
                    A.set(i, A.get(j));
                    A.set(j, temp_al);

                    temp_al = new ArrayList<Integer>();
                    temp_al = B.get(i);
                    B.set(i, B.get(j));
                    B.set(j, temp_al);
                }
            }
        }
        
        ArrayList<ArrayList<Integer>> d_arr = new ArrayList<ArrayList<Integer>>(n); 
      
        for (int i =0; i <n; i++) {
            d_arr.add(new ArrayList<Integer>());
        }
      
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                d_arr.get(i).add(B.get(i).get(0));
            }

            else {
                if (d_arr.get(i).size() == 0) {
                    d_arr.get(i).add(B.get(i).get(0));
                }

                for (int j = 0; j < i; j++) {
                    int last_index = d_arr.get(j).size() - 1;
                    if (d_arr.get(j).get(last_index) < B.get(i).get(0)) {
                        if (d_arr.get(i).size() -1 < d_arr.get(j).size()) {
                            temp_al = new ArrayList<Integer>();

                            for (int k = 0; k < d_arr.get(j).size(); k++) {
                                temp_al.add(d_arr.get(j).get(k));
                            }
                            temp_al.add(B.get(i).get(0));
                            d_arr.set(i, temp_al);
                        }
                    }        
                }
            }                
        }
        int max_length = 0;
      
        for (int i = 0; i < n; i++) {
            if (max_length < d_arr.get(i).size()) {
                max_length = d_arr.get(i).size();
            }
        }      
        System.out.println(n - max_length);
    }
}
