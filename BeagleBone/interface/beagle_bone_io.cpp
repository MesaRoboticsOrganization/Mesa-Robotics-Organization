#include "beagle_bone_io.h"

#include <iostream>
#include <string>
#include <sstring>
#include <stdlib.h>
#include <string.h>
using namespace std;

namespace beagle_bone_io {
  
  beagle_bone_io::set_period_timer(int pin, int period_timer) {
    string script = "sh activate_pin.sh ";
    char *script_frags = new char[script.size()+1];
    a[s.size()] = 0;
    memcpy(script_frags, script.c_str(),script.size());
    
    
    
    system(script);
  }
  
}
