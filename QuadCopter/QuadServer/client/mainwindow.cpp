#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <cstdio>
#include <QInputDialog>
#include <QtNetwork/QTcpSocket>
#include <QByteArray>
#include <SDL.h>
#include <QTimer>

SDL_Joystick *js;
int t_fix=1;

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
}

MainWindow::~MainWindow()
{
    delete ui;
}
int vals[4]={0,0,0,0};
QTcpSocket *sock;

void MainWindow::update(int s,int v) {
    while(vals[s]<v) {
        vals[s]++;
        send(s,vals[s]);
    }
    while(vals[s]>v) {
        vals[s]--;
        send(s,vals[s]);
    }
    //send(s,v);
}
void MainWindow::send(int s,int v) {
    union {
        char dat[8];
        int vals[2];
    } info;
    info.vals[0]=s;
    info.vals[1]=v;
    printf("%d: %d\n",s,v);
    if(sock) {
        sock->write(info.dat,8);
    }
}

void MainWindow::on_slider1_valueChanged(int position)
{
    update(0,position);
}

void MainWindow::on_slider2_valueChanged(int position)
{
    update(1,position);
}

void MainWindow::on_slider3_valueChanged(int position)
{
    update(2,position);
}

void MainWindow::on_slider4_valueChanged(int position)
{
    update(3,position);
}
QTimer *timer;
void MainWindow::on_actionOpen_IP_triggered()
{
    QString ip = QInputDialog::getText(this,"Gimme Ur IP","where is robot");
    if(sock) {
        delete sock;
    }
    if(timer) {
        delete timer;
    }
    sock = new QTcpSocket();
    sock->connectToHost(ip,1337);
    ui->slider1->setValue(0);
    ui->slider2->setValue(0);
    ui->slider3->setValue(0);
    ui->slider4->setValue(0);
    js = SDL_JoystickOpen(0);
    timer = new QTimer(this);
    connect(timer,SIGNAL(timeout()),this,SLOT(timerEvent()));
    timer->start(10);
    t_fix=1;
}

void MainWindow::on_stopbutton_pressed()
{
    ui->slider1->setValue(0);
    ui->slider2->setValue(0);
    ui->slider3->setValue(0);
    ui->slider4->setValue(0);
}

void MainWindow::timerEvent() {
    //fl,fr,rl,rr
    int i;
    SDL_JoystickUpdate();
    int thrust[4]={0,0,0,0};
    short throttle = SDL_JoystickGetAxis(js,3);
    short rotate = SDL_JoystickGetAxis(js,2);
    short forward = SDL_JoystickGetAxis(js,1);
    short strafe = SDL_JoystickGetAxis(js,0);
    int long_throttle = 0x7FFF-throttle;
    int long_strafe = strafe;
    int long_forward = 0-forward;
    int long_rotate = 0-rotate;
    long_throttle/=818;
    long_strafe/=6550;
    long_forward/=6550;
    long_rotate/=6550;
    if(t_fix&&long_throttle==40) {
        long_throttle=0;
    } else {
        t_fix=0;
    }
    for(int i=0;i<4;i++) {
        thrust[i]=long_throttle;
    }
    thrust[0]-=long_forward;
    thrust[1]-=long_forward;
    thrust[2]+=long_forward;
    thrust[3]+=long_forward;
    thrust[0]-=long_strafe;
    thrust[1]+=long_strafe;
    thrust[2]-=long_strafe;
    thrust[3]+=long_strafe;
    thrust[0]-=long_rotate;
    thrust[1]+=long_rotate;
    thrust[2]+=long_rotate;
    thrust[3]-=long_rotate;
    ui->slider1->setValue(thrust[0]);
    ui->slider2->setValue(thrust[1]);
    ui->slider3->setValue(thrust[2]);
    ui->slider4->setValue(thrust[3]);
    for(i=0;i<4;i++) {
        if(thrust[i]<0) {
            thrust[i]=0;
        }
        send(i,thrust[i]);
    }
}
