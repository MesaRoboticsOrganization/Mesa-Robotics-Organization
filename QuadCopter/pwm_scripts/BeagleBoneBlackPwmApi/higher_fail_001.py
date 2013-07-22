__author__ = 'Charles Bayley'

import pwm

pwm = pwm.set_pin

# The pin that this motor is linked too.
# A value of -1 that this motor is inactive.
class Pin_Numbers:
    #
    do_not_run_code = -1
    #
    status_code = 0
    pin_set_code = 1
    pin_code = 2
    # Pins
    Pins = [
            upper_left_pin = [-1, -1, -1],
            upper_right_pin = [-1, -1, -1],
            lower_left_pin = [-1, -1, -1],
            lower_right_pin = [-1, -1, -1],
            middle_left_pin = [-1, -1, -1],
            middle_right_pin = [-1, -1, -1]  
            ]

# All inputs should be floating values 
#   between -100.0f 0.0f (off) and 100.0f (full power)
def set_motors(upper_left, upper_right, lower_left, lower_right, middle_left=0.0, middle_right=0.0)
    print("placeholder")
