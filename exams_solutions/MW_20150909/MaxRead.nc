module MaxRead{
    provides interface Read<uint16_t>;
    uses interface Read<uint16_t> as Read;
    uses interface Timer<TMilli> as Timer;
}
implementation{
    uint16_t maxRead;
    uint16_t nReads;
    command error_t read(){
        maxRead = 0;
        nReads = 0;
        call Timer.startPeriodic(20);
    }
    event void fired(){
        call Read.read();
        nReads++; // Assume we don't care if reads are successful or not
        if(nReads == 10){
            call Timer.stop();
        }
    }
    event void readDone(error_t err, uint16_t val){
        if(err == SUCCESS && val > maxRead){
                maxRead = val;
        }
        if(nReads == 10){
            signal readDone(SUCCESS, maxRead);
        }
    }
}
