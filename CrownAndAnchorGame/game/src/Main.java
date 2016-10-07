
import java.util.List;

import Test.DebugLogger;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

      
        System.out.println("Press 1 for start the Game");
        System.out.println("Press 2 for Gaming Instucions");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your selection : ");
        
        int selection = scanner.nextInt();
        
        if(selection==1){
        
      actoin();
    }else{
              System.out.print("RULES FOR PLAYING CROWN AND ANCHOR\n"
                + "1. Crown and Anchor shall be played on a layout mat which is marked in a manner similar to that\n"
                + "shown in the diagram:-\n"
                + "\n"
                + " \n"
                + "2. The game shall be played with three identical dice with the faces of each dice marked with the\n"
                + "symbols of a crown, anchor, heart, diamond, club and spade.\n"
                + "3. The game shall be controlled by a dealer who is not permitted to wager.\n"
                + "4. No person other than the dealer shall throw the dice or activate the dice cage.\n"
                + "5. If any of the three dice fail to come to rest with one surface flat to the base of the dice cage or flat\n"
                + "on the layout mat the dealer shall call \"No Spin/Throw”\n"
                + "6. If the dealer calls \"No Spin/Throw\" all wagers shall be void on that spin/throw.\n"
                + "7. The minimum and maximum wagers shall be prominently displayed on a sign at the table.\n"
                + "8. A wager on a particular symbol shall win if the symbol appears on one or more of the uppermost\n"
                + "face of the three dice and shall lose if the symbol does not appear.\n"
                + "9. Winning wagers shall be paid at odds not less than:-\n"
                + "if the symbol appears on the uppermost face of 3 dice 3 to 1\n"
                + "if the symbol appears on the uppermost face of 2 dice 2 to 1\n"
                + "if the symbol appears on the uppermost face of 1 dice 1 to 1\n"
                + "10. A person under the age of 18 years shall not participate in the game or be involved in the dealing\n"
                + "or conduct of the game. ");
              
              System.out.println("");
              System.out.println("");
              
              System.out.print("Press 1 , continue");
              System.out.println("");
              System.out.print("Press 2 , Exit");
              
              System.out.print("?" );
             // 
            int ans = scanner.nextInt();
            if (ans==1) {
                actoin();
            }else{
                System.exit(0);
            }
              

        }
    }

    public static void actoin()throws Exception{
         

        DebugLogger debugLog = new DebugLogger();
        debugLog.setLOgger("122344");
        debugLog.setLOgger("55655555");
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Dice d3 = new Dice();

        //org.junit.runner.JUnitCore.main("Test.CorrectPayLevelTest");
        Player player = new Player("Fred", 100);
        Game game = new Game(d1, d2, d3);
        List<DiceValue> cdv = game.getDiceValues();

        int totalWins = 0;
        int totalLosses = 0;

        while (true) {
            int winCount = 0;
            int loseCount = 0;

            for (int i = 0; i < 100; i++) {
                String name = "Fred";
                int balance = 100;
                int limit = 0;
                player = new Player(name, balance);
                player.setLimit(limit);
                int bet = 5;

                System.out.println(String.format("Start Game %d: ", i));
                System.out.println(String.format("%s starts with balance %d, limit %d",
                        player.getName(), player.getBalance(), player.getLimit()));

                int turn = 0;
                while (player.balanceExceedsLimitBy(bet) && player.getBalance() < 200) {
                    turn++;
                    DiceValue pick = DiceValue.getRandomForPlayer();

                    System.out.printf("Turn %d: %s bet %d on %s\n",
                            turn, player.getName(), bet, pick);

                    int winnings = game.playRound(player, pick, bet);
                    cdv = game.getDiceValues();

                    System.out.printf("Rolled %s, %s, %s\n",
                            cdv.get(0), cdv.get(1), cdv.get(2));

                    if (winnings > 0) {
                        System.out.printf("%s won %d, balance now %d\n\n",
                                player.getName(), winnings, player.getBalance());
                        winCount++;
                    } else {
                        System.out.printf("%s lost, balance now %d\n\n",
                                player.getName(), player.getBalance());
                        loseCount++;
                    }

                } //while

                System.out.print(String.format("%d turns later.\nEnd Game %d: ", turn, i));
                System.out.println(String.format("%s now has balance %d\n", player.getName(), player.getBalance()));

            } //for

            System.out.println(String.format("Win count = %d, Lose Count = %d, %.2f", winCount, loseCount, (float) winCount / (winCount + loseCount)));
            System.out.println("");
            System.out.print("If you want exit press 'q' , 'Enter' for continue");
            totalWins += winCount;
            totalLosses += loseCount;

            String ans = console.readLine();
            if (ans.equals("q")) {
                break;
            }
        } //while true

        System.out.println(String.format("Overall win rate = %.1f%%", (float) (totalWins * 100) / (totalWins + totalLosses)));
    }
}
