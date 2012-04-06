/**
 * File Name: ImagePanel.java
 *
 * Author: Kiet Lam
 *
 * Purpose: JPanel that contains an image
 *          Will automagically scale the image to
 *          fit the panel
 *
 * TODO: Find a better way to scale the image?
 *
 * Date Modified: 4/05/2012
 *
 */


import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import javax.swing.*;


public class ImagePanel extends JPanel
{
    final int xStepRate = 10;
    final int yStepRate = 10;

    private BufferedImage foregroundImage;
    private BufferedImage backgroundImage;
    private BufferedImage finalImage;
    private int           foregroundX;
    private int           foregroundY;
    private int           foregroundW;
    private int           foregroundH;


    public ImagePanel()
    {
        backgroundImage = null;
        foregroundImage = null;
        finalImage      = null;
        foregroundX     = 50;
        foregroundY     = 100;
        foregroundW     = 480;
        foregroundH     = 240;
    }

    public void setBackgroundImage(BufferedImage _backgroundImage)
    {
        backgroundImage = resizeImage(_backgroundImage, getWidth(), getHeight());
        process();
    }

    public BufferedImage getBackgroundImage()
    {
        return backgroundImage;
    }

    public void setForegroundImage(BufferedImage _foregroundImage)
    {
        foregroundImage = resizeImage(_foregroundImage, foregroundW, foregroundH);
        process();
    }

    public BufferedImage getForegroundImage()
    {
        return foregroundImage;
    }

    public void process()
    {
        if (backgroundImage == null || foregroundImage == null)
        {
            return;
        }

        int[][] backPixel = getRGBMatrix(backgroundImage);
        int[][] forePixel = getRGBMatrix(foregroundImage);

        int[] finalPixels = new int[backPixel.length * backPixel[0].length];

        for (int i = 0; i < backPixel.length; ++i)
        {
            for (int ii = 0; ii < backPixel[0].length; ++ii)
            {
                finalPixels[ii * backPixel.length + i] = backPixel[i][ii];
            }
        }

        finalImage = new BufferedImage(backPixel.length, backPixel[0].length,
                                       BufferedImage.TYPE_INT_ARGB);

        WritableRaster raster = finalImage.getRaster();

        int[] chooseMe = backgroundImage.getRGB(0, 0,
                                                backgroundImage.getWidth(),
                                                backgroundImage.getHeight(),
                                                finalPixels, 0, 0);

        finalImage.setRGB(0, 0, backPixel.length, backPixel[0].length, chooseMe, 0, 0);
        repaint();
    }


    private BufferedImage resizeImage(BufferedImage _image, int w, int h)
    {
        BufferedImage finalImage = null;

        int imageW   = _image.getWidth();
        int imageH   = _image.getHeight();
        finalImage   = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = finalImage.createGraphics();

        g.drawImage(_image, 0, 0, w, h, null);
        g.dispose();

        return finalImage;
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

    public void paintComponent(Graphics g)
    {
        g.drawImage(finalImage, 0, 0, null);
    }
}