#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>
#include <time.h>

int main(void) {
	struct timespec start, end;

	clock_gettime(CLOCK_REALTIME, &start);

  printf("Hello world!\n");

	clock_gettime(CLOCK_REALTIME, &end);
	long diff = 1000000000*(end.tv_sec-start.tv_sec) + end.tv_nsec-start.tv_nsec;
	printf("%lu ns\n", diff);

	return EXIT_SUCCESS;
}
