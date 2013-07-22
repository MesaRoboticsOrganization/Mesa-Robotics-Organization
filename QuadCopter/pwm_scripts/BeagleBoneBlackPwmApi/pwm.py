import os

duty_dir = '/sys/devices/ocp.2/P9_14.14/duty'

test = False

def set_pin(pin_set, pin, pulse_width):
    if test is True:
        return
    os.system('echo ' + str(pulse_width) + ' > /sys/devices/ocp.2/P' + str(pin_set) + '_' + str(pin) + '.*/duty')
  
def start_device_tree():
    os.system('echo MULTC > /sys/devices/bone_capemgr.8/slots')
