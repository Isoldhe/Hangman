package nl.iamlinda;

public class Main {

    public static void main(String[] args) {

        // Game contains all game related methods
        Game game = new Game();
        // Prompter asks user input and displays messages about the user's progress
        Prompter prompter = new Prompter(game);

        // As long as the user has tries remaining and the game is not won, keep playing
        while (game.getRemainingTries() > 0 && !game.isWon()) {
            prompter.displayProgress();  // Prints: "You have ... tries left to solve"
            prompter.promptForGuess(); // Starts prompting for input
        }
        prompter.displayOutcome();
    }
}
