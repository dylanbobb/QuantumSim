package mcgillphys19;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author aweso
 */
public class ShorDriver {
    public static void main(String[] args) {
        Random rand = new Random();
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a product of 2 primes smaller than 32 : N = ");
        int N = scanner.nextInt();
        
        int q = (int) Math.ceil(2 * Math.log(N) / Math.log(2));
        int qPrime = (int) Math.ceil(q / 2.0);
        State driver = new State((int)(q + qPrime));
        
        int a;
        for (a = 2; a < N; a++) {
            System.out.println("Using a = " + a);
            //a = scanner.nextInt();

            if (N % a == 0) {
                System.out.println("It's the answer!");
                System.out.println("p = " + a);
                System.out.println("q = " + N / a);
                
                return;
            }

            int[] selection = new int[q];
            for (int i = 0; i < q; i++){
                selection[i] = i;
            }
            driver.h(selection);

            driver.qF(a, N, q);

            for (int i = q; i < driver.nbQubits; i++){
                driver.collapse(rand, i);
            }

            double[] states = driver.getState();
            double prevX = 0;
            double total = 0;
            int count = 0;
            for (int i = 0; i < states.length; i++) {
                if (states[i] != 0.0) {
                    if (count == 0) {
                        prevX = i / Math.pow(2, qPrime);
                    } else {
                        total += (i / Math.pow(2, qPrime)) - prevX;
                        prevX = i / Math.pow(2, qPrime);
                    }
                    count++;
                }
            }
            
            int r = (int) (total / (count - 1));
            
            if (r % 2 == 1) {
                System.out.println("This was a bad \"a\"! The resulting r (" + r + ") is odd!");
                System.out.println("Trying again\n");
            }
            else {
                int ar2 = (int)(Math.pow(a, 0.5 * r));
                
                if ((ar2 + 1) % N == 0) {
                    System.out.println("This was a bad \"a\"! The resulting a^r/2 (" + (ar2 + 1) + ") is a multiple of N (" + N +")!");
                    System.out.println("Trying again\n");
                }
                else {
                    int temp = findGCD(ar2 + 1, N);
                    
                    if (N % temp == 0 && temp != 1) {
                        System.out.println("Found the answer!");
                        System.out.println("p = " + temp);
                        System.out.println("q = " + N / temp);

                        return;
                    } else {
                        temp = findGCD(ar2 - 1, N);
                        if (N % temp == 0 && temp != 1) {
                            System.out.println("Found the answer!");
                            System.out.println("p = " + temp);
                            System.out.println("q = " + N / temp);

                            return;
                        } else {
                            System.out.println("This was a bad \"a\"! The resulting p is 1!");
                            System.out.println("Trying again\n");
                        }
                    }
                }
            }
        }
    }
    
    private static int findGCD(int number1, int number2) {
        //base case
        if(number2 == 0){
            return number1;
        }
        return findGCD(number2, number1%number2);
    }
}
