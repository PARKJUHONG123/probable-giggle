import java.io.*;
import java.util.*;

public class solution {
    public static void main(String[] args) {
    	
    	String location = "C:\\Users\\corallines\\eclipse-workspace\\probable-giggle\\4_Geometric_inside\\Geometric_Problem\\src\\";
    	File input_file = new File(location + "input.txt");
    	
    	try {
            Scanner scanner = new Scanner(input_file);
            int N = scanner.nextInt();
            Point[] P = new Point[N + 1];
            Point A = new Point();

            P[0] = new Point();
            for (int i = 1; i <= N; i++) {
            	P[i] = new Point();
                P[i].set_x(scanner.nextInt());
                P[i].set_y(scanner.nextInt());
            }
            
            /*
            P[0].set_x(P[N].get_x());
            P[0].set_y(P[N].get_y());        
            */
            P[0] = P[N];
            
            A.set_x(scanner.nextInt());
            A.set_y(scanner.nextInt());
            scanner.close();
            
            if (isPointInside(A, P, N)) {
            	System.out.println("내부");            	
            }
            else {
            	System.out.println("외부");            	            	
            }
    	}
    	
    	catch(FileNotFoundException e) {
    		System.out.println("File Error");
    	}
    }
    
    static int Direction(Point A, Point B, Point C) {
    	int dxAB, dxAC, dyAB, dyAC, Dir = 0;
    	dxAB = B.get_x() - A.get_x();
    	dyAB = B.get_y() - A.get_y();
    	dxAC = C.get_x() - A.get_x();
    	dyAC = C.get_y() - A.get_y();
    	
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
    
    static boolean intersection(line AB, line CD) {
    	boolean lineCrossing;
    	if ( ( Direction(AB.get_pt1(), AB.get_pt2(), CD.get_pt1()) * Direction(AB.get_pt1(), AB.get_pt2(), CD.get_pt2() ) <= 0) 
    			&& ( Direction(CD.get_pt1(), CD.get_pt2(), AB.get_pt1()) * Direction(CD.get_pt1(), CD.get_pt2(), AB.get_pt2() ) <= 0) ) {
    		lineCrossing = true;
    	}
    	else {
    		lineCrossing = false;
    	}
    	return lineCrossing;
    }
    
    static boolean isPointInside(Point A, Point P[], int n) {
    	final int INF = 100000;
    	int i, count = 0, LastPoint = 0;
    	boolean PointOnTestLine = false;
    	line TestLine = new line();
    	line PolygonLine = new line();

    	Point T_1 = new Point();
    	Point T_2 = new Point();
    	/*
    	Point P_1 = new Point();
    	Point P_2 = new Point();
    	Point P_T = new Point();
    	*/
    	
    	T_1.set_x(A.get_x());
    	T_1.set_y(A.get_y());
    	T_2.set_x(INF);
    	T_2.set_y(A.get_y());
    	
    	TestLine.set_pt1(T_1);
    	TestLine.set_pt2(T_2);
    	
    	for (i = 1; i <= n; i++) {
    		/*
    		P_1.set_x(P[i].get_x());
    		P_1.set_y(P[i].get_y());
    		P_2.set_x(P[i].get_x());
    		P_2.set_y(P[i].get_y());
    		PolygonLine.set_pt1(P_1);
    		PolygonLine.set_pt2(P_2);
    		*/
    		
    		PolygonLine.set_pt1(P[i]);
    		PolygonLine.set_pt2(P[i]);
    		
    		if (intersection(TestLine, PolygonLine)) {
    			PointOnTestLine = true;
    		}
    		else {
    			/*
    			P_T.set_x(P[LastPoint].get_x());
    			P_T.set_y(P[LastPoint].get_y());
    			PolygonLine.set_pt2(P_T);
				*/
    			
    			PolygonLine.set_pt2(P[LastPoint]);    			
    			LastPoint = i;
    			if (!PointOnTestLine) {
    				if (intersection(PolygonLine, TestLine)) {
    					count++;
    				}
    			}
    			else {
    				if ( Direction(TestLine.get_pt1(), TestLine.get_pt2(), PolygonLine.get_pt1() ) * Direction(TestLine.get_pt1(), TestLine.get_pt2(), PolygonLine.get_pt2()) < 0 ) {
    					count++;
    					PointOnTestLine = false;
    				}
    			}
    		}
    	}
    	return ((count % 2) == 1);
    }
}

class line {
	private Point pt1;
	private Point pt2;
	
	public void set_pt1(Point pt) {
		this.pt1 = pt;
	}
	public void set_pt2(Point pt) {
		this.pt2 = pt;
	}
	public Point get_pt1() {
		return this.pt1;
	}
	public Point get_pt2() {
		return this.pt2;
	}
}

class Point {
	private int x;
	private int y;
	
	public void set_x(int val) {
		this.x = val;
	}
	public void set_y(int val) {
		this.y = val;
	}
	public int get_x() {
		return this.x;
	}
	public int get_y() {
		return this.y;
	}
}