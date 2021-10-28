/**
 * Created an implementation of sleepsort inside of C mostly because sleepsort is kinda funny. 
 * And also it was a great fun way to learn a bit about threads in C!
 * 
 * Sleepsort is a sorting algorithm that takes in a list and prints the list sorted!
 * Creates a thread to sleep by the value in the list. It would then print the value afterwards,
 * meaning smaller numbers will finish sleeping earlier making it print earlier!
 * 
 * Limited the largest value to 10 so it won't take too long to sort.
 * 
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>

void *sleepThenPrint(void *arg)
{
    // sleep by the list value then print it!
    int *n = (int *)arg;
    sleep(*n);
    printf("%d ", *n);
}

void sort(int *list, const int size)
{
    pthread_t threads[size];
    for (int i = 0; i < size; i++)
    {
        // create thread to sleep
        int t = pthread_create(&*(threads + i), NULL, sleepThenPrint, (void *)&*(list + i));
        if (t) // safety in case thread creation fails
        {
            printf("Error: thread creation failed, %d\n", t);
            exit(-1);
        }
    }
    pthread_exit(NULL);
}

int main()
{
    const int size = 10;
    int list[size];

    time_t t;
    srand((unsigned)time(&t));

    for (int i = 0; i < 10; i++)
    {
        list[i] = rand() % 10;
        printf("%d ", list[i]);
    }
    printf("\n");

    sort(list, size);
}

// gcc sleepsort.c -pthread (had trouble remembering to add -pthread to compile it)
