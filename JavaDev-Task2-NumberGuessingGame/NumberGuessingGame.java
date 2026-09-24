import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        boolean playAgain = true;
        int totalRounds = 0;
        
        System.out.println("=== Welcome to the Number Guessing Game! ===");
        
        while (playAgain) {
            totalRounds++;
            int targetNumber = random.nextInt(100) + 1; // Generates 1 to 100
            int attempts = 0;
            int maxAttempts = 7;
            boolean hasGuessedCorrectly = false;
            
            System.out.println("\n--- Round " + totalRounds + " ---");
            System.out.println("I have chosen a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");
            
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                
                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid integer!");
                    scanner.next(); 
                    continue;
                }
                
                int guess = scanner.nextInt();
                attempts++;
                
                if (guess == targetNumber) {
                    System.out.println("Correct! You guessed the number in " + attempts + " attempts.");
                    hasGuessedCorrectly = true;
                    break;
                } else if (guess > targetNumber) {
                    System.out.println("Too High!");
                } else {
                    System.out.println("Too Low!");
                }
                
                System.out.println("Attempts remaining: " + (maxAttempts - attempts));
            }
            
            if (!hasGuessedCorrectly) {
                System.out.println("You Lost! Out of attempts. The correct number was: " + targetNumber);
            }
            
            System.out.print("\nWould you like to play another round? (yes/no): ");
            String choice = scanner.next();
            if (!choice.equalsIgnoreCase("yes")) {
                playAgain = false;
                System.out.println("Thanks for playing! Goodbye.");
            }
        }
        
        scanner.close();
    }
}