package gui.panel;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import download.DLTask;

public class DownloadPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1783257170523512884L;
	
	
	private List<DownloadProgressBar> myProgressBars;
	
    public DownloadPanel() {
    	super();
    	this.setSize(620, 580);
    	this.setVisible(true);
    	myProgressBars = new ArrayList<DownloadProgressBar>();
    }
    
    public void appendNewProgressBar() {
    	DownloadProgressBar newBar = new DownloadProgressBar();
    	myProgressBars.add(newBar);
        add(newBar);
//        System.out.println("Panel : X : " + newBar.getX());
//        System.out.println("Panel : Y : " + newBar.getY());
//        System.out.println("Panel : Width : " + newBar.getWidth());
//        System.out.println("Panel : Height : " + newBar.getHeight());
    }
    
}
