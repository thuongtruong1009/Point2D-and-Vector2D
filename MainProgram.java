package dsa2020_thuongtruong1009;

public class MainProgram {
    public static void main(String[] args) {
        task3_1();
        //task3_2();
        //task3_3();
        //task3_4();
    }
    
    /*
    Method: println_pointList
    Objective: print items in the list on screen
    
    Space complexity: O(n)
    Reasons:
        + O(n): to store array of n elements
        + constant: for temporay variables, e.g. N, and s
        + Total: O(n) + c  = O(n)
    
    Comptational Complexity: O(n)
    Reasons:
        + O(n): use for-loop of n iterations
        + constant: for other operations, e.g., assignment, printing.
    */
    public static void println_pointList(Point2D[] list){
        int N = list.length;
        
        System.out.println("List of points:");
        String s = String.format("%3s | %18s", 
                    "IDX",
                    "Point");
        System.out.println(s);
        System.out.println(new String(new char[s.length()]).replace('\0', '-'));
        for(int idx=0; idx < N; idx++){
            s = String.format("%3d | %18s", 
                    idx,
                    list[idx]);
            System.out.println(s);
        }
    }
    /*
    Method: task3_1
    Objective: generate list of points and print the list
    
    Space complexity: O(n)
    Reasons:
        + O(n): to store an array of n elements (in list)
        + constant: other variables
    
    Computational Complexity: O(n)
    Reasons:
        + O(n): for calling "generate", see generate for detail.
                It uses a for-loop of n iterations
        + O(n): for calling "println_pointList", it uses a for-loop of n iterations
        + Total: O(n) + O(n) = O(n)
    */
    public static void task3_1() {
        int N = 10;
        double min = -10, max = 10;
        Point2D[] list = Point2D.generate(N, min, max);
        println_pointList(list);
    }
    
    /*
    Method: task3_2:
    Objective:
        + Generate two lists of points, of the same length
        + Compute distance between pairs
        + Print the result
    
    Space complexity: O(n)
    Reasons:
        + O(n): to store an array of n elements in listA
        + O(n): to store an array of n elements in listB
        + O(n): to store an array of n elements, each is the distance of a pair.
        + c: for other variables
        + Total: O(n) + O(n) + O(n) + c = O(n)
    
    Computational Complexity: O(n)
    Reasons:
        + O(n): to generate listA
        + O(n): to generate listB
        + O(n): to compute the distances of n pairs, use a for loop of n iterations
        + c: other operations
        + Total: O(n) + O(n) + O(n) + c = O(n)
    */
    public static void task3_2() {
        int N = 10;
        double min = -10, max = 10;
        Point2D[] listA = Point2D.generate(N, min, max);
        Point2D[] listB = Point2D.generate(N, min, max);
        double[] distance = new double[N];
        for(int idx =0; idx < N; idx++){
            Point2D p1 = listA[idx];
            Point2D p2 = listB[idx];
            distance[idx] = Point2D.distanceAB(p1, p2);
        }
        //Printing:
        String s = String.format("%3s | %18s, %18s | %6s", 
                    "IDX",
                    "p1: Point in listA", "p2: Point in listB", 
                    "Distance(p1, p2)");
        System.out.println(s);
        System.out.println(new String(new char[s.length()]).replace('\0', '-'));
        for(int idx=0; idx < N; idx++){
            Point2D p1 = listA[idx];
            Point2D p2 = listB[idx];
            s = String.format("%3d | %18s, %18s | %6.2f", 
                    idx,
                    p1, p2, 
                    distance[idx]);
            System.out.println(s);
        }
    }
    
    /*
    Method: getDistance
    Objective: Compute the distance between pairs of points in a list (passed)
    
    Space complexity: O(n^2)
    Reasons:
        + O(n): to store list of n elements
        + O(n^2): to store distance of pairs, n^2 pairs for list of n elements
        + constant: other variables
        + Total: O(n) + O(n^2) + c = O(n^2)
    
    Computational Complexity: O(n^2)
    Reasons:
        + O(n^2): for two nested loops, each has n iterations
        + constant: other opertions
        + Total: O(n^2) + c = O(n^2)
    */
    public static double[][] getDistance(Point2D[] list) {
        int N = list.length;
        double[][] distance = new double[N][N];
        for(int r=0; r < N; r++){
            for(int c=0; c < N; c++){
               if(r > c)
                   distance[r][c] = distance[c][r];
               else if (r == c)
                   distance[r][c] = 0;
               else{
                   Point2D p1 = list[r];
                   Point2D p2 = list[c];
                   distance[r][c] = Point2D.distanceAB(p1, p2);
               }
            }
        }
        return distance;
    }
    
    /*
    Method: find_min
    Objective: Find the minimun distance, distances stored in a squared matrix of size nxn
    
    Space complexity: O(n^2)
    Reasons:
        + O(n^2): to store distance of pairs, n^2 pairs for list of n elements
        + constant: other variables, including an array of 3 elements
        + Total: O(n^2) + c = O(n^2)
    
    Computational Complexity: O(n^2)
    Reasons:
        + O(n^2): for two nested loops
            >> outer loop: n iterations
            >> inner loop: num of iterations decreased from (n-1) to 0
            So: 
            outer loop, index = 0: has (n-1)  iterations for inner loop
            outer loop, index = 1: has (n-2)  iterations for inner loop
            outer loop, index = 2: has (n-3)  iterations for inner loop
            outer loop, index = n-1: has 0  iterations for inner loop
            => total iterations: (n-1) + (n-2) + (n-3) + .. + 1 = (n-1)(n)/2 => O(n^2)
        + constant: other opertions
        + Total: O(n^2) + c = O(n^2)
    */
    public static double[] find_min(double[][] distance) {
        int N = distance.length;
        int rMin = 0;
        int cMin = 1;
        double distMin = distance[rMin][cMin];
        for(int r=0; r < N; r++){
            for(int c=r+1; c < N; c++){ //use upper-triangle matrix
                double distRC = distance[r][c];
                if(distRC < distMin){
                    rMin = r; cMin = c;
                    distMin = distRC;
                }
            }
        }
        //return a compound data
        double[] retData = new double[3];
        retData[0] = rMin;
        retData[1] = cMin;
        retData[2] = distMin;
        return retData;
    }
    
    /*
    Method: find_max
    Objective: Find the maximum distance, distances stored in a squared matrix of size nxn
    
    Space complexity: O(n^2)
    Reasons:
        + O(n^2): to store distance of pairs, n^2 pairs for list of n elements
        + constant: other variables, including an array of 3 elements
        + Total: O(n^2) + c = O(n^2)
    
    Computational Complexity: O(n^2)
    Reasons:
        + O(n^2): for two nested loops
            >> outer loop: n iterations
            >> inner loop: num of iterations decreased from (n-1) to 0
            So: 
            outer loop, index = 0: has (n-1)  iterations for inner loop
            outer loop, index = 1: has (n-2)  iterations for inner loop
            outer loop, index = 2: has (n-3)  iterations for inner loop
            outer loop, index = n-1: has 0  iterations for inner loop
            => total iterations: (n-1) + (n-2) + (n-3) + .. + 1 = (n-1)(n)/2 => O(n^2)
        + constant: other opertions
        + Total: O(n^2) + c = O(n^2)
    */
    public static double[] find_max(double[][] distance) {
        int N = distance.length;
        int rMax = 0;
        int cMax = 0;
        double distMax = distance[rMax][cMax];
        for(int r=0; r < N; r++){
            for(int c=r+1; c < N; c++){ //use upper-triangle matrix
                double distRC = distance[r][c];
                if(distRC > distMax){
                    rMax = r; cMax = c;
                    distMax = distRC;
                }
            }
        }
        //return a compound data
        double[] retData = new double[3];
        retData[0] = rMax;
        retData[1] = cMax;
        retData[2] = distMax;
        return retData;
    }
     
    /*
    Method: task3_3
    Objective:
        + Generate a list n elements
        + Find pairs of points that are nearest and farthest in the list
    
    Space complexity: O(n^2)
    Reasons:
        + O(n) : to store the list of n elements
        + O(n^2): to store the distance for n^2 pairs
        + c: other variables
        + Total: O(n) + O(n^2) + c = O(n^2)
    
    Computational Complexity: O(n^2)
    Reasons:
        + O(n): to generate a list of n elements. It uses a for loop of n iterations
        + O(n^2): to compute the distance for n^2 pairs, see method: getDistance
        + O(n^2): for calling find_min, see find_min
        + O(n^2): for calling find_max, see find_max
        + O(n): for calling println_pointList, see println_pointList
        + O(n^2): for //print distance
        + c: for other operations
        + Total: O(n^2)
        
    */
    public static void task3_3() {
        int N = 10;
        double min = -10, max = 10;
        Point2D[] list = Point2D.generate(N, min, max);
        double[][] distance = getDistance(list);
        
        double[] minInfo = find_min(distance);
        int aMin = (int)minInfo[0], bMin = (int)minInfo[1];
        double distMin = minInfo[2];
        
        double[] maxInfo = find_max(distance);
        int aMax = (int)maxInfo[0], bMax = (int)maxInfo[1];
        double distMax = maxInfo[2];
        
        //printing list of points
        println_pointList(list);
        
        //print distance
        System.out.println("Distance between pairs of points:");
        String s = "";
        for(int r=0; r < N; r++){
            for(int c=0; c<N; c++){
                s += String.format("%6.2f", distance[r][c]);
            }
            s += "\n";
        }
        System.out.println(s);
        System.out.println("");
        
        s = "Nearest Pair: \n";
        s += String.format("\tBetween list[%d]=%s with list[%d]=%s\n", aMin, list[aMin], bMin, list[bMin]);
        s += String.format("\tDistance: %6.2f\n", distMin);
        System.out.println(s);
        
        s = "Farthest Pair: \n";
        s += String.format("\tBetween list[%d]=%s with list[%d]=%s\n", aMax, list[aMax], bMax, list[bMax]);
        s += String.format("\tDistance: %6.2f\n", distMax);
        System.out.println(s);
        
    }
    
    
     /*
    Method: task3_4
    Objective:
        + Generate a list n elements
        + Find the center and the neast and the farthest point to the center
    
    Space complexity: O(n)
    Reasons:
        + O(n) : to store the list of n elements
        + c: other variables
        + Total: O(n) + c = O(n^2)
    
    Computational Complexity: O(n^2)
    Reasons:
        + O(n): to generate a list of n elements. It uses a for loop of n iterations
        + O(n): for calling println_pointList, see println_pointList
        + O(n): for //Compute the center
        + O(n): for //Find nearest
        + O(n): for //Find farthest
        + c: for other operations
        + Total: O(n)
        
    */
    public static void task3_4() {
        int N = 10;
        double min = -10, max = 10;
    
        Point2D[] list = Point2D.generate(N, min, max);
        //printing list of points
        println_pointList(list);
        
        //Compute the center
        double cx=0, cy=0;
        for(int idx=0; idx < N; idx++){
            cx += list[idx].getX();
            cy += list[idx].getY();
        }
        cx = cx/N; cy = cy/N;
        Point2D centerPoint = new Point2D(cx, cy);
        
        //Find nearest
        int nIdx = 0;
        double nDistance = Point2D.distanceAB(centerPoint, list[nIdx]);
        for(int idx=0; idx < N; idx++){
            double dist = Point2D.distanceAB(centerPoint, list[idx]);
            if(dist < nDistance){
                nIdx = idx;
                nDistance = dist;
            }
        }
        String s = "Nearest Point: \n";
        s += String.format("\tCenter: %s; Nearest: list[%d] = %s\n", 
                centerPoint,
                nIdx, list[nIdx]
                );
        s += String.format("\tDistance: %6.2f\n", nDistance);
        System.out.println(s);
        
        //Find farthest:
        int fIdx = 0;
        double fDistance = Point2D.distanceAB(centerPoint, list[fIdx]);
        for(int idx=0; idx < N; idx++){
            double dist = Point2D.distanceAB(centerPoint, list[idx]);
            if(dist > fDistance){
                fIdx = idx;
                fDistance = dist;
            }
        }      
        s = "Farthest Point: \n";
        s += String.format("\tCenter: %s; Farthest: list[%d] = %s\n", 
                centerPoint,
                fIdx, list[fIdx]
                );
        s += String.format("\tDistance: %6.2f\n", fDistance);
        System.out.println(s);
    }
    
}
