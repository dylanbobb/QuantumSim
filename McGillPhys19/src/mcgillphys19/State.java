package mcgillphys19;

public class State {

    protected double[] state;

    public State(int nbQubits) {
        this.state = new double[(int) Math.pow(2, nbQubits)];
//        this.qbits = {new QBit({1,0}), new QBit({1,0})};
    }

    public State(double[] state) {
        this.state = state;
    }

    public double[] getState() {
        return this.state;
    }

//    public void hGate1() {
//        double[][] hGate = {{1 / Math.sqrt(2), 1 / Math.sqrt(2)}, {1 / Math.sqrt(2), -1 / Math.sqrt(2)}};
//        return new State(Matrix.transform(hGate, vector));
//    }

    public void swap() {
        double[][] swapGate = {{1, 0, 0, 0}, {0, 0, 1, 0}, {0, 1, 0, 0}, {0, 0, 0, 1}};
        this.state = Matrix.transform(swapGate, this.getState());
    }

    public void cNot() {
        double[][] cNot = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 1}, {0, 0, 1, 0}};
        this.state = Matrix.transform(cNot, this.getState());
    }
}
