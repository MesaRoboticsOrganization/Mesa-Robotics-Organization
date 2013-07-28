#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <cstdio>
#include <QInputDialog>
#include <QtNetwork/QTcpSocket>
#include <QByteArray>

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
    send(s,v);
}
void MainWindow::send(int s,int v) {
    union {
        char dat[8];
        int vals[2];
    }   info;
    info.vals[0]=s;
    info.vals[1]=v;
    printf("%d: %d\n",s,v);
    if(sock) {
        sock->write(info.dat,8);
    }
}

void MainWindow::on_slider1_sliderMoved(int position)
{
    update(0,position);
}

void MainWindow::on_slider2_sliderMoved(int position)
{
    update(1,position);
}

void MainWindow::on_slider3_sliderMoved(int position)
{
    update(2,position);
}

void MainWindow::on_slider4_sliderMoved(int position)
{
    update(3,position);
}

void MainWindow::on_actionOpen_IP_triggered()
{
    QString ip = QInputDialog::getText(this,"Gimme Ur IP","asdf");
    if(sock) {
        delete sock;
    }
    sock = new QTcpSocket();
    sock->connectToHost(ip,1337);
}
