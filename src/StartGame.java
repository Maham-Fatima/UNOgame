import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class StartGame {
    public static void main(String[] args) {
        menu();
    }
    public static void Play(){
        GamePlay game = new GamePlay();
        Scanner obj = new Scanner(System.in);
        int size;
        HashSet<String> set = new HashSet<>();
        do {
            System.out.println("Enter number of players greater than 1:");
            size = obj.nextInt();
        }while(size < 2);
        int playerCount = Math.min(size, 4);
        String[] PlayerNames = new String[size];
        for (int i = 0; i < size; i++) {
            System.out.println("Enter names of player: ");
            PlayerNames[i] = obj.next();
            while(set.contains(PlayerNames[i])) {
                System.out.println("Player name already exist try another name:");
                System.out.println("Enter names of player: ");
                PlayerNames[i] = obj.next();
            }
            set.add(PlayerNames[i]);
        }
        DoublyCircularLinkedListForTurns playerCircle = new DoublyCircularLinkedListForTurns();
        System.out.println("Players name:"+ Arrays.toString(PlayerNames));
        for (int i=0; i< playerCount ;i++) {
            System.out.println(PlayerNames[i]);
            playerCircle.AddInGame(new competitor(PlayerNames[i]));
        }
        // player greator than four will be added in the wait list
        Queue queue = null;
        if(PlayerNames.length > 4) {
            System.out.println("Remaining players are added in the waiting list");
            queue = new Queue(PlayerNames.length - 4);
            for (int j = 4; j < PlayerNames.length; j++) {
                System.out.println(PlayerNames[j]+ " is added in the queue");
                queue.enqueue(new competitor(PlayerNames[j]));
            }
        }
        System.out.println(playerCircle.getSize());
        competitor loser = game.start(playerCircle);
        competitor newPlayer;
        if(queue != null) {
            while (!queue.isEmpty()) {
                newPlayer = queue.dequeue();
                queue.enqueue(loser);
                playerCircle.AddInGame(newPlayer);
                playerCircle.removePlayer(loser);
                System.out.println("\n\n" + newPlayer.competitorname() + " (from wait list) IN, " + loser.competitorname() + " OUT. \n\n");
                System.out.println("=================> Ready For Next Round <============= \n\n");
                loser = game.start(playerCircle);
            }
        }
    }
    public static void Exit(){
        System.exit(1);
    }
    public static void HowToPlay(){
        System.out.println("How to play: "+"\nWrite the instructions here");
        System.out.println("1.First Entered The Number Of Players\n2.Enter Name Of Players\n3.If Players are greater than 4 then players will be sent to wait list\n4.Cards will be shuffled\n5.Then 7 cards will be distributed to each player\n6.System will initiate the game by placing one card\n7.Then the game will start\n8.If the player have Draw two then the next player will pick two cards\n9.If the player have Draw Four then the next player will pick four cards\n10.If the player have SKIP card the next player turn will be skipped\n11.If player have Wild card then the player can pick any colour of card\n12.If the player don't have required card then he can pick the card from the deck of cards\n13.If the player have 0  card he will be the winner and if the player have maximum cards among all the players he will be the loser");
    }
    public static void menu() {
        Scanner obj = new Scanner(System.in);
        String h = null;
        System.out.println("***************************************");
        System.out.println("                 MENU                   ");
        System.out.println("*****************************************");
        System.out.println("WHAT DO YOU WANT?");
        System.out.println("1.START GAME\n2.SHOW INSTRUCTIONS\n3.EXIT");
        int option = obj.nextInt();
        do{
            switch (option) {
                case 1:
                    Play();
                    break;
                case 2:
                    HowToPlay();
                    break;
                case 3:
                    Exit();
                    break;
            }
            System.out.println("DO YOU WANT TO CONTINUE (Y-N)");
            h = obj.next();
           } while (!h.equals("N")) ;


    //    while(h.equals("Y"));
        
        
        
    }
}
