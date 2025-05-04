public class Point
{
    double x;
    double y;
    
    public Point(double x, double y)
    {
        this.x = x; 
        this.y = y;
    }
    
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o == this)
            return true;
        
        if (!(o instanceof Point))
            return false;
            
        Point p = (Point) o;
        return this.x == p.x && this.y == p.y;
    }
    
    @Override
    public int hashCode()
    {
        return ((Double)x).hashCode() + 
        ((Double)y).hashCode();
    }
    
    public double distance(Point p)
    {
        if (p == null)
            return 0;
            
        double dx = x - p.x;
        double dy = y - p.y;
        
        return Math.sqrt(dx*dx + dy*dy);
    }
    
}
