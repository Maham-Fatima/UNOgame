import java.util.Objects;

public class DoublyCircularLinkedListForTurns {
    private int size = 0;
    private competitor head = null;
    private competitor tail = null;
    public void AddInGame(competitor p){
        if (head == null){
            head = p;
        }else {
            tail.setSucceeding(p);
            head.setPreceding(p);
            p.setSucceeding(head);// next because it is circular
            p.setPreceding(tail);// previous
        }
        tail = p;
        size ++;
    }
    public competitor getHead(){
        return head;
    }
    public void removePlayer(competitor p) {
        competitor current = head;
        boolean check = false;
        for (int i=0; i< size; i++){
            if (Objects.equals(current.competitorname(), p.competitorname())){
                current.getPreceding().setSucceeding(current.getSucceeding());
                current.getSucceeding().setPreceding(current.getPreceding());
                this.size --;
                check = true;
                break;
            }
            current = current.getSucceeding();
        }
        if (!check) {
            throw new Error ("failed: player to kik out not exist.");
        }
    }


    public competitor getFirstPlayer() {
        return head;
    }

    public int getSize(){
        return this.size;
    }

    public String playerturn() {
        competitor player =head;
        String pl = "";
        for (int i=0; i < this.size; i++){
            pl += player.toString()+"\n";
            player= player.getSucceeding();
        }
        return pl;
    }
}
