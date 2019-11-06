#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

const int processCount = 10;

int main(void) {
  printf("Fork-join (%d worker processes)\n", processCount);

  /* Hinweise zur Datei-Ein-Ausgabe:
   *
   * FILE-Struct aus Filehandle ("r" zum Lesen, "w" zum Schreiben)
   * FILE* fp = fdopen(file_handle, "r");
   *
   * Zahl in FILE schreiben
   * fprintf(fp, "%d\n", zahl);
   *
   * FILE Stream flushen
   * fflush(fp);
   *
   * Zahl aus FILE lesen
   * int zahl;
   * fscanf(fp, "%d", &zahl);
   *
   * FILE schließen
   * fclose(fp);
   */

	return EXIT_SUCCESS;
}
