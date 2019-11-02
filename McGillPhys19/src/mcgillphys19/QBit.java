package mcgillphys19;

public class QBit
{
    protected double[] state;
    
    public QBit(double [] state)
    {
        this.state = state;
    }
    
    public double[] getState()
    {
        return this.state;
    }
}