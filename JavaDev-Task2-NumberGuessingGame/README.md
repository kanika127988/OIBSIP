# Oasis Infobyte - Java Development Internship

## Task 2: Number Guessing Game
A console-based Java application where the computer generates a random number between 1 and 100, and the user attempts to guess it within a limited number of tries, receiving "Too High!" or "Too Low!" hints.

### Features
* Random number generation (1 to 100)
* Attempt counter (maximum 7 attempts per round)
* Input validation for numeric values
* Multi-round gameplay support

### How to Run
1. Open the project in VS Code or your Java IDE.
2. Compile and run `NumberGuessingGame.java`.
3. Follow the console prompts to enter your guesses.

### Sample Output / Execution
```text
=== Welcome to the Number Guessing Game! ===

--- Round 1 ---
I have chosen a number between 1 and 100.
You have 7 attempts to guess it.
Enter your guess: 50
Too High!
Attempts remaining: 6
Enter your guess: 25
Too Low!
Attempts remaining: 5
Enter your guess: 37
Correct! You guessed the number in 3 attempts.

Would you like to play another round? (yes/no): no
Thanks for playing! Goodbye.

