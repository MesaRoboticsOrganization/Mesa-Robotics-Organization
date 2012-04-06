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


public class MyFrame extends javax.swing.JFrame
{

    /**
     * Creates new form MyFrame
     */
    public MyFrame()
    {
        setTitle("Mesa Robotics Organization - Green Screen");
        initComponents();

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
        javax.swing.JButton backgroundButton = new javax.swing.JButton();
        foregroundButon = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();


        foregroundButon.addActionListener(new ForegroundListener(picturePanel));
        backgroundButton.addActionListener(new BackgroundListener(picturePanel));

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
            .addGap(0, 427, Short.MAX_VALUE)
        );

        backgroundButton.setText("Load Background");

        foregroundButon.setText("Load Foreground");

        saveButton.setText("Save Picture");

        quitButton.setText("Quit");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picturePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(backgroundButton)
                .addGap(89, 89, 89)
                .addComponent(foregroundButon)
                .addGap(89, 89, 89)
                .addComponent(saveButton)
                .addGap(89, 89, 89)
                .addComponent(quitButton)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(picturePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backgroundButton)
                    .addComponent(foregroundButon)
                    .addComponent(saveButton)
                    .addComponent(quitButton))
                .addGap(54, 54, 54))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );

        picturePanel.setVisible(true);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
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
    private javax.swing.JButton foregroundButon;
    private javax.swing.JPanel  jPanel6;
    private ImagePanel          picturePanel;
    private ImagePanel          foregroundPanel;
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
            // Create a file chooser with the current directory as the default directory
            JFileChooser jc = new JFileChooser(".");

            // Display it
            int status = jc.showOpenDialog(null);

            // File variable to be used
            File file = null;

            // String to store the content
            String str = "";

            // If user did not choose a file
            if (status != JFileChooser.APPROVE_OPTION)
            {
                // Just return
                return;
            }
            else
            {
                // Get the file that the user chose
                file = jc.getSelectedFile();
            }

            BufferedImage image = null;

            try
            {
                image = ImageIO.read(file);
            } catch (IOException ex)
            {
                return;
            }

            picturePanel.setBackgroundImage(image);
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
            // Create a file chooser with the current directory as the default directory
            JFileChooser jc = new JFileChooser(".");

            // Display it
            int status = jc.showOpenDialog(null);

            // File variable to be used
            File file = null;

            // String to store the content
            String str = "";

            // If user did not choose a file
            if (status != JFileChooser.APPROVE_OPTION)
            {
                // Just return
                return;
            }
            else
            {
                // Get the file that the user chose
                file = jc.getSelectedFile();
            }

            BufferedImage image = null;

            try
            {
                image = ImageIO.read(file);
            } catch (IOException ex)
            {
                return;
            }

            picturePanel.setForegroundImage(image);
        }
    }
}
