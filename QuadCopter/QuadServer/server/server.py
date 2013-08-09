import socket
import pwm

srvsock = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
srvsock.bind((socket.gethostname(), 1337))
srvsock.listen(1)
motors=[]

for m in range(0,3):
  motors.append(pwm.signal(m))

while 1:
  for m in range(0,3):
    motors[m].set_width(1000000)
  (sock,addr)=srvsock.accept()
  while 1:
    pkt=sock.recv(8)
    if not pkt:
      break
    (motor,value)=struct.unpack_from('ii',pkt)
    motors[motor].set_width(100000*value+1000000)
