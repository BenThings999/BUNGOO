import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Bungo {
    private BingoNumber[][] card;

    public Bungo() {
        this.card = generateCard();
    }

    private BingoNumber[][] generateCard() {
        BingoNumber[][] card = new BingoNumber[5][5];
        Random random = new Random();

        // Generate numbers for each column
        List<List<Integer>> columnNumbers = new ArrayList<>();
        columnNumbers.add(generateColumnNumbers(1, 15)); // B column
        columnNumbers.add(generateColumnNumbers(16, 30)); // I column
        columnNumbers.add(generateColumnNumbers(31, 45)); // N column
        columnNumbers.add(generateColumnNumbers(46, 60)); // G column
        columnNumbers.add(generateColumnNumbers(61, 75)); // O column

        // Shuffle each column separately
        for (List<Integer> column : columnNumbers) {
            Collections.shuffle(column);
        }

        // Fill the card with numbers from each column
        for (int column = 0; column < 5; column++) {
            for (int row = 0; row < 5; row++) {
                int value;
                // For the center cell, mark it as FREE
                if (row == 2 && column == 2) {
                    card[row][column] = new BingoNumber(0);
                    card[row][column].setMarked(true);
                } else {
                    // Get number from the corresponding column
                    value = columnNumbers.get(column).get(row);
                    card[row][column] = new BingoNumber(value);

                }
            }
        }

        return card;
    }

    // Helper method to generate numbers for a specific column range
    private List<Integer> generateColumnNumbers(int start, int end) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            numbers.add(i);
        }
        return numbers;
    }


    public void markNumber(int value) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (card[i][j] != null && card[i][j].getValue() == value) {
                    card[i][j].setMarked(true);
                    return;
                }
            }
        }
    }

    private String generatePadding(int numberOfSpaces) {
        StringBuilder padding = new StringBuilder();
        for (int i = 0; i < numberOfSpaces; i++) {
            padding.append(" ");
        }
        return padding.toString();
    }

    public void printCard() {
        int maxDigits = 0;
        System.out.println("==========================================================================");
        System.out.println("|  B         | |  I         | |  N         | |  G         | |  O         |");
        System.out.println("==========================================================================");

        for (BingoNumber[] row : card) {
            for (BingoNumber element : row) {
                if (element != null) {
                    int value = element.getValue();
                    int digits = (int) Math.log10(value) + 1;
                    if (digits > maxDigits) {
                        maxDigits = digits;
                    }
                }
            }
        }

        int paddingSize = maxDigits + 4; // +4 for '|' on each side and space
        String padding = generatePadding(paddingSize - 2); // Internal padding

        for (BingoNumber[] row : card) {
            for (BingoNumber element : row) {
                System.out.print("| "); // Leading pipe and space
                if (element != null) {
                    int value = element.getValue();
                    if (value == 0) {
                        System.out.print(" FREE " + generatePadding(paddingSize - 6)); // Adjusted
                    } else {
                        int digits = (int) Math.log10(value) + 1;
                        int paddingNeeded = maxDigits - digits;
                        System.out.print(String.format("%" + (digits + paddingNeeded) + "s", value) + padding);
                    }
                    String markPadding = generatePadding(paddingSize - 6); // Adjusted
                    System.out.print(element.isMarked() ? " (X)" + markPadding : generatePadding(paddingSize - 2));
                }
                System.out.print(" | "); // Trailing pipe
            }
            System.out.println();
        }
    }

    public boolean checkNumber(int number) {
        // Check if the generated number exists on the card
        for (BingoNumber[] row : card) {
            for (BingoNumber element : row) {
                if (element != null && element.getValue() == number) {
                    // Number found on the card
                    return true;
                }
            }
        }
        // Number not found on the card
        return false;
    }
    public boolean hasBingo() {
        // Check rows
        for (int row = 0; row < 5; row++) {
            boolean bingo = true;
            for (int col = 0; col < 5; col++) {
                if (!card[row][col].isMarked()) {
                    bingo = false;
                    break;
                }
            }
            if (bingo) return true;
        }

        // Check columns
        for (int col = 0; col < 5; col++) {
            boolean bingo = true;
            for (int row = 0; row < 5; row++) {
                if (!card[row][col].isMarked()) {
                    bingo = false;
                    break;
                }
            }
            if (bingo) return true;
        }

        // Check diagonals
        boolean diagonal1Bingo = true;
        boolean diagonal2Bingo = true;
        for (int i = 0; i < 5; i++) {
            if (!card[i][i].isMarked()) {
                diagonal1Bingo = false;
            }
            if (!card[i][4 - i].isMarked()) {
                diagonal2Bingo = false;
            }
        }
        if (diagonal1Bingo || diagonal2Bingo) return true;

        return false;
    }

}