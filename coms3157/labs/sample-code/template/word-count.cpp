#include <map>
#include <string>
#include <iostream>

using namespace std;

int main()
{
    map<string,int>  word_count;
    string word;
    while (cin >> word)
	word_count[word]++;

    // The use of "auto" and range-based for-loop may require 
    // "-std=c++11" or "-std=c++0x" flag if you're using an older
    // version of g++ or clang++ compiler.

    for (const auto& entry : word_count) {
	cout << entry.first << " occurs ";
	cout << entry.second << " times." << endl;
    }
    return 0;
}
