import java.io.*;
import java.util.*;

public class Geometry_Solution {
    public static void main(String[] args) {
    	String location = "C:\\Users\\corallines\\eclipse-workspace\\probable-giggle\\3_Geometric\\Geometric_Problem\\src\\";
    	File input_file = new File(location + "input.txt");
    	
    	try {
            Scanner scanner = new Scanner(input_file);
            int N = scanner.nextInt();
            Point[] P = new Point[N];
            
            for (int i = 0; i < N; i++) {
            	P[i] = new Point();
                P[i].set_X(scanner.nextInt());
                P[i].set_Y(scanner.nextInt());
            }
            scanner.close();
            
            int startPoint = get_startPoint(P, N);
        	getResult(P, startPoint, N);
            
    	}
    	catch(FileNotFoundException e) {
    		System.out.println("File Error");
    	}
    }
    
    static int get_startPoint(Point[] P, int N) {
    	int num = 0;
    	Point start = new Point();
    	start.set_X(P[0].get_X());
    	start.set_Y(P[0].get_Y());
    	
    	for (int i = 1; i < N; i++) {
    		if (start.get_Y() > P[i].get_Y()) {
    			start.set_X(P[i].get_X());
    			start.set_Y(P[i].get_Y());
    			num = i;
    		}
    		else if (start.get_Y() == P[i].get_Y()) {
    			if (start.get_X() > P[i].get_X()) {
        			start.set_X(P[i].get_X());
        			start.set_Y(P[i].get_Y());    	
        			num = i;
    			}
    		}
    	}
    	return num;
    }
    
    
    static float ComputeAngle(Point A, Point B) {
    	int Dx, Dy;
    	float Angle;
    	Dx = B.get_X() - A.get_X();
    	Dy = B.get_Y() - A.get_Y();
    	
    	if ( (Dx >= 0) && (Dy == 0) ) {
    		Angle = 0;
    	}
    	else {
    		Angle = (float)(Math.abs(Dy)) / (float)(Math.abs(Dx) + Math.abs(Dy));
    		if ((Dx < 0) && (Dy >= 0)) {
    			Angle = 2 - Angle;
    		}
    		else if ((Dx <= 0) && (Dy < 0)) {
    			Angle = 2 + Angle;
    		}
    		else if ((Dx > 0) && (Dy <0)) {
    			Angle = 4 - Angle;
    		}
    	}
    	Angle *= 90.0;
    	return Angle;
    }
    
    static void getResult(Point[] P, int startPoint, int N) {
    	for (int i = 0; i < N; i++) {
    		if (i == startPoint) {
    			P[i].set_angle((float)0);
    		}
    		else {
    			P[i].set_angle(ComputeAngle(P[startPoint], P[i]));
    		}
    	}
    	
    	int temp = -1;
    	int[] rank = new int[N];
    	for (int i = 0; i < N; i++) {
    		rank[i] = i;
    	}
    	
    	for (int i = 0; i < N; i++) {
    		for (int j = i + 1; j < N; j++) {
    			if (P[rank[i]].get_angle() > P[rank[j]].get_angle()) {
    				temp = rank[i];
    				rank[i] = rank[j];
    				rank[j] = temp;
    			}
    		}
    	}
    	
    	for (int i =0; i < N; i++) {
    		System.out.println(P[rank[i]].get_X() + " " + P[rank[i]].get_Y());
    	}
		System.out.println(P[rank[0]].get_X() + " " + P[rank[0]].get_Y());
    }
}

class Point {
	private int x;
	private int y;
	private float angle;
	
	public void set_X(int x) {
		this.x = x;
	}

	public void set_Y(int y) {
		this.y = y;
	}
	
	public void set_angle(float num) {
		this.angle = num;
	}
	
	public int get_X() {
		return this.x;
	}

	public int get_Y() {
		return this.y;
	}
	
	public float get_angle() {
		return this.angle;
	}
}
