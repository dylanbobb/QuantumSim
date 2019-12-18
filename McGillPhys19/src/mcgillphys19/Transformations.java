package mcgillphys19;

/**
 * DEPRECATED This class is used for basic transformation
 * 
 * @author Drop Table Team
 */
public class Transformations
{

    /**
     * Performs performs an H gate on a state
     * 
     * @param vector input state
     * @return new state
     */
    public State hGate1(double[] vector)
    {
        double[][] hGate = { {1/Math.sqrt(2),1/Math.sqrt(2)}, {1/Math.sqrt(2),-1/Math.sqrt(2)} };
        return new State(Matrix.transform(hGate,vector));
    }
    
    /**
     * Performs a swap between two qubits
     * 
     * @param state input state
     * @return new state
     */
    public State swap(State state)
    {
        double[][] swapGate = { {1,0,0,0}, {0,0,1,0}, {0,1,0,0}, {0,0,0,1} };
        return new State(Matrix.transform(swapGate, state.getState()));
    }
    
    /**
     * Performs a C-NOT gate between 2 qubits
     * 
     * @param state input state
     * @return new state
     */
    public State cNot(State state)
    {
        double[][] cNot = { {1,0,0,0}, {0,1,0,0}, {0,0,0,1}, {0,0,1,0} };
        return new State(Matrix.transform(cNot, state.getState()));
    }
}