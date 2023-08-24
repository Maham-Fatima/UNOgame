public class StackOfCards {
  SingleLinkedListForCardLinking sl;
  public StackOfCards(){

      sl = new SingleLinkedListForCardLinking();
  }
  public void push(Card card){
      sl.InsertCard(card);
  }
  public Card pop(){
      if(!IsEmpty()) {
          return sl.RemoveLast();
      }
      else
       return null;
  }
  public boolean IsEmpty(){
      if(sl.IsEmpty()){
          System.out.println("Stack is empty");
          return true;
      }
      return false;
  }
  public int getSize(){

      return sl.size;
  }
  public Card ThrowCard(String card){
      return sl.ThrowCard(card);
  }

}
