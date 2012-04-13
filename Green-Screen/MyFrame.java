/**
 * File Name: MyFrame.java
 *
 * Author: Kiet Lam
 *
 * Purpose: Main GUI for green screen program
 *
 * Note: Generated using Netbeans IDE and manually edited
 *
 */


import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;


public class MyFrame extends javax.swing.JFrame
{

    /**
     * Creates new form MyFrame
     */
    public MyFrame()
    {
        FileChooser.setMediaPath(".");

        setTitle("Mesa Robotics Organization - Green Screen");
        initComponents();
        initCustom();

        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        jPanel6 = new javax.swing.JPanel();
        picturePanel = new ImagePanel();
        backgroundButton = new javax.swing.JButton();
        foregroundButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();
        widthSlider = new javax.swing.JSlider();
        heightSlider = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bothSlider = new javax.swing.JSlider();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout picturePanelLayout = new javax.swing.GroupLayout(picturePanel);
        picturePanel.setLayout(picturePanelLayout);
        picturePanelLayout.setHorizontalGroup(
            picturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        picturePanelLayout.setVerticalGroup(
            picturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 529, Short.MAX_VALUE)
        );

        backgroundButton.setText("Load Background");

        foregroundButton.setText("Load Foreground");

        saveButton.setText("Save Picture");

        quitButton.setText("Quit");

        widthSlider.setMaximum(650);
        widthSlider.setMinimum(-200);
        widthSlider.setToolTipText("");

        heightSlider.setMaximum(400);
        heightSlider.setMinimum(-90);

        jLabel1.setText("Width");

        jLabel2.setText("Height");

        bothSlider.setMaximum(400);
        bothSlider.setMinimum(-90);

        jLabel4.setText("Both");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(backgroundButton)
                    .addComponent(widthSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(heightSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(50, 50, 50)
                        .addComponent(bothSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(foregroundButton)
                        .addGap(84, 84, 84)
                        .addComponent(saveButton)
                        .addGap(75, 75, 75)
                        .addComponent(quitButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(106, Short.MAX_VALUE))
            .addComponent(picturePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(picturePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(widthSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(heightSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bothSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(foregroundButton)
                            .addComponent(backgroundButton)
                            .addComponent(saveButton)
                            .addComponent(quitButton)))
                    .addComponent(jLabel4))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void initCustom()
    {
        foregroundButton.addActionListener(new ForegroundListener(picturePanel));
        backgroundButton.addActionListener(new BackgroundListener(picturePanel));
        saveButton.addActionListener(new SaveListener(picturePanel));
        quitButton.addActionListener(new QuitListener());

        picturePanel.setFocusable(true);
        picturePanel.addKeyListener(picturePanel);
        picturePanel.addMouseListener(picturePanel);
        picturePanel.addMouseMotionListener(picturePanel);

        widthSlider.addChangeListener(new WidthListenter(picturePanel));
        heightSlider.addChangeListener(new HeightListenter(picturePanel));
        bothSlider.addChangeListener(new BothListener(picturePanel));

        widthSlider.setValue(0);
        heightSlider.setValue(0);
        bothSlider.setValue(0);

        widthSlider.setFocusable(false);
        heightSlider.setFocusable(false);
        bothSlider.setFocusable(false);

        picturePanel.setVisible(true);

        pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(MyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(MyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(MyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable()
            {
                public void run()
                {
                    new MyFrame().setVisible(true);
                }
            });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton foregroundButton;
    private javax.swing.JButton backgroundButton;
    private javax.swing.JSlider heightSlider;
    private javax.swing.JSlider widthSlider;
    private javax.swing.JSlider bothSlider;
    private javax.swing.JLabel  jLabel1;
    private javax.swing.JLabel  jLabel2;
    private javax.swing.JLabel  jLabel4;
    private javax.swing.JPanel  jPanel6;
    private ImagePanel          picturePanel;
    private javax.swing.JButton quitButton;
    private javax.swing.JButton saveButton;


    // End of variables declaration//GEN-END:variables


    private class BackgroundListener implements ActionListener
    {
        private ImagePanel picturePanel;

        public BackgroundListener(ImagePanel _picturePanel)
        {
            picturePanel = _picturePanel;
        }

        public void actionPerformed(ActionEvent e)
        {
            String fi = FileChooser.pickAFile();

            if (fi != null)
            {
                picturePanel.setBackgroundImage(new Picture(fi));
            }

            picturePanel.requestFocus();
        }
    }


    private class ForegroundListener implements ActionListener
    {
        private ImagePanel picturePanel;

        public ForegroundListener(ImagePanel _picturePanel)
        {
            picturePanel = _picturePanel;
        }

        public void actionPerformed(ActionEvent e)
        {
            String fi = FileChooser.pickAFile();

            if (fi != null)
            {
                picturePanel.setForegroundImage(new Picture(fi));
            }

            picturePanel.requestFocus();
        }
    }

    private class WidthListenter implements ChangeListener
    {
        private ImagePanel picturePanel;

        public WidthListenter(ImagePanel _picturePanel)
        {
            picturePanel = _picturePanel;
        }

        public void stateChanged(ChangeEvent e)
        {
            JSlider slider = (JSlider)e.getSource();

            picturePanel.adjustForegroundWidth(slider.getValue());

            picturePanel.process();
            picturePanel.requestFocus();
        }
    }

    private class HeightListenter implements ChangeListener
    {
        private ImagePanel picturePanel;

        public HeightListenter(ImagePanel _picturePanel)
        {
            picturePanel = _picturePanel;
        }

        public void stateChanged(ChangeEvent e)
        {
            JSlider slider = (JSlider)e.getSource();

            picturePanel.adjustForegroundHeight(slider.getValue());

            picturePanel.process();
            picturePanel.requestFocus();
        }
    }


    private class BothListener implements ChangeListener
    {
        private ImagePanel picturePanel;

        public BothListener(ImagePanel _picturePanel)
        {
            picturePanel = _picturePanel;
        }

        public void stateChanged(ChangeEvent e)
        {
            JSlider slider = (JSlider)e.getSource();

            picturePanel.adjustForegroundWidth(slider.getValue());
            picturePanel.adjustForegroundHeight(slider.getValue());

            picturePanel.process();
            picturePanel.requestFocus();
        }
    }


    private class SaveListener implements ActionListener
    {
        private ImagePanel picturePanel;

        public SaveListener(ImagePanel _picturePanel)
        {
            picturePanel = _picturePanel;
        }

        public void actionPerformed(ActionEvent e)
        {
            Picture pic = picturePanel.getFinalImage();

            if (pic == null)
            {
                return;
            }

            JFileChooser jc = new JFileChooser(".");

            int status = jc.showSaveDialog(null);

            File file = null;

            if (status != JFileChooser.APPROVE_OPTION)
            {
                return;
            }
            else
            {
                file = jc.getSelectedFile();
            }

            try
            {
                BufferedImage bImage = pic.getBufferedImage();
                ImageIO.write(bImage, "png", file);
            } catch (IOException ex)
            {
                System.out.println("There was an exception!");
            }
        }
    }

    private class QuitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
}
