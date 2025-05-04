public class PointPair implements Comparable<PointPair>
{
    Point p1;
    Point p2;
    double distance;
        
    public PointPair(Point p1, Point p2)
    {
        this.p1 = p1; this.p2 = p2; 
        this.distance = p1.distance(p2);
    }
        
    public PointPair(Point p1)
    {
        this(p1, null); 
    }
    
    public String toString()
    {
        return "Points: " + p1 + ", " + p2 +
        ". Distance: " + distance;
    }
    
    @Override
    public boolean equals(Object o)
    {
       if (o == this)
            return true;
            
       if (!(o instanceof PointPair))
            return false;
            
       PointPair pp = (PointPair) o;
       
       // (p1, p2) represents the sampe pair as (p2, p1)
       boolean cmp1 = p1.equals(pp.p1) && p2.equals(pp.p2);
       boolean cmp2 = p1.equals(pp.p2) && p2.equals(pp.p1);
       
       return cmp1 || cmp2;
    }
        
    public void updateDistance()
    {
        distance = p1.distance(p2);
    }
               
    public int compareTo(PointPair pp)
    {
        return ((Double)distance).compareTo(pp.distance);
    }
}