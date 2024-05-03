import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            Patterns[] patterns = null;

            // Print the main menu
            printMainMenu();

            // Get user's choice
            int choice = getUserChoice(scanner);

            // Initialize patterns based on user's choice
            switch (choice) {
                case 1:
                    patterns = new Patterns[]{createDoubleDiagonalPattern()};
                    break;
                case 2:
                    patterns = new Patterns[]{createBlackoutPattern()};
                    break;
                case 3:
                    patterns = createBothDiagonalPatterns();
                    break;
                case 4:
                    patterns = new Patterns[]{createFourCornersPattern()};
                    break;
                case 5:
                    patterns = createAllHorizontalPatterns();
                    break;
                case 6:
                    patterns = createAllVerticalPatterns();
                    break;
                case 7:
                    patterns = new Patterns[]{createGPattern()};
                    break;
                case 8:
                    String asciiExit = """
                                                               
                                                                                                                                              
                                      ██████████████████                                                                                      
                                  ████                  ████                                                                                  
                                ██                          ██                                                                                
                                ██                          ██                                                                                
                              ██░░                          ░░▓▓                                                                              
                          ░░  ██  ░░░░                        ██                                                                              
                          ░░░░░░░░░░░░░░░░          ▓▓▓▓▓▓    ██                                                                              
                        ░░░░░░░░░░░░░░░░░░░░        ██████    ██                                                                              
                            ░░██░░  ░░░░░░    ██    ██████    ██                                                                              
                              ░░▓▓  ░░      ▓▓████          ▓▓                                                                                
                              ████  ██                  ██  ████        ████████                      ██  ██                                  
                              ██    ██████████████████████    ██        ██                            ██  ██                                  
                              ██      ██  ██  ██  ██  ██      ██        ██  ▓▓▓▓  ▓▓▓▓▓▓  ██▓▓▓▓  ▓▓▓▓██  ██▓▓▓▓  ▓▓  ██  ▓▓▓▓▓▓              
                              ▒▒████    ██████████████    ████▒▒        ██    ██  ██  ██  ██  ██  ██  ██  ██  ██  ██  ██  ██  ██              
                            ▒▒▒▒▒▒▒▒████      ▓▓  ▓▓  ████▒▒▒▒▒▒▒▒      ████████  ██████  ██████  ██████  ██████  ██████  ██████              
                          ░░▒▒██▒▒▒▒▓▓▓▓██████████████▓▓▓▓▒▒▒▒██▒▒                                                    ██  ██                  
                            ░░░░██████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██████  ██      ████████████████████████████████████████  ██████  ██████    ██  ██  ██
                          ░░░░  ██  ▓▓██████▒▒▒▒▒▒██████    ██  ██▓▓                                                                          
                          ██      ██▓▓▓▓▓▓    ██    ██    ██      ██░░                                                                        
                        ██    ████  ████▓▓▓▓▓▓      ██████  ████  ░░░░                                                                        
                        ██        ██    ██▓▓▓▓▓▓  ████    ██        ░░░░                                                                      
                        ██          ██  ██    ▓▓▓▓  ██  ██          ██                                                                        
                          ██      ██    ████████▓▓▓▓▓▓▓▓  ██      ██                                                                          
                            ▓▓▓▓  ██    ████████████▓▓    ██  ▓▓▓▓                                                                            
                              ██████    ██████████████    ██████                                                                              
                              ██████████████████████████████████                                                                              
                            ██████████████████████████████████████                                                                            
                            ██████████████████  ██████████████████                                                                            
                              ██████████████      ██████████████                                                                              
                          ██████          ██      ██          ██████                                                                          
                          ██            ████      ████            ██                                                                          
                            ▓▓▓▓▓▓▓▓▓▓▓▓              ▓▓▓▓▓▓▓▓▓▓▓▓                                                                            
                      
                            """;                
                    System.out.println(asciiExit);
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }

            if (choice >= 1 && choice <= 7) { // Play game if choice is valid pattern
                BallsQueue ballsQueue = new BallsQueue();
                System.out.println(ballsQueue);
                playBingo(patterns, ballsQueue);
            }

        } while (!askToQuit(scanner)); // Continue playing until the user wants to quit
    }

    private static boolean askToQuit(Scanner scanner) {
        String input;
        do {

            String human = """
_____________________________________________________________________________________________________________________________________________________________
                                                                                                                                                            |
██████╗  ██████╗     ██╗   ██╗ ██████╗ ██╗   ██╗    ██╗    ██╗ █████╗ ███╗   ██╗████████╗    ████████╗ ██████╗     ██████╗ ██╗      █████╗ ██╗   ██╗██████╗ |
██╔══██╗██╔═══██╗    ╚██╗ ██╔╝██╔═══██╗██║   ██║    ██║    ██║██╔══██╗████╗  ██║╚══██╔══╝    ╚══██╔══╝██╔═══██╗    ██╔══██╗██║     ██╔══██╗╚██╗ ██╔╝╚════██╗|
██║  ██║██║   ██║     ╚████╔╝ ██║   ██║██║   ██║    ██║ █╗ ██║███████║██╔██╗ ██║   ██║          ██║   ██║   ██║    ██████╔╝██║     ███████║ ╚████╔╝   ▄███╔╝|
██║  ██║██║   ██║      ╚██╔╝  ██║   ██║██║   ██║    ██║███╗██║██╔══██║██║╚██╗██║   ██║          ██║   ██║   ██║    ██╔═══╝ ██║     ██╔══██║  ╚██╔╝    ▀▀══╝ |
██████╔╝╚██████╔╝       ██║   ╚██████╔╝╚██████╔╝    ╚███╔███╔╝██║  ██║██║ ╚████║   ██║          ██║   ╚██████╔╝    ██║     ███████╗██║  ██║   ██║     ██╗   |
╚═════╝  ╚═════╝        ╚═╝    ╚═════╝  ╚═════╝      ╚══╝╚══╝ ╚═╝  ╚═╝╚═╝  ╚═══╝   ╚═╝          ╚═╝    ╚═════╝     ╚═╝     ╚══════╝╚═╝  ╚═╝   ╚═╝     ╚═╝   |
                                                                                                                                                            |
                                                                                                                                                            |
                                                                                                                                                            |
                ██╗██╗   ██╗██╗         ██╗   ██╗███████╗███████╗                                                                                           |
                ██╔╝╚██╗ ██╔╝╚██╗        ╚██╗ ██╔╝██╔════╝██╔════╝                                                                                          | 
               ██╔╝  ╚████╔╝  ╚██╗        ╚████╔╝ █████╗  ███████╗                                                                                          |
               ╚██╗   ╚██╔╝   ██╔╝         ╚██╔╝  ██╔══╝  ╚════██║                                                                                          |
                ╚██╗   ██║   ██╔╝ ██╗       ██║   ███████╗███████║                                                                                          |     
                 ╚═╝   ╚═╝   ╚═╝  ╚═╝       ╚═╝   ╚══════╝╚══════╝                                                                                          |
                                                                                                                                                            |
                 ██╗███╗   ██╗██╗         ███╗   ██╗ ██████╗                                                                                                |
                ██╔╝████╗  ██║╚██╗        ████╗  ██║██╔═══██╗                                                                                               |
               ██╔╝ ██╔██╗ ██║ ╚██╗       ██╔██╗ ██║██║   ██║                                                                                               |
               ╚██╗ ██║╚██╗██║ ██╔╝       ██║╚██╗██║██║   ██║                                                                                               |
                ╚██╗██║ ╚████║██╔╝ ██╗    ██║ ╚████║╚██████╔╝                                                                                               |
                 ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝    ╚═╝  ╚═══╝ ╚═════╝                                                                                                |
____________________________________________________________________________________________________________________________________________________________|                          
                    """;
            
            System.out.println(human);
            input = scanner.nextLine().toLowerCase();
            if(!input.equalsIgnoreCase("y")&&!input.equalsIgnoreCase("n")){
                System.out.println("Please enter valid input (y/n)");
            } 
        }while (!input.equalsIgnoreCase("y")&&!input.equalsIgnoreCase("n"));
        return input.equals("y");
    }

    private static int getUserChoice(Scanner scanner) {
        int choice = 0;
        boolean validInput = false;
        while (!validInput) {
            if(scanner.hasNextLine()){ 
                String strChoice = scanner.nextLine().replaceAll("0"," ");          
                    try {
                        choice = Integer.parseInt(strChoice);
                            if (choice < 1 || choice > 8){
                                System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                                System.out.print("Enter your choice: ");
                            } else {
                                validInput = true;
                                break;
                            }

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input / There's no input. Please enter a number.");
                        // scanner.nextLine(); // Clear the invalid input
                        System.out.print("Enter your choice: ");
                    }
            } 
        }
        scanner.nextLine(); // Clear the newline character
        return choice;
    }

    // Method to print the main menu
    private static void printMainMenu() {

        String textAscii = """
                
 __| |____________________________________________| |__
 (__   ____________________________________________   __)
    | |              Welcome to Bingo!             | |
    | |        Choose the type of pattern          | |
    | |        -> 1. X Pattern                     | |
    | |        -> 2. Blackout Pattern              | |
    | |        -> 3. Both Diagonal Patterns        | |
    | |        -> 4. Four Corners Pattern          | |
    | |        -> 5. Horizontal Pattern            | |     
    | |        -> 6. Vertical Pattern              | |
    | |        -> 7. G Pattern                     | |
    | |        -> 8. Exit                          | |
  __| |____________________________________________| |__
 (__   ____________________________________________   __)
    | |                                            | |
 
                """;
        // System.out.println("Welcome to Bingo!");
        // System.out.println("Choose the type of pattern:");
        // System.out.println("1. X Pattern");
        // System.out.println("2. Blackout Pattern");
        // System.out.println("3. Both Diagonal Patterns");
        // System.out.println("4. Four Corners Pattern");
        // System.out.println("5. Horizontal Pattern");
        // System.out.println("6. Vertical Pattern");
        // System.out.println("7. G Pattern");
        // System.out.println("8. Exit");
        System.out.println(textAscii);
        System.out.print("Enter your choice: ");
    }



    private static boolean rowIsFilled(int rowIndex, boolean[][] patternMatrix) {
        for (int i = 0; i < 5; i++) {
            if (!patternMatrix[rowIndex][i]) {
                return false; // If any cell in the row is false, the row is not filled
            }
        }
        return true; // If all cells in the row are true, the row is filled
    }


    // Method to create a double diagonal pattern
    private static Patterns createDoubleDiagonalPattern() {
        boolean[][] diagonalPattern = {
                {true, false, false, false, true},
                {false, true, false, true, false},
                {false, false, true, false, false},
                {false, true, false, true, false},
                {true, false, false, false, true}
        };
        return new Patterns("Double Diagonal", diagonalPattern);
    }

    // Method to create a blackout pattern
    private static Patterns createBlackoutPattern() {
        boolean[][] blackoutPattern = {
                {true, true, true, true, true},
                {true, true, true, true, true},
                {true, true, true, true, true},
                {true, true, true, true, true},
                {true, true, true, true, true}
        };
        return new Patterns("Blackout", blackoutPattern);
    }

    // Method to create both left and right diagonal patterns
    private static Patterns[] createBothDiagonalPatterns() {
        Patterns leftDiagonal = Patterns.createDiagonalPattern(true);
        Patterns rightDiagonal = Patterns.createDiagonalPattern(false);
        return new Patterns[]{leftDiagonal, rightDiagonal};
    }
    private static Patterns[] createAllHorizontalPatterns() {
        Patterns[] horizontalPatterns = new Patterns[5];

        // Create horizontal patterns for each row
        for (int row = 0; row < 5; row++) {
            boolean[][] patternMatrix = new boolean[5][5];
            for (int i = 0; i < 5; i++) {
                patternMatrix[row][i] = true;
            }
            horizontalPatterns[row] = new Patterns("Horizontal Row " + (row + 1), patternMatrix);
        }

        return horizontalPatterns;
    }

    private static Patterns[] createAllVerticalPatterns() {
        Patterns[] verticalPatterns = new Patterns[5];

        // Create vertical patterns for each column
        for (int col = 0; col < 5; col++) {
            boolean[][] patternMatrix = new boolean[5][5];
            for (int i = 0; i < 5; i++) {
                patternMatrix[i][col] = true;
            }
            verticalPatterns[col] = new Patterns("Vertical Column " + (col + 1), patternMatrix);
        }

        return verticalPatterns;
    }

    // Method to create the four corners pattern
    private static Patterns createFourCornersPattern() {
        boolean[][] fourCornersPattern = {
                {true, false, false, false, true},
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false, false, false},
                {true, false, false, false, true}
        };
        return new Patterns("Four Corners", fourCornersPattern);
    }

    private static Patterns createGPattern() {
        boolean[][] gPattern = {
                {true, true, true, true, true},
                {true, false, false, false, false},
                {true, false, true, true, true},
                {true, false, false, false, true},
                {true, true, true, true, true}
        };
        return new Patterns("G Pattern", gPattern);
    }

    private static void playBingo(Patterns[] patterns, BallsQueue ballsQueue) {
        BingoCard[] players = new BingoCard[4];
        List<Integer> losingPlayers = new ArrayList<>(); // Keep track of losing players

        // Initialize players
        for (int i = 0; i < players.length; i++) {
            players[i] = new BingoCard();
            players[i].printCard();
        }

        int winnerIndex = -1;
        Set<String> announcedNumbers = new HashSet<>();

        while (winnerIndex == -1) {
            BingoNumber generatedBall = ballsQueue.dequeue(); // Dequeue the next drawn ball
            System.out.println("\nGenerated Ball: " + generatedBall.getValue());

            // Check each pattern for bingo
            for (Patterns pattern : patterns) {
                for (int i = 0; i < players.length; i++) {
                    boolean hasNumber = players[i].markNumber(generatedBall.getValue());
                    String message;
                    if (hasNumber) {
                        message = "Player " + (i + 1) + " has the number " + generatedBall.getValue();
                    } else {
                        message = "Player " + (i + 1) + " does not have the number " + generatedBall.getValue();
                    }

                    // Print the message only if it hasn't been announced before
                    if (!announcedNumbers.contains(message)) {
                        announcedNumbers.add(message);
                        System.out.println(message);
                    }

                    if (players[i].hasBingo(pattern)) {
                        winnerIndex = i;
                        break;
                    }
                }
                // If a winner is found with this pattern, break out of the loop
                if (winnerIndex != -1) {
                    System.out.println();
                    System.out.println("Pattern: "+pattern.getName());
                    break;
                }
            }

            ballsQueue.enqueue(generatedBall); // Add the drawn ball back to the queue

        }
        System.out.println(ballsQueue); // Print the ballsQueue status for demonstration on how the balls queue work
        System.out.println("\nPlayer " + (winnerIndex + 1) + " wins with BINGO!");
        System.out.println("\nCard after marking some numbers:");

        players[winnerIndex].printCard();

        // Print the cards of the other losing players
        for (int i = 0; i < players.length; i++) {
            if (i != winnerIndex) {
                System.out.println("\nPlayer " + (i + 1) + "'s card:");
                players[i].printCard();
                losingPlayers.add(i + 1); // Add losing player to the list
            }
        }


        // Print losing players
        System.out.println("\nLosing players: " + losingPlayers);
    }


}
