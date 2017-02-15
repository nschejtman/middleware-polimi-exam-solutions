interface Counter{
  command void incrementCounter();
  command void resetCounter();
  command void enableAutoReset();
  command void disableAutoReset();
  event void hundredthCIncrease(uint32_t counter);
}

module Counter{
  provides interface Counter;
  uses interface Timer<TMilli> as Timer;
}

implementation{
  uint32_t counter;

  // Timer controls

  command enableAutoReset(){
    call Timer.startPeriodic(10000);
  }

  command disableAutoReset(){
    call Timer.stop();
  }

  event void Timer.fired(){
    call resetCounter();
  }

  // Counter controls

  command void incrementCounter(){
    counter++;
    if(counter % 100 == 0){
      signal hundredthCIncrease(counter);
    }
  }

  command void resetCounter(){
    counter = 0;
  }

}
