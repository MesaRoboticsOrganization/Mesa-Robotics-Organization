import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Chromakey extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5132504707183584799L;
	
	private static Picture fgPicture;
	private static Picture bgPicture;
	
	@SuppressWarnings("unused")
	private static Color keyColor = Color.GREEN;
	
	private static String targDirectory;
	private static JFileChooser dirChooser;
	
	public Chromakey(Picture foreGround, Picture newBackGround)
	{
		super("Chromakey");
		
		fgPicture = foreGround;
		bgPicture = newBackGround;
		
	}
	
	public static void setKeyColor(Color color)
	{
		keyColor = color;
	}
	
	public static void chooseKeyColor() {
        Color newKeyColor = JColorChooser.showDialog(null,
	            "Chromakey Key Color Selection", Color.GREEN);
        if ( newKeyColor != null) {
          setKeyColor(newKeyColor);
        }
	}
	
	public static boolean keyColorDetected(Pixel pixel)
	{
		return isMostlyGreen(pixel);
		//return isMostlyBlue(pixel);
	}
	
	public static boolean isMostlyGreen(Pixel pixel) {
		return (pixel.getRed() + pixel.getBlue() < pixel.getGreen());
	}
	
	public static boolean isMostlyBlue(Pixel pixel) {
		return (pixel.getRed() + pixel.getGreen() < pixel.getBlue());
	}
	
	public static void process()
	{
		Pixel[] pixelArray = getFgPicture().getPixels();
		Pixel curPixel = null;
		Pixel newPixel = null;
		
		// Loop through all the pixels
		for (int i = 0; i < pixelArray.length; i++)
		{
			// get the current pixel
			curPixel = pixelArray[i];
			
			/* If the color at the current pixel is mostly the set key color,
			 * then use the new background color instead 
			 */
			if (keyColorDetected(curPixel))
			{
				newPixel = bgPicture.getPixel(curPixel.getX(), curPixel.getY());
				curPixel.setColor(newPixel.getColor());
			}
		}
		
		fgPicture.repaint();
	}
	
	public static void write()
	{
		//("Please select a target directory.");
		System.out.println("Selected target directory is " + targDirectory);
		if (targDirectory != null)
		{
			FileChooser.setMediaPath(targDirectory);
		}
		
		String fileName = JOptionPane.showInputDialog("What would you like the chromakey filename to be?");
		String extension = ".jpg";
		
		if (fileName != null)
		{
			String fullyQualifiedFileName = targDirectory + fileName + extension;
			fgPicture.write(fullyQualifiedFileName);
			System.out.println("File written to " + fullyQualifiedFileName);
		}
		else
		{
			System.out.println("File creation aborted by user.");
		}
	}

	public static Picture getFgPicture() {
		return fgPicture;
	}

	@SuppressWarnings("unused")
	private Picture getBgPicture() {
		return bgPicture;
	}

	/**
	 * @param args
	 */
	public static void main(String args[]) {
	    FileChooser.setMediaPath("/home/sifu/mesa/MRO/greenScreenPics");
	    
	    Picture fgPicture = new Picture(FileChooser.pickAFile());
	    Picture bgPicture = new Picture(FileChooser.pickAFile());
	        
	    JFrame frame = new Chromakey(fgPicture, bgPicture);

	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container content = frame.getContentPane();
	    
	    ForePanel forePanel = new ForePanel(fgPicture);
	    BackPanel backPanel = new BackPanel(bgPicture);
	    
	    Box hBox = Box.createHorizontalBox();
	    hBox.add(forePanel);
	    hBox.add(backPanel);
	    
	    Box vBox = Box.createVerticalBox();
	    vBox.add(hBox);
	    
	    JButton button = new JButton("Process");
	    
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
//	    	  	chooseKeyColor();
	    	  	process();
	    	  	write();
	      }	
	    };
	    button.addActionListener(actionListener);

	    vBox.add(button);	   
	    content.add(vBox, BorderLayout.CENTER);
	    
	    frame.pack();
	    frame.setSize(1400, 600);
	    frame.setVisible(true);
	    
		//directory = FileChooser.pickAFile();
		
		dirChooser = new JFileChooser(); 
		dirChooser.setCurrentDirectory(new java.io.File("."));
		dirChooser.setDialogTitle("Select Target Directory");
		dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		/*
		*	disable the "All files" option.
		*/
		dirChooser.setAcceptAllFileFilterUsed(false);
	  
		if (dirChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) 
		{ 
			System.out.println("getCurrentDirectory(): " +  dirChooser.getCurrentDirectory());
			System.out.println("getSelectedFile() : " +  dirChooser.getSelectedFile());
			File selectedFile = dirChooser.getSelectedFile();
			targDirectory = selectedFile.getAbsolutePath() + "/"; 
		}
		else {
			System.out.println("No Selection ");
		}
	}

}
