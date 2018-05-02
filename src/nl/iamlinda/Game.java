package nl.iamlinda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game {
    // Maximum number of tries/ misses the user gets
    public static final int MAX_MISSES = 7;

    // ArrayList containing all hard-coded answers, of which one will be randomly chosen inside constructor
    public static final ArrayList<String> answers = new ArrayList<>();
    private String[] allAnswers = new String[] {"encapsulation", "class", "object", "shadowing", "constructor", "programmer", "developer", "primitive", "integer", "variable", "signature", "parameter", "argument"};

    private String answer;
    private String hits;
    private String misses;

    public Game() {
        // Adding allAnswers array to answers ArrayList
        answers.addAll(Arrays.asList(allAnswers));

        // Choosing random answer for the game
        Random randomAnswer = new Random();
        int index = randomAnswer.nextInt(answers.size());
        this.answer = answers.get(index);

        // Setting hits and misses to empty to prevent NPE
        hits = "";
        misses = "";
    }

    public String getAnswer() {
        return answer;
    }

    private char checkUserInput(char letter) {
        // If the char is not a letter...
        if (!Character.isLetter(letter)) {
            // Exceptions are caught in Prompter class at promptForGuess()
            throw new IllegalArgumentException("A letter is required");
        }
        letter = Character.toLowerCase(letter);

        // If the char has already been guessed...
        // If the char does not exist inside the strings misses or hits, it returns a -1
        if (misses.indexOf(letter) != -1 || hits.indexOf(letter) != -1) {
            throw new IllegalArgumentException(letter + " has already been guessed");
        }
        return letter;
    }

    // This is where user input goes through first
    public boolean applyGuess(String letters) {
        // If the user didn't enter anything...
        if (letters.length() == 0) {
            throw new IllegalArgumentException("No letter found");
        }
        // If the user entered a letter, applyGuess with the first char
        // So if user enters multiple letters, it ignores everything after index 0
        return applyGuess(letters.charAt(0));
    }

    // Checks if user input is a hit or a miss
    public boolean applyGuess(char letter) {
        // First check if user input is valid
        letter = checkUserInput(letter);
        // Then check if it's a hit. If the char is present in the answer, the index is not -1, isHit = true
        boolean isHit = answer.indexOf(letter) != -1;
        if (isHit) {
            hits += letter;
        } else {
            misses += letter;
        }
        return isHit;
    }

    // For every miss, the number of tries goes down
    public int getRemainingTries() {
        return MAX_MISSES - misses.length();
    }

    // For every char in the answer, first enter -
    // If there was a hit, show the letter
    public String getCurrentProgress() {
        String progress = "";
        for (char letter : answer.toCharArray()) {
            char display = '-';
            if (hits.indexOf(letter) != -1) {
                display = letter;
            }
            progress += display;
        }
        return progress;
    }

    // If no '-' chars are left in String progress of getCurrentProgress(), isWon() = true
    public boolean isWon() {
        return getCurrentProgress().indexOf('-') == -1;
    }
}
