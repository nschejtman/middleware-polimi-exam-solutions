public class MsgBatcher{
  private Queue<Message> batch;
  private int capacity;

  public MsgBatcher(int capacity){
    this.capacity = capacity;
  }

  public void enqueue(Message message){
    syncronized(batch){
      while(batch.lenght() == capacity){
        Thread.sleep(); // Block the caller thread until the batch is empty
      }
      batch.put(message);
    }
  }

  public void sendAll(){
    syncronized(batch){
      SendThread st = new SendThread(batch);
      st.start();
    }
  }

  private class SendThread implements Runnable{
    private Queue<Message> batch;

    public SendThread(Queue<Message> batch){
      this.batch = batch;
    }

    public void run(){
      while(!batch.isEmpty()){
          Message msg = batch.poll();
          msg.send();
      }
      batch.notifyAll();
    }
  }

}
