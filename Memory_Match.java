import java.util.*; 
public class Memory_Match
{    
public static void main() {
        final int NUM_PAIRS = 4;
        int board[] = new int[NUM_PAIRS*2+1];
        boolean printElement[] = new boolean[NUM_PAIRS*2+1];
        int temp, position1, position2,i;
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
        //the actual game begins
        Scanner console = new Scanner(System.in);
        int count = 0;
        String playerOne = "";
        String playerTwo = "";
        int playerOnescore = 0;
        int playerTwoscore = 0;
        int guess1 = 0;
        int guess2 = 0;
        int decider = 1;
        boolean flag = false;
        System.out.println("Player 1's name? ");
        playerOne = console.next();
        System.out.println("Player 2's name? ");
        playerTwo = console.next();
        for (i=0; i<board.length; i++) { // prints out board with nothing
            if (printElement[i]==true)
                System.out.print(board[i] +  "  ");
            else
                System.out.print(" * ");
        }
        System.out.println();
        System.out.println(Arrays.toString(board));
        while (count < board.length) { // checks if the entire board is flipped or not
            for (int p = 1; p < board.length; p++) {
                if (printElement[p] == true) {
                    count++;
                }
            }
            if (decider % 2 == 1) { // player 1's turn
                 System.out.println(playerOne + ", what is your 1st choice? ");
                 System.out.println();
                 guess1 = console.nextInt();
                 decider = game(guess1, guess2, decider, playerOnescore, playerTwoscore, playerOne, playerTwo, printElement, board);
                 if (decider % 2 == 1) {
                    playerOnescore++;
                }
                System.out.println(playerOne + ": " + playerOnescore + " points");
                System.out.println(playerTwo + ": " + playerTwoscore + " points");
            } else { // player 2's turn
                System.out.println(playerTwo + ", what is your 1st choice? ");
                System.out.println();
                guess1 = console.nextInt();
                decider = game(guess1, guess2, decider, playerOnescore, playerTwoscore, playerOne, playerTwo, printElement, board);
                if (decider % 2 == 0) {
                    playerTwoscore++;
                }
                System.out.println(playerOne + ": " + playerOnescore + " points");
                System.out.println(playerTwo + ": " + playerTwoscore + " points");
            }
            System.out.println();
        }
}      
public static int game(int guess1, int guess2, int decider, int playerOnescore, int playerTwoscore, String playerOne, String playerTwo, boolean[] printElement, int[] board) {
    Scanner console = new Scanner(System.in);
    int playerOnes = 0;
    int playerTwos = 0;
    while (printElement[guess1] == true) {
    System.out.println("You sly fox, try again");
    System.out.println("What is your 1st choice? ");
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
    System.out.println("What is your 2nd choice? ");
    System.out.println();
    guess2 = console.nextInt();
    while (printElement[guess2] == true) { //checks if a sneaky man chooses an already flipped number on his second choice
        System.out.println("You sly fox, try again");
        System.out.println("What is your 2nd choice? ");
        guess2 = console.nextInt();
    }
    if (board[guess1] == board[guess2]) { // if both choices match
        printElement[guess2] = true;
        if (decider % 2 == 1) {
            playerOnes++; 
            
        } else {
            playerTwos++;
            
        }
        for (int a = 1; a < board.length; a++) {
            if (printElement[a] == true) {
                System.out.print(board[a] + " ");
            } else {
                System.out.print(" * ");
            }
        }
        System.out.println("MATCH!!! YOU GOT A MATCH!!! " + "go again!!!");
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
        decider++;
    }
    return decider;
    }
}