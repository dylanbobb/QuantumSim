package mcgillphys19;

public class Qubit
{
    protected double[] state;
    
    public Qubit(double[] state)
    {
        this.state = state;
    }
    
    public double[] collapse()
    {
        return this.state;
    }
    
    public boolean isSuperPosition()
    {
        return false;
    }
    
    public double[] getState()
    {
        return this.state;
    }
    
    public void setState(double[] state)
    {
        this.state = state;
    }
}