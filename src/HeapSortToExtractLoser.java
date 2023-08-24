public class HeapSortToExtractLoser {
    int size;
    competitor[] array;
    DoublyCircularLinkedListForTurns player;
    public HeapSortToExtractLoser(DoublyCircularLinkedListForTurns player){
        this.player = player;
        //only 4 players will play the game so players in game will be store by min
        size = Math.min(player.getSize(), 4);
        array = new competitor[player.getSize()];
    }
    public void BuildMaxHeap(){
        competitor temp = player.getFirstPlayer();
        for (int i = 0; i < size; i++) {
            array[i] = temp;
            temp = temp.getSucceeding();
        }
        for (int i = (size/2) - 1; i >= 0 ; i--) {
             Heapify(size-1, i);
        }
    }
    public void Heapify(int size,int index){
        int largest = index;
        int L = (index * 2)+ 1;
        int R = (index * 2)+ 2;
        if(L < size && array[largest].getHand().getSize() < array[L].getHand().getSize()){
            largest = L;
        }
        if(R < size && array[largest].getHand().getSize() < array[R].getHand().getSize()){
            largest = R;
        }
        if(index != largest){
            competitor swap;

           competitor temp = array[largest];
            array[largest] = array[index];
            array[index] = temp;
            Heapify(size, largest );
        }
    }
    public competitor ExtractMaxCard(){
        return array[0];
    }
    public void HeapSort(){
        for (int i = size- 1; i > 0; i--) {
            competitor temp=array[0] ;
            array[0] = array[i];
            array[i] = temp;
            Heapify(i,0);
        }
    }
    public void DisplayScoreCard(){
        HeapSort();
        System.out.println("Display Score card:");
        System.out.println("No  Player name   Cards Remaining");
        for (int i = 0; i < size; i++) {
            System.out.println((i+1)+"   "+array[i].competitorname()+"              "+array[i].getHand().getSize());
        }
    }

}