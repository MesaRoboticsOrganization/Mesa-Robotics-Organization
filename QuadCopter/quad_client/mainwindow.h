#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT
    
public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();
    
private slots:
    void on_slider1_sliderMoved(int position);

    void on_slider2_sliderMoved(int position);

    void on_slider3_sliderMoved(int position);

    void on_slider4_sliderMoved(int position);

    void on_actionOpen_IP_triggered();

    void update(int,int);

    void send(int,int);

private:
    Ui::MainWindow *ui;
};

#endif // MAINWINDOW_H
