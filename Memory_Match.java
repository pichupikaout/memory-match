import java.util.*; 
public class Memory_Match {
public static final int NUM_PAIRS = 4;
public static void main() {
        boardCreate();
    }
public static void boardCreate() { // creation and shuffling of the board
        
        int board[] = new int[NUM_PAIRS*2+1];
        boolean printElement[] = new boolean[NUM_PAIRS*2+1];
        int temp = 0;
        int position1 = 0;
        int position2 = 0;
        int i = 0;
        int k=1;
        for (i=1; i<=NUM_PAIRS*2; i+=2) {
            board[i] = k;
            board[i+1] = k; 
            k++;
        }
        for (i=1; i<=1000; i++) {  
              position1 = (int) (Math.random() * (board.length-1) + 1);
              position2 = (int) (Math.random() * (board.length-1) + 1);   
           
              temp = board[position1];
              board[position1] = board[position2];
              board[position2] = temp;
        }
        prompt(board, printElement);
    }
public static void prompt(int[] board, boolean[] printElement) { //the actual game begins here
        Scanner console = new Scanner(System.in);
        int numbersFlipped = 0;
        String playerOne = "";
        String playerTwo = "";
        int playerOnescore = 0;
        int playerTwoscore = 0;
        boolean player = true;
        System.out.println("Player 1's name? ");
        playerOne = console.next();
        System.out.println("Player 2's name? ");
        playerTwo = console.next();
        for (int i=0; i<board.length; i++) { // prints out board with nothing
            if (printElement[i]==true)
                System.out.print(board[i] +  "  ");
            else
                System.out.print(" * ");
        }
        System.out.println();
        while (numbersFlipped <= board.length - 1) { // checks if the entire board is flipped or not
            for (int p = 1; p < board.length; p++) { // the actual algorithm 
            if (player == true) { // player 1's turn
                player = game(playerOnescore, playerTwoscore, playerOne, playerTwo, printElement, board, player);
                if (player == true) { //checks if player 1 won
                    playerOnescore++;
                    numbersFlipped+=2;
                }
                System.out.println(playerOne + ": " + playerOnescore + " points");
                System.out.println(playerTwo + ": " + playerTwoscore + " points");
            } else { // player 2's turn
                player = game(playerOnescore, playerTwoscore, playerOne, playerTwo, printElement, board, player);
                if (player == false) { //checks if player 2 won
                    playerTwoscore++;
                    numbersFlipped+=2;
                }
                System.out.println(playerOne + ": " + playerOnescore + " points");
                System.out.println(playerTwo + ": " + playerTwoscore + " points");
            }
            System.out.println();
        }
    } 
}     
public static boolean game(int playerOnescore, int playerTwoscore, String playerOne, String playerTwo, boolean[] printElement, int[] board, boolean player) {
    Scanner console = new Scanner(System.in);
    int guess1 = 0;
    int guess2 = 0;
    if (player == true) {
        System.out.println(playerOne + ", what is your 1st choice? ");
        System.out.println();
    } else {
        System.out.println(playerTwo + ", what is your 1st choice? ");
        System.out.println();
        
    }
    guess1 = console.nextInt();
    while (printElement[guess1] == true) { //checks if a sneaky man decides to choose an already flipped number on the 1st guess
        System.out.println("You sly fox, try again");
        if (player == true) {
            System.out.println(playerOne + ", what is your 1st choice? ");
        } else {
            System.out.println(playerTwo + ", what is your 1st choice? ");
        }
        guess1 = console.nextInt(); 
    }
    printElement[guess1] = true;
    for (int a = 1; a < board.length; a++) { 
        if (printElement[a] == true) {
            System.out.print(board[a] + " ");
        } else {
            System.out.print(" * ");
        }     
    }
    if (player == true) {
            System.out.println(playerOne + ", what is your 2nd choice? ");
            System.out.println();
        } else {
            System.out.println(playerTwo + ", what is your 2nd choice? ");
            System.out.println();
        }
    guess2 = console.nextInt();
    while (printElement[guess2] == true) { //checks if a sneaky man decides to choose an already flipped number on the 2nd guess
        System.out.println("You sly fox, try again");
        if (player == true) {
            System.out.println(playerOne + ", what is your 2nd choice? ");
        } else {
            System.out.println(playerTwo + ", what is your 2nd choice? ");
        }
        guess2 = console.nextInt(); 
    }
    if (board[guess1] == board[guess2]) { // if both choices match
        printElement[guess2] = true;
        for (int a = 1; a < board.length; a++) {
            if (printElement[a] == true) {
                System.out.print(board[a] + " ");
            } else {
                System.out.print(" * ");
            }
        }
        if (player == true) {
            System.out.println("MATCH!!!!! " + playerOne + " goes again!");
            System.out.println();
        } else {
            System.out.println("MATCH!!!!! " + playerTwo + " goes again!");
            System.out.println();
        }
    } else { // if it doesn't, goes to next player
        printElement[guess1] = false;
        for (int a = 1; a < board.length; a++) {
            if (printElement[a] == true) {
                System.out.print(board[a] + " ");
            } else {
                System.out.print(" * ");
            }
        }
        System.out.println("No match");
        if (player == true) {
            player = false;
        } else {
            player = true;
        }
    }
    return player;
    }
}
