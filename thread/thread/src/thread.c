#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <time.h>


const int loopCount = 10000;

int main(void) {
	struct timespec start, end;

	clock_gettime(CLOCK_REALTIME, &start);
  for (int i=0; i<loopCount; ++i) {
    // ...
	}
	clock_gettime(CLOCK_REALTIME, &end);

  // Print result
	long diff = 1000000000*(end.tv_sec-start.tv_sec) + end.tv_nsec-start.tv_nsec;
	printf("%lu ns per thread creation\n", diff/loopCount);

	return EXIT_SUCCESS;
}
