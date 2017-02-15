interface PeriodicReader{
  command void start(uint32_t dt);
  command void stop();
  event void readDone(uint16_t val);
}
module PeriodicReader{
  provides interface PeriodicReader;
  uses interface Timer<TMilli> as Timer;
  uses interface Read<uint16_t> as Read;
}
implementation{
  command void start(uint32_t dt){
    call Timer.startPeriodic(dt);
  }
  event void Timer.fired(){
    call Read.read();
  }
  command void stop(){
    call Timer.stop();
  }
  event void readDone(error_t err, uint16_t val){
    // Assume I only signal the event in case there was no error
    if(err == SUCCESS){
      signal readDone(val);
    }
  }

}
