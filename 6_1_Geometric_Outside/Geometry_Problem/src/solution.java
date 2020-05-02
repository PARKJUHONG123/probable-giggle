import java.io.*;
import java.util.*;

public class solution {
    public static void main(String[] args) {
    	
    	String location = "C:\\Users\\corallines\\eclipse-workspace\\probable-giggle\\6_1_Geometric_Outside\\Geometry_Problem\\src\\";
    	File input_file = new File(location + "input.txt");
    	
    	try {
            Scanner scanner = new Scanner(input_file);
            int N = scanner.nextInt();
            Point[] P = new Point[N + 1];
            
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
    	int result = -1;
    	swap(P[0], P[startPoint]);
    	result = PointWrapping(P, N);
    	System.out.println(result);
    	for (int i =0; i < result; i++) {
    		System.out.println(P[i].get_X() + " " + P[i].get_Y());
    	}
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
    
    
    // min_version (every point on the line)
    /* 
    static int PointWrapping (Point P[], int n) {
    	int i, NumVertex;
    	float MinAngle, MaxAngle, Angle;
    	int FirstPoint = 0, NextPoint = -1;
    	    	
    	for (i = 1; i < n; i++) {
    		if (P[i].get_Y() < P[FirstPoint].get_Y()) {
    			FirstPoint = i;
    		}
    	}
    	NumVertex = -1;
		P[n] = P[FirstPoint];
		MaxAngle = (float) 0.0;
		NextPoint = FirstPoint;
		
		do {
			NumVertex++;
			swap(P[NumVertex], P[NextPoint]);
			NextPoint = n;
			MinAngle = MaxAngle;
			MaxAngle = (float) 360.0;
			for (i = NumVertex + 1; i <= n; i++) {
				Angle = ComputeAngle(P[NumVertex], P[i]);
				P[i].set_angle(Angle);
				if (Angle >= MinAngle && Angle < MaxAngle ) {
					if (P[NumVertex].get_X() != P[i].get_X() || P[NumVertex].get_Y() != P[i].get_Y()) {
						MaxAngle = Angle;						
					}
				}
			}
			
			float min_distance = Float.MAX_VALUE;
			float distance = Float.MAX_VALUE;
			for (i = NumVertex + 1; i <= n; i++) {
				if (P[i].get_angle() == MaxAngle) {
					distance = (P[NumVertex].get_X() - P[i].get_X()) * (P[NumVertex].get_X() - P[i].get_X()) 
							+ (P[NumVertex].get_Y() - P[i].get_Y()) * (P[NumVertex].get_Y() - P[i].get_Y());
					if (min_distance > distance && distance > 0) {
						min_distance = distance;
						NextPoint = i;
					}
				}
			}
		} while (NextPoint != n);
		return ++NumVertex;
    }
    */
    

    // max_version (maximum point on the line)
    static int PointWrapping (Point P[], int n) {
    	int i, NumVertex;
    	float MinAngle, MaxAngle, Angle;
    	int FirstPoint = 0, NextPoint = -1;
    	    	
    	for (i = 1; i < n; i++) {
    		if (P[i].get_Y() < P[FirstPoint].get_Y()) {
    			FirstPoint = i;
    		}
    	}
    	NumVertex = -1;
		P[n] = P[FirstPoint];
		MaxAngle = (float) 0.0;
		NextPoint = FirstPoint;
		
		do {
			NumVertex++;
			swap(P[NumVertex], P[NextPoint]);
			NextPoint = n;
			MinAngle = MaxAngle;
			MaxAngle = (float) 360.0;
			for (i = NumVertex + 1; i <= n; i++) {
				Angle = ComputeAngle(P[NumVertex], P[i]);
				P[i].set_angle(Angle);
				if (Angle >= MinAngle && Angle < MaxAngle ) {
					if (P[NumVertex].get_X() != P[i].get_X() || P[NumVertex].get_Y() != P[i].get_Y()) {
						MaxAngle = Angle;						
					}
				}
			}
			
			float max_distance = Float.MIN_VALUE;
			float distance = Float.MIN_VALUE;
			for (i = NumVertex + 1; i <= n; i++) {
				if (P[i].get_angle() == MaxAngle) {
					distance = (P[NumVertex].get_X() - P[i].get_X()) * (P[NumVertex].get_X() - P[i].get_X()) 
							+ (P[NumVertex].get_Y() - P[i].get_Y()) * (P[NumVertex].get_Y() - P[i].get_Y());
					if (max_distance < distance && distance > 0) {
						max_distance = distance;
						NextPoint = i;
					}
				}
			}
		} while (NextPoint != n);
		return ++NumVertex;
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