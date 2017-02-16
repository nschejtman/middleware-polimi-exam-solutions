#import <omp.h>
#define NUM_THREADS 4
using namespace std;

int hammingDistance(int[] data1, int[] data2, int NUM){
  int chunkSize = NUM/NUM_THREADS;
  int distance = 0;
  #pragma omp parallel num_threads(NUM_THREADS) for(dynamic, chunkSize) reduction(+: distance)
    for(int i = 0; i<NUM; i++){
      if(data1[i] != data2[i]) distance++;
    }
}
