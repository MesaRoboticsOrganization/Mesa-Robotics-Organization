__author__ = 'Charles Bayley'

import BeagleBoneBlackPwmApi as bbb
import time

time.sleep(5)

bbb.higher.start([
      [0, 9, 14]
    ])

change = 1000
low = 1200000
high = 1700000
for i in range(low / change, high / change):
    os.system('echo ' + str(i * change) + ' > ' + duty_dir)
for i in reversed(range(low / change, high / change)):
    os.system('echo ' + str(i * change) + ' > ' + duty_dir)
time.sleep(2)    
os.system('echo ' + str(0) + ' > ' + duty_dir)

