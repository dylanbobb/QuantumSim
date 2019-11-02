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
        System.out.println(this.nbQubits);
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
        System.out.println(Arrays.deepToString(operationChain));
        for (int i = 1; i < nbQubits; i++) {
            if (currSelectionIndex < selectionLen && selection[currSelectionIndex] == i) {
                operationChain = Matrix.tensorMatrix(operationChain, H);
                currSelectionIndex++;
            } else {
                operationChain = Matrix.tensorMatrix(operationChain, I);
            }
        }
        System.out.println(Arrays.deepToString(operationChain));

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

    public void cNot() {
        double[][] cNot = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 1}, {0, 0, 1, 0}};
        this.state = Matrix.transform(cNot, this.getState());
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

    public final double[][] H = {{1 / Math.sqrt(2), 1 / Math.sqrt(2)}, {1 / Math.sqrt(2), -1 / Math.sqrt(2)}};
    public final double[][] I = {{1, 0}, {0, 1}};
    public final double[][] S = {{1, 0, 0, 0}, {0, 0, 1, 0}, {0, 1, 0, 0}, {0, 0, 0, 1}};
}
