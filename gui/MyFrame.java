package gui;

import java.awt.BorderLayout;

import gui.menu.DownloadMenuBar;
import gui.panel.DownloadPanel;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import download.TaskManager;

public class MyFrame {

	public static final String PROGRAM_NAME = "JHttpDownloader";
	
	private JFrame myFrame;
	private JMenuBar myMenuBar;
	private TaskManager myManager;
	private DownloadPanel myDownloadPanel;

    public MyFrame() {
    	myDownloadPanel = new DownloadPanel();
		myManager = new TaskManager();
    	myMenuBar = new DownloadMenuBar(myManager, myDownloadPanel);
    	myFrame = new JFrame(PROGRAM_NAME);
    	myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	myFrame.setJMenuBar(myMenuBar);
    	myFrame.add(myDownloadPanel, BorderLayout.CENTER);
    	myFrame.setSize(640, 600);
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);
		myFrame.setResizable(false);

    }





}
