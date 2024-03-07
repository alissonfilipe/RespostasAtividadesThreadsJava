/*Crie um programa em Java que realize a transposição de matrizes utilizando threads. O usuário
deverá fornecer a dimensão da matriz e os valores a serem preenchidos. */

import java.util.Scanner;

public class TransposicaoMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Solicita ao usuário as dimensões da matriz
        System.out.println("Digite o número de linhas da matriz:");
        int linhas = scanner.nextInt();
        System.out.println("Digite o número de colunas da matriz:");
        int colunas = scanner.nextInt();
        
        // Cria a matriz com os valores fornecidos pelo usuário
        int[][] matriz = new int[linhas][colunas];
        System.out.println("Digite os elementos da matriz:");
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = scanner.nextInt();
            }
        }
        
        // Mostra a matriz original
        System.out.println("Matriz original:");
        mostrarMatriz(matriz);
        
        // Cria e inicia as threads para transpor a matriz
        Thread[] threads = new Thread[colunas];
        for (int i = 0; i < colunas; i++) {
            final int colunaAtual = i;
            threads[i] = new Thread(() -> transporColuna(matriz, colunaAtual));
            threads[i].start();
        }
        
        // Aguarda todas as threads terminarem
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // Mostra a matriz transposta
        System.out.println("Matriz transposta:");
        mostrarMatriz(matriz);
    }
    
    // Função para transpor uma coluna da matriz
    private static void transporColuna(int[][] matriz, int coluna) {
        int temp;
        for (int i = coluna + 1; i < matriz.length; i++) {
            temp = matriz[coluna][i];
            matriz[coluna][i] = matriz[i][coluna];
            matriz[i][coluna] = temp;
        }
    }
    
    // Função para mostrar a matriz
    private static void mostrarMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}

