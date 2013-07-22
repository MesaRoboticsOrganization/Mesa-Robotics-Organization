import BeagleBoneBlackPwmApi as bbb

bbb.higher.start([0, 9, 14])

for i in range(0, 10001):
    temp = bbb.higher.convert_float_to_pulse_width(float(i / 100.0))
    print(str(float(i / 100.0)) + '\t=>\t' + str(temp))
    bbb.higher.set_motors([temp])

for i in reversed(range(0, 10001)):
    temp = bbb.higher.convert_float_to_pulse_width(float(i / 100.0))
    print(str(float(i / 100.0)) + '\t=>\t' + str(temp))
    bbb.higher.set_motors([temp])