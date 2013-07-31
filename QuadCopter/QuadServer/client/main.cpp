#include "mainwindow.h"
#include <QApplication>
#include <SDL.h>
#include <cstdio>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    MainWindow w;
    w.show();
    SDL_Init(SDL_INIT_JOYSTICK);
    return a.exec();
}
