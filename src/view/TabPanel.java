package view;

import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controller.Controller;

/**
 * 
 * @author Andreas
 *
 */
public class TabPanel extends JPanel{
	
	/**
	 * Takes in a number (0, 1 or 2) to select one of the tabs
	 * 0 is MineAvtalerPanel
	 * 1 is MineMoterPanel
	 * 2 is InboxPanel
	 * 
	 * @param index
	 */
	public void setTab(int index){
		if(index >= 0 && index <= 2){
			tabbedPane.setSelectedIndex(index);
		}
	}

	/**
	 * Creates a tabbed panel for MineAvtalerPanel, MineMoterPanel and InboxPanel
	 */
	public TabPanel(Controller cont){
		tabbedPane = new JTabbedPane();
		
		//Mine Avtaler
		mineAvtalerPanel = new MineAvtalerPanel(cont);
		tabbedPane.addTab("Mine Avtaler", null, mineAvtalerPanel, 
				"Her kan du se alle dine registrerte avtaler");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		//Mine M�ter
		mineMoterPanel = new MineMoterPanel();
		tabbedPane.addTab("Mine M�ter", null, mineMoterPanel, 
				"Her kan du se alle dine registrerte m�ter, og hvem som deltar");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		//Inbox
		inboxPanel = new InboxPanel(cont);
		tabbedPane.addTab("Inbox", null, inboxPanel, 
				"Her kan du se og skrive meldinger, og svare p� invitasjoner");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		
		add(tabbedPane);
	}
	
	//Variables declaration
	private JTabbedPane tabbedPane;
	private MineAvtalerPanel mineAvtalerPanel;
	private MineMoterPanel mineMoterPanel;
	private InboxPanel inboxPanel;
	//End of variables declaration
}
