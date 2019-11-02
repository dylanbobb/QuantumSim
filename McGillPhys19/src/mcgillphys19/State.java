package mcgillphys19;

public class State
{
    protected double[] state;
    protected int nbQubits;
    
    public State(int nbQubits)
    {
        this.nbQubits = nbQubits;
        this.state = new double[(int)Math.pow(2, nbQubits)];
    }
    
    public double[] getState()
    {
        return this.state;
    }
}