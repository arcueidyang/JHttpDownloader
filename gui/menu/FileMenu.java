package gui.menu;


import gui.panel.DownloadPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import download.DLTask;
import download.TaskManager;

public class FileMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String INPUT_DIALOG_STRING = "please enter the address";
	
	private JMenuItem newItem;
	private JMenuItem pauseAllItem;
	private JMenuItem resumeAllItem;
	private JMenuItem closeItem;
	private DownloadPanel myPanel;
	
	private TaskManager myManager;
	
	public FileMenu(TaskManager manager, DownloadPanel panel) {
		this("", manager, panel);
	}
	
	public FileMenu(String name, TaskManager manager, DownloadPanel panel) {
		super(name);
		myManager = manager;
		myPanel = panel;
		createNewItem(myManager, myPanel);
		createPauseAllItem();
		createResumeAllItem();
		createCloseItem();
	}
	
	private void createNewItem(final TaskManager manager, final DownloadPanel panel) {
		newItem = new JMenuItem("New");
		newItem.setMnemonic(KeyEvent.VK_N);
		
		ActionListener newActionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileMenu.getPopupInputDialog(myManager, myPanel);
			}
		};
		newItem.addActionListener(newActionListener);
		add(newItem);
	}
	
	private void createPauseAllItem() {
	
		pauseAllItem = new JMenuItem("Pause All tasks");
		pauseAllItem.setMnemonic(KeyEvent.VK_P);
				
		ActionListener pauseActionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			    //TODO
			}
		};
		
		pauseAllItem.addActionListener(pauseActionListener);
		add(pauseAllItem);
	}
	
	private void createResumeAllItem() {
		resumeAllItem = new JMenuItem("Resume All tasks");
		resumeAllItem.setMnemonic(KeyEvent.VK_R);
        
		ActionListener resumeActionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			    //TODO
			}
		};
		
		resumeAllItem.addActionListener(resumeActionListener);
		add(resumeAllItem);

	}
	
	private void createCloseItem() {
		closeItem = new JMenuItem("Close");
		closeItem.setMnemonic(KeyEvent.VK_C);
		
		ActionListener closeActionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		};
		closeItem.addActionListener(closeActionListener);
		add(closeItem);
	}
	
	private static JDialog getPopupInputDialog(final TaskManager myManager, final DownloadPanel myPanel) {
		final JDialog dialog = new JDialog();
		
		JLabel label = new JLabel(INPUT_DIALOG_STRING, JLabel.CENTER);
		label.setFont(new Font("Dialog",1,15));
		dialog.add(label, BorderLayout.NORTH);
	
		
		final JTextArea textArea = new JTextArea();
		textArea.setSize(250,200);
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		final JPopupMenu menu = FileMenu.createPopupMenu(textArea);
		
		MouseListener mouseListener = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getButton() == MouseEvent.BUTTON3) {
					menu.setLocation(arg0.getXOnScreen(), arg0.getYOnScreen());
					menu.setVisible(true);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			    //do nothing
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				//do nothing	
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				//do nothing
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				//do nothing
			}
		};
		
		textArea.addMouseListener(mouseListener);
		
		final JTextField textField = new JTextField();
		textField.setSize(250, 50);
		textField.setText(Integer.toString(DLTask.MAX_THREAD_NUMBER));
		
		
		JButton confirmButton = new JButton("Confirm");
		JButton cancelButton = new JButton("Cancel");
		JPanel buttonPanel = new JPanel();
		
		ActionListener confirmListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			    String address = textArea.getText();
			    myManager.addNewTask(address, Integer.parseInt(textField.getText()));
			    myPanel.appendNewProgressBar();
				dialog.dispose();	
			}
		};

		confirmButton.addActionListener(confirmListener);

		ActionListener cancelListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}	
		};
		
		cancelButton.addActionListener(cancelListener);
		
		buttonPanel.add(confirmButton, BorderLayout.EAST);
		buttonPanel.add(cancelButton, BorderLayout.WEST);
		
		dialog.add(scrollPane, BorderLayout.CENTER);
		dialog.add(buttonPanel, BorderLayout.SOUTH);
		dialog.setSize(new Dimension(300,300));
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		
		dialog.setVisible(true);
	
	    return dialog;
	}
	
	public static JPopupMenu createPopupMenu(final JTextArea textArea) {
		final JPopupMenu rootMenu = new JPopupMenu("Right click menu"); 
	    JMenuItem pasteItem = new JMenuItem("Paste");
	    pasteItem.setMnemonic(KeyEvent.VK_P);
	    ActionListener pasteListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				Transferable tran = null;
				
				tran = clipboard.getContents(null);
				
				String content = null;
				
				if(tran != null && tran.isDataFlavorSupported(DataFlavor.stringFlavor)){
				    try {
					     content = (String)tran.getTransferData(DataFlavor.stringFlavor);
					} catch (UnsupportedFlavorException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				textArea.append(content);
				rootMenu.setVisible(false);
			}
	    	
	    };
	    pasteItem.addActionListener(pasteListener);
	    rootMenu.add(pasteItem);
	    
	    JMenuItem clearItem = new JMenuItem("Clear");
	    clearItem.setMnemonic(KeyEvent.VK_C);
	    ActionListener clearListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
			}
	    };
	    clearItem.addActionListener(clearListener);
	    rootMenu.add(clearItem);
	    
	    return rootMenu;
	
	}
	
}
