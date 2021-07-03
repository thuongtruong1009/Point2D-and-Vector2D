package dsa2020_thuongtruong1009;

public class Vector2D {
    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }
    private double x;
    private double y;
    
    public Vector2D(){
        this.x = 1;
        this.y = 0;
    }
    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Vector2D(Vector2D oldVector){
        this.x = oldVector.x;
        this.y = oldVector.y;
    }
    public Vector2D clone(){
        return new Vector2D(this.x, this.y);
    }
    public String toString(){
        return String.format("V(%6.2f, %6.2f)", this.x, this.y);
    }
    public Vector2D a2b(Point2D a, Point2D b){
        double x = b.getX() - a.getX();
        double y = b.getY() - a.getY();
        return new Vector2D(x,y);
    }
    public double length(){
        return Math.sqrt(x*x + y*y);
    }
    public Vector2D normalize(){
        double length = this.length();
        this.x = x/length;
        this.y = y/length;
        return this;
    }
    public static double dot(Vector2D a, Vector2D b){
        return a.getX()*b.getX() + a.getY()*b.getY();
    }
    public double dotWith(Vector2D vec){
        return this.getX()*vec.getX() + this.getY()*vec.getY();
    }
    public static double angle(Vector2D a, Vector2D b){
        double lenA = a.length();
        double lenB = b.length();
        final double eps = 1e-7;
        if ((Math.abs(lenA) < eps) || (Math.abs(lenA) < eps) )
            return 0.0;
        double AdotB = Vector2D.dot(a,b);
        double cosAB = AdotB/(lenA*lenB);

        double _angle = Math.acos(cosAB)*(180.0/Math.PI);
        return _angle;
    }
    public Vector2D getPerp(){
        return new Vector2D(-this.getY(), this.getX());
    }
}
