import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class ForePanel extends JPanel {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 3985324545277924251L;
	
	private BufferedImage image;

    public ForePanel(Picture fgPicture) {
       image = fgPicture.getBufferedImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}
