/*
  Is the given number even?
  O(1) time/space solution using map
*/

#include <iostream>
#include <limits>
#include <map>

using namespace std;

const int INT_MAX = numeric_limits<int>::max();
bool map_initialized = false;
map<int, bool> evenness;

// Initialize the map
void init_map() {
  if (!map_initialized) {
    bool even = true;
    for (int i = 0; i <= INT_MAX; i++) {
      evenness.insert(pair<int, bool>(i, even));
      even = !even;
    }
    map_initialized = true;
  }
}

// Is the given number even?
bool isEven(int num) {
  init_map();
  if (num < 0) {
    num = num * -1;
  }
  return evenness.at(num);
}