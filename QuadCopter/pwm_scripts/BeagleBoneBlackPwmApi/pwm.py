__author__ = 'Charles Bayley'

import os

duty_dir = '/sys/devices/ocp.2/P9_14.14/duty'
warning = '\tWARNING! Pin not mapped!'

test = False

def set_pin(pin_set, pin, pulse_width):
    if test is True:
        return
    command = ('echo ' + str(pulse_width) + ' > /sys/devices/ocp.2/P' + str(pin_set) + '_' + str(pin) + '.')
    true_pin = '*'
    if pin_set == 8:
        if pin == 13:
            true_pin = 18
        elif pin == 19:
            true_pin = 19
        else:
            print warning
    elif pin_set == 9:
        if pin == 14:
            true_pin = 14
        elif pin == 16:
            true_pin = 15
        elif pin == 21:
            true_pin = 16
        elif pin == 22:
            true_pin = 17
        else:
            print warning
    else:
        print warning
    os.system(command + str(str(true_pin) + '/duty'))
        
            
def start_device_tree():
    os.system('echo MULTC > /sys/devices/bone_capemgr.8/slots')
