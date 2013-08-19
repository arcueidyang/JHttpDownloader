package gui.menu;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import download.TaskManager;

public class HelpMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenuItem contactItem;
	private JMenuItem aboutItem;
	
	public HelpMenu() {
		this("");
	}
	
	public HelpMenu(String title) {
		super(title);
		createContactItem();
		createAboutItem();
	}
	
	public void createContactItem() {
		contactItem = new JMenuItem("Contact Author");
		contactItem.setMnemonic(KeyEvent.VK_C);
		
		ActionListener contactListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(contactItem, "if you have any questions or advices to this game\n "+ 
			                                    "please send an e-mail to \n" + "arcueidyang@gmail.com", null, JOptionPane.OK_OPTION);
			}
		};
		
		contactItem.addActionListener(contactListener);
		add(contactItem);
	}
	
	public void createAboutItem() {
	    aboutItem = new JMenuItem("About..");
	    aboutItem.setMnemonic(KeyEvent.VK_A);
	    
	    ActionListener aboutListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(aboutItem, "version " + TaskManager.CURRENT_VERSION +"\n" +
						                                "Author : Yang", null, JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
			}
	    };
	    aboutItem.addActionListener(aboutListener);
	    add(aboutItem);
	}
}
