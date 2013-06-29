#include <iostream>
#include <sstream>
#include <string>
#include <stdlib.h>
using namespace std;

void callScript(string script, string flag1="") {
  stringstream strs;
  strs << script << " " << flag1;
  string result = strs.str();
  system(result);
}

void ssTest() {
  stringstream ss;
}

int main() {
  string script = "sh shell.sh";
  string flag1 = "Charlie";
  callScript(script, flag1);
  ssTest();
  return 0;
}
