package mcgillphys19;

import java.util.Arrays;
import java.util.Random;

/**
 * Master class for everything about a system of qubits
 * 
 * @author Drop Table Team
 */
public class State {

    /**
     * Current state
     */
    protected double[] state;

    /**
     * Number of qubits in the state
     */
    protected int nbQubits;

    /**
     * Creates empty state based on qubit count
     * 
     * @param nbQubits Number of qubits in the system
     */
    public State(int nbQubits) {
        this.state = new double[(int) Math.pow(2, nbQubits)];
        this.state[0] = 1;
        this.nbQubits = nbQubits;
    }

    /**
     * Creates a state from a pre-made list
     * 
     * @param state pre-made state
     */
    public State(double[] state) {
        this.state = state;
        this.nbQubits = (int) (Math.log(state.length) / Math.log(2));
    }

    /**
     * Getter for state
     * 
     * @return Returns the current state
     */
    public double[] getState() {
        return this.state;
    }

    /**
     * Performs an H gate on a selection of qubits
     * 
     * @param selection Array of selected qubits to perform the gate
     */
    public void h(int[] selection) {
        int currSelectionIndex = 0;
        int selectionLen = selection.length;
        double[][] operationChain = null;
        
        if (selection[currSelectionIndex] == 0) {
            operationChain = H;
            currSelectionIndex++;
        } else {
            operationChain = I;
        }
        
        for (int i = 1; i < nbQubits; i++) {
            if (currSelectionIndex < selectionLen && selection[currSelectionIndex] == i) {
                operationChain = Matrix.tensorMatrix(operationChain, H);
                currSelectionIndex++;
            } else {
                operationChain = Matrix.tensorMatrix(operationChain, I);
            }
        }

        this.state = Matrix.transform(operationChain, this.getState());
    }
    
    /**
     * Performs the X gate on a selection of qubits
     * 
     * @param selection Array of selected qubits to perform the gate
     */
    public void x(int[] selection) {
        int currSelectionIndex = 0;
        int selectionLen = selection.length;
        double[][] operationChain = null;
        
        if (selection[currSelectionIndex] == 0) {
            operationChain = X;
            currSelectionIndex++;
        } else {
            operationChain = I;
        }
        
        for (int i = 1; i < nbQubits; i++) {
            if (currSelectionIndex < selectionLen && selection[currSelectionIndex] == i) {
                operationChain = Matrix.tensorMatrix(operationChain, X);
                currSelectionIndex++;
            } else {
                operationChain = Matrix.tensorMatrix(operationChain, I);
            }
        }

        this.state = Matrix.transform(operationChain, this.getState());
    }

    /**
     * Performs a swap between 2 qubits
     * 
     * @param a first qubit to swap
     * @param b second qubit to swap
     */
    public void swap(int a, int b) {
        if (a > nbQubits || b > nbQubits)
            return;
        
        int source = a < b ? a : b;
        int dest = a < b ? b : a;

        double[][][] transformations = new double[dest - source][1][1];
        for (int i = 0; i < transformations.length; i++) {
            
            transformations[i][0][0] = 1.0;
            for (int j = 0; j < i + source; j++) {
                transformations[i] = Matrix.tensorMatrix(transformations[i], I);
            }
            transformations[i] = Matrix.tensorMatrix(transformations[i], S);
            for (int j = i+source+1; j < nbQubits - 1; j++) {
                transformations[i] = Matrix.tensorMatrix(transformations[i], I);
            }
            state = Matrix.transform(transformations[i], state);
        }
        
        for (int i = transformations.length - 2; i >= 0; i--) {
            state = Matrix.transform(transformations[i], state);
        }
    }

    /**
     * Performs a C-NOT between 2 qubits
     * 
     * @param a conditional bit
     * @param b target bit
     */
    public void cNot(int a, int b) {
        /*
        b depends on a
        */
        double [][] cNotMatrix = new double[state.length][state.length];
        
        int idxA = nbQubits - 1 - a;
        int idxB = nbQubits - 1 - b;
        int powA = ((int) Math.pow(2, idxA));
        
        for (int i = 0; i < cNotMatrix.length; i++) {
            if ((i / powA) % 2 == 1) {
                cNotMatrix[i][i ^ ((int) Math.pow(2, idxB))] = 1;
            }
            else {
                cNotMatrix[i][i] = 1;
            }
        }
        
        this.state = Matrix.transform(cNotMatrix, this.getState());
    }

    /**
     * Collapses the entire system into a final state
     * 
     * @param rand a Random object
     */
    public void collapse(Random rand) {
        double result = rand.nextDouble();

        double[] newState = new double[state.length];

        double currState = 0;

        for (int i = 0; i < state.length; i++) {
            currState += state[i] * state[i];

            if (result <= currState) {
                newState[i] = 1;
                state = newState;
                return;
            }
        }
    }
    
    /**
     * Performs a black-box function on qubits 0 and 1
     * 
     * @param id Id of the black-box function
     */
    public void U(int id) {
        /*
        ID
        0: Constant 0
        1: Constant 1
        2: Identity
        3: NOT gate
        */
        
        if (id == 0) {
            return;
        } else if (id == 1) {
            int[] selection = {0};
            x(selection);
        } else if (id == 2) {
            cNot(1, 0);
        } else {
            cNot(1, 0);
            int[] selection = {0};
            x(selection);
        }
    }
    
    /**
     * Collapses a specific qubit in the system while leaving the others in superposition
     * 
     * @param rand a Random object
     * @param index qubit to collapse
     */
    public void collapse(Random rand, int index) {
        double result = rand.nextDouble();
        double totalProb = 0;
        int powA = (int) Math.pow(2, nbQubits - 1 - index);
        
        for (int i = 0; i < state.length; i++) {
            if (((i / powA) % 2 == 1)) {
                totalProb += state[i] * state[i];
            }
        }
        
        double [][] transformation = null;
        if (result <= totalProb) {
            transformation = M1;
        } else {
            transformation = M0;
            totalProb = 1 - totalProb;
        }
        
        double[][] chain = new double[1][1];
        chain[0][0] = 1.0;
        for (int i = 0; i < index; i++) {
            chain = Matrix.tensorMatrix(chain, I);
        }
        chain = Matrix.tensorMatrix(chain, transformation);
        for (int i = index + 1; i < nbQubits; i++) {
            chain = Matrix.tensorMatrix(chain, I);
        }
        
        state = Matrix.transform(chain, state);
        double k = 1.0 / Math.pow(totalProb, 0.5);
        state = Matrix.scalarVector(k, state);
    }
    
    /**
     * First instance of the function F (used in shor's)
     * 
     * @param a base number
     * @param N Modulus term
     * @return Returns 1
     */
    public int f0(int a, int N) {
        return 1;
    }
    
    /**
     * Next instance of the function F (used in shor's)
     * 
     * @param a base number
     * @param N Modulus term
     * @param prev previous instance
     * @return Returns current instance of the function
     */
    public int nextF(int a, int N, int prev) {
        int returnVal = (prev * a) % N;
        return returnVal;
    }
    
    /**
     * Creates and performs the F function to the system (used in shor's)
     * @param a base number
     * @param N Modulus term
     * @param q qubit number
     */
    public void qF(int a, int N, int q) {
        double[][] transformation = new double[state.length][state.length];
        int Q = (int) Math.pow(2, q);
        int offset = (int) Math.pow(2, q / 2.0);
        
        /*for (int i = 0; i < state.length; i++) {
            transformation[i][i] = 1;
        }*/
        
        int i = 0;
        int prevF = f0(a, N);
        int sourceIdx = i * offset;
        int destIdx = sourceIdx + prevF;
        double temp = state[sourceIdx];
        state[sourceIdx] = state[destIdx];
        state[destIdx] = temp;
        //transformation[sourceIdx][destIdx] = 1;
        //transformation[destIdx][sourceIdx] = 1;
        for (i = 1; i < Q; i++) {
            prevF = nextF(a, N, prevF);
            sourceIdx = i * offset;
            destIdx = sourceIdx + prevF;
            temp = state[sourceIdx];
            state[sourceIdx] = state[destIdx];
            state[destIdx] = temp;
            //transformation[sourceIdx][destIdx] = 1;
            //transformation[destIdx][sourceIdx] = 1;
        }
        //state = Matrix.transform(transformation, state);
    }

    /**
     * Matrix for 1 qubit H gate
     */
    public final double[][] H = {{1 / Math.sqrt(2), 1 / Math.sqrt(2)}, {1 / Math.sqrt(2), -1 / Math.sqrt(2)}};

    /**
     * Matrix for 1 qubit I gate
     */
    public final double[][] I = {{1, 0}, {0, 1}};

    /**
     * Matrix for 2 qubit S gate
     */
    public final double[][] S = {{1, 0, 0, 0}, {0, 0, 1, 0}, {0, 1, 0, 0}, {0, 0, 0, 1}};

    /**
     * Matrix for 1 qubit X gate
     */
    public final double[][] X = {{0, 1}, {1, 0}};
    
    /**
     * Matrix for 1 qubit M0 gate
     */
    public final double[][] M0 = {{1, 0}, {0, 0}};

    /**
     * Matrix for 1 qubit M1 gate
     */
    public final double[][] M1 = {{0, 0}, {0, 1}};
    //public final double[][] CNOT = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 1}, {0, 0, 1, 0}};

    @Override
    public String toString() {
        String printStr = "";
        for (int i = 0; i < state.length; i++) {
            printStr += "\n" + i + " : " + state[i];
        }
        
        return printStr;
    }
}
