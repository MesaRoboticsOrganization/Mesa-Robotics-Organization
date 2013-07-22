__author__ = 'Charles Bayley'

import os
import time

duty_dir = '/sys/devices/ocp.2/P9_14.14/duty'

while True:
  os.system('echo ' + str(0) + ' > ' + duty_dir)
