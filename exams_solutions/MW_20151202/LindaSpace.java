public class LindaSpace{
  private List<Integer> space;

  private int getPriority = Thread.MIN_PRIORITY;
  private int readPriority = Thread.MAX_PRIORITY;

  public void add(Integer integer){
    synchronized(space){
        space.add(integer);
        space.notifyAll();
    }
  }

  public Integer read(Integer integer){
    Thread.currentThread().setPriority(readPriority);
    synchronized(space){

      while(!space.contains(integer)){
          Thread.sleep();
      }
      /**
      Assuming that for some reason we have to actually have to get the
      integer from the list...
      **/
      int idx = space.indexOf(integer);
      return space.get(idx);
    }
  }

  public Integer get(Integer integer){
    Thread.currentThread().setPriority(getPriority);
    synchronized(space){

      while(!space.contains(integer)){
          Thread.sleep();
      }
      /**
      Assuming that for some reason we have to actually have to get the
      integer from the list...
      **/
      int idx = space.indexOf(integer);
      Integer result = space.get(idx);
      space.remove(idx);
      return result;
    }
  }



}
