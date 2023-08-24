import java.util.Random;

public class DeckHandling {
    StackOfCards sc;
    StackOfCards placedCards;
    Card lastCard=null;
    Card[] cards = new Card[108];
    int index;

    public DeckHandling(){
        index =0;
        sc = new StackOfCards();
        placedCards = new StackOfCards();
        InitializeNumberCards();
        InitializeSpecialCards();
    }
    public void InitializeNumberCards(){
        // zero cards
        cards[index++]= new Card(0,"Red");
        cards[index++]= new Card(0,"Green");
        cards[index++]= new Card(0,"Blue");
        cards[index++]= new Card(0,"Yellow");
        for (int i = 1; i <= 9; i++) {
            // two cards per color
            cards[index++]= new Card(i,"Red");
            cards[index++]= new Card(i,"Red");
            cards[index++]= new Card(i,"Green");
            cards[index++]= new Card(i,"Green");
            cards[index++]= new Card(i,"Blue");
            cards[index++]= new Card(i,"Blue");
            cards[index++]= new Card(i,"Yellow");
            cards[index++]= new Card(i,"Yellow");
        }
    }
    public void InitializeSpecialCards(){
        for (int i = 1; i <= 2; i++) {
            cards[index++] = new Card("Draw two","Red");
            cards[index++] = new Card("Draw two","Green");
            cards[index++] = new Card("Draw two","Blue");
            cards[index++] = new Card("Draw two","Yellow");

        }
        for (int i = 1; i <= 2; i++) {
            cards[index++] = new Card("Reverse","Red");
            cards[index++] = new Card("Reverse","Green");
            cards[index++] = new Card("Reverse","Blue");
            cards[index++] = new Card("Reverse","Yellow");
        }
        for (int i = 1; i <= 2; i++) {
            cards[index++] = new Card("Skip","Red");
            cards[index++] = new Card("Skip","Green");
            cards[index++] = new Card("Skip","Blue");
            cards[index++] = new Card("Skip","Yellow");

        }
        for (int i = 1; i <= 4; i++) {
            cards[index++] = new Card("Wild","Wild");
        }

        for (int i = 1; i <= 4; i++) {
            cards[index++] = new Card("Wild Draw Four","Wild");
        }
    }
    public void shuffleCards(){
        Random r = new Random();

        for (int i = cards.length-1; i > 0; i--) {

            int j = r.nextInt(i+1);

            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }
    public void insertInStack(){
        shuffleCards();
        for (Card card : cards) {
            sc.push(card);
        }
    }
    public Card DrawCard(){
        if(sc.getSize()!=0){
            return sc.pop();
        }
        else{
            if(placedCards.getSize()!=0)
             return placedCards.pop();
            else
              throw new RuntimeException("No cards available forced to stop");
        }
    }
    public void discardCard(Card placed){

        if (lastCard !=null && !placed.CheckValidityOfCards(placed)) {
            throw new Error ("failed: illegal card to place down.");
        }
        placedCards.push(placed);
        lastCard = placed;
    }
    public Card ThrowCard(String card){
       return sc.ThrowCard(card);
    }
    public Card LastCard(){
        return lastCard;
    }

    public String printBothDecks(){
        return "deck: " + sc.toString() + "\n discard: " + placedCards.toString();
    }

}
