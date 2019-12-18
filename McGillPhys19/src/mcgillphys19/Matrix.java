package mcgillphys19;

/**
 * Matrix math class
 * 
 * @author Drop Table Team
 */
public class Matrix {

    /**
     * Performs a matrix-vector multiplication (linear transformation)
     * 
     * @param A Transformation Matrix
     * @param v Vector
     * @return Returns transformed vector
     */
    public static double[] transform(double [][] A, double [] v) {
        int aColumns = A[0].length;
        int bRows = v.length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Columns " + aColumns + " did not match B:Rows " + bRows + ".");
        }
        
        double[] C = new double[bRows];
        
        for (int i = 0; i < bRows; i++) {
            C[i] = dotProduct(A[i], v);
        }
        
        return C;
    }
    
    /**
     * Performs a dot product of two vectors
     * 
     * @param v1 First vector
     * @param v2 Second vector
     * @return Returns the dot product of both vectors
     */
    public static double dotProduct(double [] v1, double [] v2) {
        double total = 0;
        
        for (int i = 0; i < v1.length; i++)
            total += v1[i] * v2[i];
        
        return total;
    }
    
    /**
     * Performs a matrix-matrix multiplication
     * 
     * @param A First matrix
     * @param B Second matrix
     * @return Returns the resultant matrix
     */
    public static double[][] times(double[][] A, double[][] B) {
        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Columns " + aColumns + " did not match B:Rows " + bRows + ".");
        }

        double[][] C = new double[aRows][bColumns];

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }
    
    /**
     * Performs a tensor product between 2 vectors
     * 
     * @param a first vector
     * @param b second vector
     * @return Returns resultant vector
     */
    public static double [] tensorVectors(double [] a, double [] b) {
        int aLen = a.length;
        int bLen = b.length;
        
        int cLen = aLen * bLen;
        double[] C = new double[cLen];
        
        for (int i = 0; i < cLen; i ++) {
            C[i] = a[i / bLen] * b[i % bLen];
        }
        
        return C;
    }
    
    /**
     * Performs a tensor product between 2 matrices
     * 
     * @param a First matrix
     * @param b Second matrix
     * @return Returns the resultant matrix
     */
    public static double [][] tensorMatrix(double [][] a, double [][] b) {
        int aRows = a.length;
        int bColumns = b[0].length;
        int bRows = b.length;
        int aColumns = a[0].length;
        
        int cColumns = aColumns * bColumns;
        double[][] C = new double[aRows * bRows][cColumns];
        
        for (int i = 0; i < C.length; i ++) {
            for (int j = 0; j < cColumns; j++) {
                C[i][j] = a[i / bRows][j / bColumns] * b[i % bRows][j % bColumns];
            }
        }
        
        return C;
    }
    
    /**
     * Performs a scalar-vector multiplication
     * 
     * @param k scalar value
     * @param v vector
     * @return Returns the resultant vector
     */
    public static double[] scalarVector(double k, double [] v) {
        for (int i = 0; i < v.length; i++) {
            v[i] = k * v[i];
        }
        
        return v;
    }
}
