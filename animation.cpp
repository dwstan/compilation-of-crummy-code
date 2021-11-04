#include <iostream>
#ifdef _WIN32
#include <Windows.h>
#else
#include <unistd.h>
#endif
using namespace std;

// ඞ Animates a ඞ walking side to side!!!!! use it as a loading bar or something?
int main()
{
    int index = 0;
    int inc = 1;
    while (true)
    {
        usleep(500000);
        for (int i = 0; i < 10; i++)
        {
            cout << '\b' << flush;
        }
        for (int i = 0; i < 10; i++)
        {
            if (i == index)
            {
                cout << "ඞ"; // ඞ
            }
            else
            {
                cout << "-";
            }
        }
        cout << flush;
        index += inc;
        if (index >= 9 || index < 1)
        {
            inc = -inc;
        }
    }
}