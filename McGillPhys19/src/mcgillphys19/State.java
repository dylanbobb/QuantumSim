package mcgillphys19;

import java.util.Arrays;
import java.util.Random;

public class State {

    protected double[] state;
    protected int nbQubits;

    public State(int nbQubits) {
        this.state = new double[(int) Math.pow(2, nbQubits)];
        this.nbQubits = nbQubits;
    }

    public State(double[] state) {
        this.state = state;
        this.nbQubits = (int) (Math.log(state.length) / Math.log(2));
    }

    public double[] getState() {
        return this.state;
    }

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
    
    public void x(int[] selection) { //TODO
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
            System.out.println(Arrays.deepToString(transformations[i]));
            state = Matrix.transform(transformations[i], state);
        }
        
        for (int i = transformations.length - 2; i >= 0; i--) {
            state = Matrix.transform(transformations[i], state);
        }
    }

    public void cNot(int a, int b) {
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

    public final double[][] H = {{1 / Math.sqrt(2), 1 / Math.sqrt(2)}, {1 / Math.sqrt(2), -1 / Math.sqrt(2)}};
    public final double[][] I = {{1, 0}, {0, 1}};
    public final double[][] S = {{1, 0, 0, 0}, {0, 0, 1, 0}, {0, 1, 0, 0}, {0, 0, 0, 1}};
    public final double[][] X = {{0, 1}, {1, 0}};
    //public final double[][] CNOT = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 1}, {0, 0, 1, 0}};
}
