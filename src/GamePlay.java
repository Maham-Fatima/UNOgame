import java.util.Arrays;
import java.util.Scanner;

public class GamePlay {

    public competitor start(DoublyCircularLinkedListForTurns playerCircle){

        DeckHandling deck = new DeckHandling();
        deck.insertInStack();
        deck.shuffleCards();
        competitor player = playerCircle.getHead();
        System.out.println("Every one get 7 cards.");
        for (int i=0; i < playerCircle.getSize(); i++){
            for (int j=0; j<7; j++) {
                player.GiveCard(deck.DrawCard());
            }
            System.out.print(player.competitorname() + ": ");
            player.printCardsInHand();
            player = player.getSucceeding();
        }
        deck.discardCard(deck.DrawCard());
        System.out.println("\nSystem place down: " + deck.LastCard().card + "\n");
        System.out.println("Game Starts.....Best ofLuck!\n");

        competitor winner;
        competitor loser;
        System.out.println("First player: "+playerCircle.getHead().competitorname());
        competitor FP = playerCircle.getHead();
        Scanner obj = new Scanner(System.in);
        int most_num_cards = 0;
        int dir = 1 ;
        int r = 0;

        while (true){
            System.out.print("\n"+FP.competitorname() + "'s turn  ---------------------- ");
            FP.printCardsInHand();
            int draw2;
            int draw4;
            draw2 = deck.LastCard().isDrawTwo ? 2:-1;
            draw4 = deck.LastCard().isWildDrawFour ? 4:-1;
            if (Math.max(draw2, draw4) > 0 ){
                System.out.print("Special card has been placed. auto draw "+ Math.max(draw2, draw4) + " cards from deck : ");
                for (int i=0; i< Math.max(draw2, draw4); i++){
                    Card newCard = deck.DrawCard();
                    FP.GiveCard(newCard);
                    System.out.print(newCard.card+ ", ");
                }
                System.out.println();
            }
            if (deck.LastCard().isSkip){
                System.out.println("\n" + FP.competitorname()+ " is skipped. \n");
                FP = dir==1? FP.getSucceeding():FP.getPreceding();
                System.out.println(FP.competitorname() + "'s turn  ---------------------- " + "(" + FP.getHand().size + ")");
            }
            String[] suggested = suggested(FP.getHand(), deck.LastCard());

            if (suggested.length == 0){
                Card newCard = deck.DrawCard();
                FP.GiveCard(newCard);
                System.out.println("No card to place down, auto draw one card from deck :+ " + newCard.card + "\n");
                System.out.print("Current status: ");
                FP.printCardsInHand();
                System.out.println("Cards remaining: "+FP.getHand().getSize());

            }else{
                System.out.println("Suggested (pick by entering name): "+ Arrays.toString(suggested));
                String card = obj.nextLine();

                while (! Arrays.asList(suggested).contains(card)){
                    System.out.println("Can't place this card try another: ");
                    card = obj.nextLine();
                }

                System.out.println("Placed Down: " + card);
                Card discard = FP.removeFromHand(card);
                deck.discardCard(discard);
                System.out.println("Total cards left: " +FP.getHand().getSize() + "\nRemaining cards name:");
                FP.printCardsInHand();
                if(FP.winner()){
                    winner = FP;
                    break;
                }

                if (card.contains("Reverse")){
                    dir = dir==1? 0:1;
                    System.out.println("\n\n-- direction has been reversed -- \n\n");
                }
            }

            FP = dir==1 ? FP.getSucceeding(): FP.getPreceding();
            r++;
        }

        /*loser = playerCircle.getHead();
        int size = Math.min(playerCircle.getSize(), 5);
        for (int i=0; i< size; i++){
            if(loser.getHand().getSize() >= most_num_cards){
                most_num_cards = loser.getHand().getSize();
            }
            loser = loser.getSucceeding();
        }*/
        HeapSortToExtractLoser h = new HeapSortToExtractLoser(playerCircle);
        h.BuildMaxHeap();
        loser = h.ExtractMaxCard();

        System.out.println("\n********************************");
        System.out.println("Game finished. Report: ");
        System.out.println("number of rounds: " + r/4);
        System.out.println("+ winner: " + winner.competitorname());
        System.out.println("- loser:  " + loser.competitorname() +" No. of Cards("+loser.getHand().getSize()+")");
        h.DisplayScoreCard();
        return loser;
    }

    public String[] suggested(SingleLinkedListForCardLinking hand, Card last){
        String[] suggested = new String[hand.getSize()];
        Node card = hand.peek();
        int i;
        if(last.isWild||last.isWildDrawFour){
            for ( i = 0; card != null ; i++) {
                suggested[i] = card.data.card;
                card = card.next;
            }
        }else {
             i = 0;

            while (card != null) {
                if (last.CheckValidityOfCards(card.data)) {
                    suggested[i] = card.data.card;
                    i++;
                }
                card = card.next;
            }
        }
        return Arrays.copyOfRange(suggested, 0, i);
    }
}