package mcgillphys19;

/**
 *
 * @author aweso
 */
public class Matrix {
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
    
    public static double dotProduct(double [] v1, double [] v2) {
        double total = 0;
        
        for (int i = 0; i < v1.length; i++)
            total += v1[i] * v2[i];
        
        return total;
    }
    
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
}
