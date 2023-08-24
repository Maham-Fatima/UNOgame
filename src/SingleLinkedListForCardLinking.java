public class SingleLinkedListForCardLinking {
    Node head;
    int size;

    public SingleLinkedListForCardLinking(){

        size =0;
    }
    public void InsertCard(Card card){
        Node n = new Node(card);
        if(head!=null){
            n.next = head;
        }
        head = n;
        size++;
    }
    public Card RemoveLast(){
        Card toReturn = head.data;
        head = head.next;
        size--;
        return toReturn;
    }

    public Card ThrowCard(String card){
        Node temp= head;
        Card toReturn=null;
        Node pre = null;
        for (int i = 0; temp != null; i++) {
            if(temp.data.card.equals(card)){
                toReturn = temp.data;
                if(pre == null){
                    head = head.next;
                }else {
                    pre.next = temp.next;
                }
                size--;
                break;
            }
            pre = temp;
            temp = temp.next;
        }
        return toReturn;
    }
    public Node peek(){
        return head;
    }
    public boolean IsEmpty(){
        return size==0;
    }
    public int getSize(){
        return size;
    }


}
