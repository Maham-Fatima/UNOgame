public class competitor {
    private String competitorname;
    private  competitor succeeding=null;
    private competitor preceding=null;
    private SingleLinkedListForCardLinking hand;

    public competitor(String competitorname){
        this.competitorname = competitorname;
        this.hand = new SingleLinkedListForCardLinking();
    }


    public void GiveCard(Card card)
    {
        hand.InsertCard(card);

    }
    public Card removeFromHand(String card){
        return hand.ThrowCard(card);
    }

    public void RemoveLast(Card card){
        hand.RemoveLast();
    }

    public boolean winner(){
        return hand.IsEmpty();
    }
    public competitor getPreceding() {
        return preceding;
    }
    public void setPreceding(competitor preceding) {
        this.preceding=preceding;
    }
    public competitor getSucceeding() {
        return succeeding;
    }
    public void setSucceeding(competitor succeeding) {
        this.succeeding=succeeding;
    }
    public String toString()
    {
        return "Player [name=" + competitorname + "]" + ", [card#=" + hand.IsEmpty() + "], " + "[prev/nex= " + this.preceding.competitorname() + "/" + this.succeeding.competitorname() + "].";
    }

    public String competitorname() {
        return competitorname;
    }

    public SingleLinkedListForCardLinking getHand() {
        return hand;
    }
    public void printCardsInHand(){
        SingleLinkedListForCardLinking sl = getHand();
        Node temp = sl.head;
        System.out.print("[");
        for (int i = 0; i < sl.size; i++) {
            System.out.print(temp.data.card+", ");
            temp = temp.next;
        }
        System.out.println("]");
    }
}
