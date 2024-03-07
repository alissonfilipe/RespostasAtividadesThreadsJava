/*Crie um programa em Java que realize a multiplicação de matrizes de dimensão 3x3 utilizando
threads. Cada matriz deverá ser preenchida com valores fornecidos pelo usuário. */

import java.util.Scanner;

public class ThreadsMatrix3x3 {
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando matrizes
        int[][] matrix1 = new int[3][3];
        int[][] matrix2 = new int[3][3];

        // Preenchendo a primeira matriz
        System.out.println("Digite os elementos da primeira matriz (3x3):");
        fillMatrix(scanner, matrix1);

        // Preenchendo a segunda matriz
        System.out.println("Digite os elementos da segunda matriz (3x3):");
        fillMatrix(scanner, matrix2);

        // Criando a matriz resultante
        int[][] resultMatrix = new int[3][3];

        // Criando threads
        Thread[] threads = new Thread[3];

        // Multiplicação de matrizes
        for (int i = 0; i < 3; i++) {
            final int row = i;
            threads[i] = new Thread(() -> multiplyRow(matrix1, matrix2, resultMatrix, row));
            threads[i].start();
        }

        // Aguardando que todas as threads terminem
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Imprimindo a matriz resultante
        System.out.println("Matriz resultante da multiplicação:");
        printMatrix(resultMatrix);
    }

    // Método para preencher uma matriz com valores fornecidos pelo usuário
    private static void fillMatrix(Scanner scanner, int[][] matrix) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
    }

    // Método para multiplicar uma linha da primeira matriz com a segunda matriz
    private static void multiplyRow(int[][] matrix1, int[][] matrix2, int[][] resultMatrix, int row) {
        for (int j = 0; j < 3; j++) {
            int sum = 0;
            for (int k = 0; k < 3; k++) {
                sum += matrix1[row][k] * matrix2[k][j];
            }
            resultMatrix[row][j] = sum;
        }
    }

    // Método para imprimir uma matriz
    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
