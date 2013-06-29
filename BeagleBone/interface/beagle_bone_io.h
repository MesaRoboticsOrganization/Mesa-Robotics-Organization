/**
 * beagle_bone_io.h
 * - TODO:DescriptionOfFile
 *
 * @author
 * - Charles Bayley, -VP -CTO
 * @created
 * - June 28, 2013
 */

/*
 TODO:List
 
 TODO: Add constructor for bb_pwm
  - debugStatus
  

  
 */

#ifndef BEAGLE_BONE_IO_H_
#define BEAGLE_BONE_IO_H_

#include <iostream>
#include <cstdlib>
#include <stdlib.h>
using namespace std;

namespace beagle_bone_io {
  
  class bb_pwm {
    public:
      
      enum Return_Status {
        FAIL = -1,
        SUCCESS = 0   
      };
      
      //double ABSOLUTE_MIN_PULSE_WIDTH = 1.0d;
      //double 
      bb_pwm(min_pulse_width, max_pulse_width);
      
      /**
       * @values
       * - pin
       *   - The pin that is being set. on P9
       * - period_timer
       *   - TODO
       */
      void set_period_timer(int pin, int period_timer);
      
    protected:
      
      
    private:
      
      
  };
  
}
#endif

