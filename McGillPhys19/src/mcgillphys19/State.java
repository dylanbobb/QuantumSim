package mcgillphys19;

public class State
{
    protected double[] state;
    protected int nbQubits;
    protected QBit[] qbits;
    
    public State(int nbQubits)
    {
        this.nbQubits = nbQubits;
        this.state = new double[(int)Math.pow(2, nbQubits)];
//        this.qbits = {new QBit({1,0}), new QBit({1,0})};
    }
    
    public State(QBit[] qbits)
    {
        this.qbits = qbits;
    }
            
    
    public double[] getState()
    {
        return this.state;
    }
}