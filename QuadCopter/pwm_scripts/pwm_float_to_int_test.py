__author__ = 'Charles Bayley'

import BeagleBoneBlackPwmApi as bbb

bbb.higher.start([[0, 9, 14],
                 [0, 9, 16],
                 [0, 9, 21],
                 [0, 9, 22],
                 [0, 8, 13],
                 [0, 8, 19]])

for i in range(0, 1001):
        temp = bbb.higher.convert_float_to_pulse_width(float(i / 10.0))
        print(str(float(i / 10.0)) + '\t=>\t' + str(temp))
        bbb.higher.set_motors([temp, temp, temp, temp, temp, temp])
for i in reversed(range(0, 1001)):
        temp = bbb.higher.convert_float_to_pulse_width(float(i / 10.0))
        print(str(float(i / 10.0)) + '\t=>\t' + str(temp))
        bbb.higher.set_motors([temp, temp, temp, temp, temp, temp])