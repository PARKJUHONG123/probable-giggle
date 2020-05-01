import java.io.*;
import java.util.*;

public class solution {
    public static void main(String[] args) {
    	String location = "C:\\Users\\corallines\\eclipse-workspace\\probable-giggle\\6_Geometric_Graham\\Geometric_Problem\\src\\";
    	File input_file = new File(location + "input.txt");
    	
    	try {
            Scanner scanner = new Scanner(input_file);
            int N = scanner.nextInt();
            Point[] P = new Point[N + 1];
            
            P[0] = new Point();
            for (int i = 1; i <= N; i++) {
            	P[i] = new Point();
                P[i].set_X(scanner.nextInt());
                P[i].set_Y(scanner.nextInt());
            }
            scanner.close();
            
            int startPoint = get_startPoint(P, N);
            P = FindSimplePolygon(P, startPoint, N);
            
            int number_outerPoint = GrahamScan(P, startPoint, N);
            System.out.println(number_outerPoint);
            
            for (int i = 1; i <= number_outerPoint; i++) {
            	System.out.println(P[i].get_X() + " " + P[i].get_Y());
            }
       	}
    	catch(FileNotFoundException e) {
    		System.out.println("File Error");
    	}
    }

    static int GrahamScan(Point P[], int FirstPoint, int n) {
    	int i, NextPoint;
        P[0] = P[n];
        NextPoint = 3;
        for (i = 4; i <= n; i++) {
        	while(Direction(P[NextPoint - 1], P[NextPoint], P[i]) >= 0) {
        		NextPoint -= 1;
        	}
        	NextPoint += 1;
        	swap(P[NextPoint], P[i]);
        }
    	return NextPoint;
    }
    
    static int Direction(Point A, Point B, Point C) {
    	int dxAB, dxAC, dyAB, dyAC, Dir = 0;
    	dxAB = B.get_X() - A.get_X();
    	dyAB = B.get_Y() - A.get_Y();
    	dxAC = C.get_X() - A.get_X();
    	dyAC = C.get_Y() - A.get_Y();
    	
    	if (dxAB * dyAC < dyAB * dxAC) Dir = 1;
    	if (dxAB * dyAC > dyAB * dxAC) Dir = -1;
    	if (dxAB * dyAC == dyAB * dxAC) {
    		if (dxAB == 0 && dyAB == 0) Dir = 0;
    		else if ((dxAB * dxAC < 0) || (dyAB * dyAC < 0)) {
    			Dir = -1;
    		}
    		else if ((dxAB * dxAB + dyAB * dyAB) >= (dxAC * dxAC + dyAC * dyAC)) {
    			Dir = 0;
    		}
    		else {
    			Dir = 1;
    		}
    	}
    	return Dir;
    }
    

    static int get_startPoint(Point[] P, int N) {
    	int num = 1;
    	Point start = new Point();
    	start.set_X(P[1].get_X());
    	start.set_Y(P[1].get_Y());
    	
    	for (int i = 2; i <= N; i++) {
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
    
    static Point[] FindSimplePolygon(Point[] P, int startPoint, int N) {
    	for (int i = 1; i <= N; i++) {
    		if (i == startPoint) {
    			P[i].set_angle((float)0);
    		}
    		else {
    			P[i].set_angle(ComputeAngle(P[startPoint], P[i]));
    		}
    	}
    	
    	int temp = -1;
    	int[] rank = new int[N + 1];
    	for (int i = 1; i <= N; i++) {
    		rank[i] = i;
    	}
    	
    	for (int i = 1; i <= N; i++) {
    		for (int j = i + 1; j <= N; j++) {
    			if (P[rank[i]].get_angle() > P[rank[j]].get_angle()) {
    				temp = rank[i];
    				rank[i] = rank[j];
    				rank[j] = temp;
    			}
    		}
    	}
    	
		Point[] R = new Point[N + 1];
		R[0] = new Point();
		for (int i = 1; i <= N; i++) {
			R[i] = new Point();
    		R[i].set_X(P[rank[i]].get_X());
    		R[i].set_Y(P[rank[i]].get_Y());
    	}
		return R;
    }    
    
    
    static void swap(Point A, Point B) {
    	Point temp = new Point();
    	temp.set_X(A.get_X());
    	temp.set_Y(A.get_Y());
    	temp.set_angle(A.get_angle());
    	
    	A.set_X(B.get_X());
    	A.set_Y(B.get_Y());
    	A.set_angle(B.get_angle());
    	
    	B.set_X(temp.get_X());
    	B.set_Y(temp.get_Y());
    	B.set_angle(temp.get_angle());
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
