/* Crie um programa em Java, utilizando 5 threads, que gere e apresente números aleatórios de 0 a
2000. Cada Thread deverá gerar 200 números. Assim, serão gerados 1000 números, que deverão
ser apresentados em ordem decrescente.
 */



// Arquivo RandomNumberGenerator.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Threads5 {

    public static void main(String[] args) {
        List<Integer> numbers = Collections.synchronizedList(new ArrayList<>());

        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new RandomNumberThread(numbers);
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Collections.sort(numbers, Collections.reverseOrder());

        System.out.println("Generated numbers in descending order:");
        for (Integer number : numbers) {
            System.out.println(number);
        }
    }

    static class RandomNumberThread extends Thread {
        private final List<Integer> numbers;
        private final Random random;

        public RandomNumberThread(List<Integer> numbers) {
            this.numbers = numbers;
            this.random = new Random();
        }

        @Override
        public void run() {
            for (int i = 0; i < 200; i++) {
                int randomNumber = random.nextInt(2001);
                numbers.add(randomNumber);
            }
        }
    }
}
