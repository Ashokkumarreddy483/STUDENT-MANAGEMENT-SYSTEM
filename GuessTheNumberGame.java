import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 10;
        int score = 0;
        
        System.out.println("Welcome to the Guess the Number Game!");
        System.out.println("You need to guess a number between " + minRange + " and " + maxRange + ".");
        System.out.println("You have " + maxAttempts + " attempts for each round.");

        boolean playAgain = true;
        while (playAgain) {
            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;
            boolean guessedCorrectly = false;
            
            System.out.println("\nNew round! Guess the number:");

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Attempt #" + (attempts + 1) + ": ");
                int guess = scanner.nextInt();
                scanner.nextLine(); // Consume newline left by nextInt()

                if (guess < minRange || guess > maxRange) {
                    System.out.println("Your guess is out of the valid range (" + minRange + " - " + maxRange + "). Try again.");
                    continue;
                }

                attempts++;

                if (guess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number " + randomNumber + " in " + attempts + " attempts.");
                    score++;
                    guessedCorrectly = true;
                } else if (guess < randomNumber) {
                    System.out.println("Too low! Try a higher number.");
                } else {
                    System.out.println("Too high! Try a lower number.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you did not guess the number. It was " + randomNumber + ".");
            }

            System.out.print("\nDo you want to play again? (yes/no): ");
            String playChoice = scanner.nextLine().trim().toLowerCase();
            if (!playChoice.equals("yes")) {
                playAgain = false;
            }
        }

        System.out.println("\nGame Over! Your total score is: " + score);
        scanner.close();
    }
}
