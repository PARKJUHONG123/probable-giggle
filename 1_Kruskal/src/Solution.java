import java.io.*;
import java.util.*;

public class Solution {
	static int find(int value, int[] P, int M, int N, Edge[] edge) {
		for (int i = 0 ; i < N; i++) {
			if (P[value] == value) {
				return value;
			}
			else {
				value = P[value];
			}
		}
		return -1;
	}
	
	static int union(int[] P, int M, int N, Edge[] edge) {
		int result = 0;
		for (int i=0; i<M; i++) {
			int start = find(edge[i].get_start(), P, M, N, edge);
			int end = find(edge[i].get_end(), P, M, N, edge);
			
			if (start != end) {
				P[end] = start;
				result += edge[i].get_weight();
			}
		}
		return result;
	}
	
	static void sort(int M, Edge[] edge) {
		Edge temp = new Edge();
        for (int i=0; i < M; i++){
        	for (int j =i +1; j < M; j++) {
        		if (edge[i].get_weight() > edge[j].get_weight()) {
        			temp.set_start(edge[j].get_start());
        			temp.set_end(edge[j].get_end());
        			temp.set_weight(edge[j].get_weight());

        			edge[j].set_start(edge[i].get_start());
        			edge[j].set_end(edge[i].get_end());
        			edge[j].set_weight(edge[i].get_weight());
        			
        			edge[i].set_start(temp.get_start());
        			edge[i].set_end(temp.get_end());
        			edge[i].set_weight(temp.get_weight());
        		}
        	}
        }
	}
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        Edge[] edge = new Edge[M];
        int[] P = new int[N + 1];
        
        for (int i=0; i <= N; i++) {
        	P[i] = i;
        }
        
        for (int i =0; i < M; i++) {
        	edge[i] = new Edge();
        }

        for (int i =0; i < M; i++){
            edge[i].set_start(scanner.nextInt());
            edge[i].set_end(scanner.nextInt());     	
            edge[i].set_weight(scanner.nextInt());    
        }
        scanner.close();

        sort(M, edge);
        System.out.println(union(P, M, N, edge));
    }
}

class Edge{
    private int start;
    private int end;
    private int weight;

    public void set_start(int num){
        this.start = num;
    }
    public void set_end(int num){
        this.end = num;
    }
    public void set_weight(int num){
        this.weight = num;
    }
    public int get_start(){
        return this.start;
    }
    public int get_end(){
        return this.end;
    }
    public int get_weight(){
        return this.weight;
    }
}