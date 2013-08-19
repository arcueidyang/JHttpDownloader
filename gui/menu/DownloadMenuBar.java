package gui.menu;


import gui.panel.DownloadPanel;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import download.TaskManager;

public class DownloadMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenu fileMenu;
	private JMenu helpMenu;
	private JMenu settingMenu;
	
	public DownloadMenuBar(TaskManager manager, DownloadPanel panel) {
		super();
		createFileMenu(manager, panel);
		createSettingMenu();
		createHelpMenu();
	}
	
	private void createFileMenu(TaskManager manager, DownloadPanel panel) {
        fileMenu = new FileMenu("File", manager, panel);
        fileMenu.setMnemonic(KeyEvent.VK_F);
		add(fileMenu);
	}
	
	private void createSettingMenu() {
		settingMenu = new SettingMenu("Setting");
		settingMenu.setMnemonic(KeyEvent.VK_S);
		add(settingMenu);
	}
	
	private void createHelpMenu() {
		helpMenu = new HelpMenu("Help");
        fileMenu.setMnemonic(KeyEvent.VK_H);
		add(helpMenu);
	}
	
	
}
