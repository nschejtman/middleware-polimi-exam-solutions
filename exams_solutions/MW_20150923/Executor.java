public class Executor{
  /**
    Keep the runner in a variable if we want to start/stop the executor thread
    multiple times
  **/
  Runnable runnable;
  Boolean stopSignal;

  public Executor(Runnable runnable){
    this.runnable = runnable;
    stopSignal = false;
    start();
  }

  public void start(){
    ExecutorThread th = new ExecutorThread(runnable);
    th.start();
  }

  private void stop(){
    synchronized(stopSignal){
      stopSignal = true;
      stopSignal.notifyAll();
    }
  }

  /**
   Want to keep execution thread separate from the thread that creates the
   executor because otherwise when we are infinitely looping, the creation
   thread will block upon the creation of the executor object and we would not
   be able to call the stop method!
  **/

  private class ExecutorThread extends Thread{
    Runnable runnable;
    Boolean stopSignal;

    public ExecutorThread(Runnable runnable, Boolean stopSignal){
      this.runnable = runnable;
    }

    public void run(){
      // TODO: check. stopSignal in Executor and ExecutorThread should
      // reference the same object so mutexes shoud work just fine...
      synchronized(stopSignal){
        while(!stopSignal){
          Thread t = new Thread(runnable);
          t.start();
          Thread.sleep(1000);
          stopSignal.notifyAll();
        }
      }
    } //End run method

  } //End private class

}
