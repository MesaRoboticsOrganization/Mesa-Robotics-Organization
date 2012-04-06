/**
 * File Name: ImagePanel.java
 *
 * Author: Kiet Lam
 *
 * Date Modified: 4/05/2012
 *
 */


import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;


public class ImagePanel extends JPanel implements KeyListener
{
    final int xStepRate = 10;
    final int yStepRate = 10;

    private Picture originalForegroundImage;
    private Picture foregroundImage;
    private Picture backgroundImage;
    private Picture finalImage;
    private int     foregroundX;
    private int     foregroundY;
    private int     foregroundW;
    private int     foregroundH;
    private int     initForegroundW;
    private int     initForegroundH;


    public ImagePanel()
    {
        originalForegroundImage = null;

        backgroundImage = null;
        foregroundImage = null;
        finalImage      = null;
        foregroundX     = 120;
        foregroundY     = 120;
        foregroundW     = 240;
        foregroundH     = 120;
        initForegroundW = foregroundW;
        initForegroundH = foregroundH;
    }

    public void setBackgroundImage(Picture _backgroundImage)
    {
        backgroundImage = resizeImage(_backgroundImage, getWidth(), getHeight());
        process();
    }

    public Picture getBackgroundImage()
    {
        return backgroundImage;
    }

    public void setForegroundImage(Picture _foregroundImage)
    {
        originalForegroundImage = _foregroundImage;
        foregroundImage = resizeImage(originalForegroundImage, foregroundW, foregroundH);
        process();
    }

    public Picture getForegroundImage()
    {
        return foregroundImage;
    }

    public void adjustForegroundWidth(int w)
    {
        if (foregroundImage == null)
        {
            return;
        }

        if (foregroundX + initForegroundW + w > backgroundImage.getWidth())
        {
            return;
        }

        foregroundW = initForegroundW + w;

        foregroundImage = resizeImage(originalForegroundImage, foregroundW, foregroundH);
    }

    public void adjustForegroundHeight(int h)
    {
        if (foregroundImage == null)
        {
            return;
        }

        if (foregroundY + initForegroundH + h > backgroundImage.getHeight())
        {
            return;
        }

        foregroundH = initForegroundH + h;

        foregroundImage = resizeImage(originalForegroundImage, foregroundW, foregroundH);
    }

    public void process()
    {
        if (foregroundImage == null)
        {
            if (backgroundImage == null)
            {
                return;
            }
            else
            {
                finalImage = backgroundImage;
                repaint();
                return;
            }
        }

        Pixel[][] backPixel = getPixelArray(backgroundImage);
        Pixel[][] forePixel = getPixelArray(foregroundImage);

        for (int i = foregroundX, j = 0; i < foregroundW + foregroundX; ++i, ++j)
        {
            for (int ii = foregroundY, jj = 0; ii < foregroundH + foregroundY; ++ii, ++jj)
            {
                Pixel pix = forePixel[j][jj];

                if (!(pix.getRed() + pix.getBlue() < pix.getGreen()))
                {
                    backPixel[i][ii] = forePixel[j][jj];
                }
            }
        }

        Picture tempImage = new Picture(backgroundImage.getWidth(), backgroundImage.getHeight());

        Pixel[] finalPixels = tempImage.getPixels();

        for (int i = 0; i < backPixel.length; ++i)
        {
            for (int ii = 0; ii < backPixel[0].length; ++ii)
            {
                finalPixels[ii * backPixel.length + i].setColor(backPixel[i][ii].getColor());
            }
        }

        finalImage = tempImage;

        repaint();
    }

    private Picture resizeImage(Picture _image, int w, int h)
    {
        int imageW   = _image.getWidth();
        int imageH   = _image.getHeight();

        return _image.scale(((double) w) / imageW, ((double) h) / imageH);
    }

    private int[][] getRGBMatrix(BufferedImage _image)
    {
        int w = _image.getWidth();
        int h = _image.getHeight();

        int[][] arInt = new int[w][h];

        for (int i = 0; i < w; ++i)
        {
            for (int ii = 0; ii < h; ++ii)
            {
                arInt[i][ii] = _image.getRGB(i, ii);
            }
        }

        return arInt;
    }

    private Pixel[][] getPixelArray(Picture _image)
    {
        int w = _image.getWidth();
        int h = _image.getHeight();

        Pixel[][] arPixel = new Pixel[w][h];

        for (int i = 0; i < w; ++i)
        {
            for (int ii = 0; ii < h; ++ii)
            {
                arPixel[i][ii] = _image.getPixel(i, ii);
            }
        }

        return arPixel;
    }

    public void paintComponent(Graphics g)
    {
        if (finalImage != null)
        {
            g.drawImage(finalImage.getBufferedImage(), 0, 0, null);
        }
    }

    public void keyPressed(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
        case KeyEvent.VK_UP:
            if (foregroundY > yStepRate)
            {
                foregroundY -= yStepRate;
            }
            break;
        case KeyEvent.VK_DOWN:
            if (foregroundY + foregroundH + yStepRate < backgroundImage.getHeight())
            {
                foregroundY += yStepRate;
            }
            break;
        case KeyEvent.VK_RIGHT:
            if (foregroundX + foregroundW + xStepRate < backgroundImage.getWidth())
            {
                foregroundX += xStepRate;
            }
            break;
        case KeyEvent.VK_LEFT:
            if (foregroundX > xStepRate)
            {
                foregroundX -= xStepRate;
            }
            break;
        }

        process();
    }

    public void keyReleased(KeyEvent e)
    {

    }

    public void keyTyped(KeyEvent e)
    {

    }
}