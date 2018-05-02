package nl.iamlinda;

import java.util.Scanner;

public class Prompter {
    private Game game;

    public Prompter(Game game) {
        this.game = game;
    }

    public void promptForGuess() {
        Scanner scanner = new Scanner(System.in);
        // boolean check if user input is an acceptable answer
        boolean isAcceptable = false;

        do {
            // Prompt user for input
            System.out.print("Enter a letter:  ");
            String guessInput = scanner.nextLine();

            try {
                // If it's a right guess, isHit = true
                game.applyGuess(guessInput);
                isAcceptable = true;
            } catch(IllegalArgumentException iae) {
                // Any Illegal Arguments will be caught here.
                System.out.printf("%s. Please try again. %n",
                        iae.getMessage());
            }
        } while (!isAcceptable);
    }

    // Show number of tries left + the right letters in the answers
    public void displayProgress() {
        System.out.printf("You have %d tries left to solve:  %s%n",
                game.getRemainingTries(),
                game.getCurrentProgress());
    }

    // Shows the outcome of the game when it's lost or won
    public void displayOutcome() {
        if(game.isWon()) {
            System.out.printf("Congratulations! You won with %d tries remaining!%n",
                    game.getRemainingTries());
        } else {
            System.out.printf("Sorry, the word was %s%n",
                    game.getAnswer());
        }
    }
}
