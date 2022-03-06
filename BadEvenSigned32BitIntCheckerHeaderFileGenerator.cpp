/*
####################################################
####################################################
## warning this code will make a 90 gb h file lol ##
########## run this code at your own risk ##########
####################################################
####################################################
*/

#include <fstream>
using namespace std;

int main()
{
    ofstream fout;
    fout.open("isEven.h");

    fout << "\#ifndef IsEven_h\n"
        << "\#define IsEven_h\n\n"
        << "bool isEven(int number)\n"
        << "{\n";

    for (int count = -2147483648; count < 2147483647; count++)
    {
        if (count % 2 == 0)
        {
            fout << "if(number == " << count << ")\n"
                << "\treturn true\;\n";
        }
    }

    fout << "return false;\n";
    fout << "}\n"
        << "\#endif";

    fout.close();
    return 0;
}
