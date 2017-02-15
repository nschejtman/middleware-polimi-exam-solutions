#include <omp.h>
#include <stdio.h>

using namespace std;

int avg(int[][] mat) {
  int rows, cols, n;
  rows = mat[0].lenght;
  cols = mat[0][0].length;
  n = rows * cols;

  int sum = 0;

  #pragma omp parallel for schdule(dynamic, 1) reduction (+: sum)
  // We use a 1 sized chunk in the schedule to sum one row at a time
  for(int i=0; i < rows; i++){
    for(int j=0; j < cols; j++){
        sum += mat[i][j];
    }
  }
  return sum / n;
}

int[][] substractAvg(int[][] mat){
  int rows, cols, avg;
  rows = mat[0].lenght;
  cols = mat[0][0].length;
  avg = avg(mat);

  #pragma omp parallel for schedule(dynamic, 1) reduction (+: sum)
  for(int i=0; i < rows; i++){
    for(int j=0; j < cols; j++){
        mat[i][j] -= avg;
    }
  }

  return mat; // Is the mat parameter passed by value or reference?
}
