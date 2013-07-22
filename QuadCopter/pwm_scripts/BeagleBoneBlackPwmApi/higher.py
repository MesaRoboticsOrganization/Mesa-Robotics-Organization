__author__ = 'Charles Bayley'

import pwm

pwm_pin = pwm.set_pin

class Pin_Info:
    #
    code_do_not_run = -1
    #
    code_status = 0
    code_pin_set = 1
    code_pin = 2
    # Internals use the pin numbers above and the code numbers above
    pins_to_motor = [
                     []
                     ]


class Converting_Info:
    MIN = 1
    MAX = 0
    PULSE_WIDTH_TRUE_MAX = 2000000
    PULSE_WIDTH_TRUE_MIN = 1000000
    PULSE_WIDTH_MID = 0
    PULSE_WIDTH_SAFETY = 1
    pulse_width_emc_min_max = [1700000 ,1200000]


def set_motors(pins=[]):
    for i in range(0, len(pins)):
        if Pin_Info.pins_to_motor[i][Pin_Info.code_status] == Pin_Info.code_do_not_run:
            print('\tThe pin \"' + str(pins[i]) + '\" is curently set to not run...')
        else:
            pwm_pin(Pin_Info.pins_to_motor[i][Pin_Info.code_pin_set], Pin_Info.pins_to_motor[i][Pin_Info.code_pin], pins[i])
      
      
def convert_float_to_pulse_width(floating_num):
    if floating_num <= 0.0:
        return Converting_Info.pulse_width_emc_min_max[Converting_Info.MIN]
    if floating_num >= 100.0:
        return Converting_Info.pulse_width_emc_min_max[Converting_Info.MAX]
    return int(Converting_Info.pulse_width_emc_min_max[Converting_Info.MIN] + 
               ((Converting_Info.pulse_width_emc_min_max[Converting_Info.MAX] - 
                 Converting_Info.pulse_width_emc_min_max[Converting_Info.MIN]) / 100.0) *  
               floating_num)

def start(mods=[[Pin_Info.code_do_not_run, Pin_Info.code_do_not_run, Pin_Info.code_do_not_run]], min_max=[Converting_Info.pulse_width_emc_min_max[0], Converting_Info.pulse_width_emc_min_max[1]]):
    #pwm.start_device_tree()
    for i in range(0, len(mods)):
        Pin_Info.pins_to_motor[0].append(mods[i])
        print(str(Pin_Info.pins_to_motor))
    Converting_Info.pulse_width_emc_min_max = min_max

