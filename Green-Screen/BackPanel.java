import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class BackPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -651088386650162775L;

	private BufferedImage image;

    public BackPanel(Picture backPicture) {
       image = backPicture.getBufferedImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}
