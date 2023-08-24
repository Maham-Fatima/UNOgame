import java.util.Arrays;

public class Queue {
    competitor[] queue;
    private int front;
    private int rear;
    int len;
    int size = 0;
    public Queue(int size){
        assert size > 0 : size;
        queue = new competitor[size];
        front = 0;
        rear = 0;
        len = size;
    }
    public boolean isFull() {
        return rear == len;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }
    public void enqueue(competitor data) {
        if (isFull()) {
            throw new Error("failed: queue full, unable to enqueue");
        } else {
            queue[rear] = data;
            rear = (rear + 1) % len; // Circular increment
            size++;
        }
    }
    // Time complexity O(1)
    public competitor dequeue (){
        if (isEmpty()){							// check if empty queue
            throw new Error("empty queue");
        }else{
            competitor player = queue[front];
            front = front+1;
            size--;
            return player;
        }
    }
    public String toString(){
        return Arrays.toString(this.queue);
    }


}