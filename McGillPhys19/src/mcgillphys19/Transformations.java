package mcgillphys19;

public class Transformations
{
    public State hGate1(double[] vector)
    {
        double[][] hGate = { {1/Math.sqrt(2),1/Math.sqrt(2)}, {1/Math.sqrt(2),-1/Math.sqrt(2)} };
        return new State(Matrix.transform(hGate,vector));
    }
    
    public State swap(State state)
    {
        double[][] swapGate = { {1,0,0,0}, {0,0,1,0}, {0,1,0,0}, {0,0,0,1} };
        return new State(Matrix.transform(swapGate, state.getState()));
    }
    
    public State cNot(State state)
    {
        double[][] cNot = { {1,0,0,0}, {0,1,0,0}, {0,0,0,1}, {0,0,1,0} };
        return new State(Matrix.transform(cNot, state.getState()));
    }
}