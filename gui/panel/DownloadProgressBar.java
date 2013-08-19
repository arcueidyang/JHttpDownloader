package gui.panel;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import download.DLTask;

public class DownloadProgressBar extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4082254149778939063L;
	
	public static final Dimension DEFAULT_DIMENSION = new Dimension(620,100);
	
	private JProgressBar myProgressBar;
	private JLabel myLabel;
	//private DLTask myTask;
	private boolean isUpdating;
	private Thread th;
	private int current;
	
	public DownloadProgressBar() {
		super();
		//myTask = task;
		createProgressBar();
		createLabel();
		current = 0;
		isUpdating = true;
		setVisible(true);
		th = new Thread(this);
		th.start();
	}
	
	public void createProgressBar() {
		myProgressBar = new JProgressBar(JProgressBar.HORIZONTAL);
		myProgressBar.setString("progress");
		myProgressBar.setStringPainted(true);
		myProgressBar.setMinimum(0);
		myProgressBar.setMaximum(200);
		myProgressBar.setVisible(true);
//        System.out.println("Bar : X : " + myProgressBar.getX());
//        System.out.println("Bar : Y : " + myProgressBar.getY());
//        System.out.println("Bar : Width : " + myProgressBar.getWidth());
//        System.out.println("Bar : Height : " + myProgressBar.getHeight());
		add(myProgressBar);
	}
	
	public void createLabel() {
		myLabel = new JLabel("Task 0");
		//myLabel = new JLabel(myTask.getFileName());
		myLabel.setLocation(getX(), getY());
		add(myLabel);
	}

	@Override
	public void run() {
		while(isUpdating) {
			myProgressBar.setValue(current);
			current ++;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void stopUpdate() {
		isUpdating = false;
	}
	
	public void resumeUpdate() {
		isUpdating = true;
	}
	
}
