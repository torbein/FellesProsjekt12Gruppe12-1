package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Ansatt;
import model.Avtale;
import model.DBConnection;
import model.Rom;

import com.sun.media.sound.Toolkit;

import view.AvtalePanel;
import view.BrukerKalenderPanel;
import view.InboxPanel;
import view.LoginPanel;
import view.MineAvtalerPanel;
import view.TabPanel;
import view.VisMeldingPanel;

public class Controller implements ActionListener, KeyListener, ListSelectionListener, MouseListener {

	private JPanel currentGUI;
	private JFrame mainFrame;
	private JMenu logOffMenu;
	private JMenuItem loggingOff;
	private JMenuBar menuBar;
	private DBConnection db;
	private Calendar calendar;
	private Ansatt user;

	public Controller(){
		try {
			db = new DBConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calendar = Calendar.getInstance();
		currentGUI = new LoginPanel(this);
		mainFrame = new JFrame();
		mainFrame.getContentPane().add(currentGUI);
		mainFrame.pack();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		centerFrame();

	}

	public static void main(String[] args) {
		new Controller();
	}
	/**
	 * Method for put the frame in the middle of the screen.
	 */
	public void centerFrame(){
		Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int w = mainFrame.getSize().width;
		int h = mainFrame.getSize().height;
		int x = (dim.width - w)/2;
		int y = (dim.height - h)/2;
		mainFrame.setLocation(x,y);
	}


	@Override
	/**
	 * Method for controlling actions happening on other panels.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JMenuItem){
			if(e.getActionCommand().equals("Logg ut")){
				mainFrame.getContentPane().remove(currentGUI);
				menuBar.removeAll();
				currentGUI = new LoginPanel(this);
				menuBar.removeAll();
				mainFrame.getContentPane().add(currentGUI);
				mainFrame.pack();
				centerFrame();
				mainFrame.setVisible(true);
				return;
			}
		}
		else if(currentGUI instanceof LoginPanel){
			if(e.getActionCommand().equals("Logg inn")){
				String username = ((LoginPanel) currentGUI).getUser();
				String password = ((LoginPanel) currentGUI).getPassword();
				if(!username.isEmpty() && !password.isEmpty()){
					try {
						if(db.checkIfPasswordIsLegal(username, password)){
							mainFrame.getContentPane().remove(currentGUI);
							currentGUI = new BrukerKalenderPanel(this);
							loggingOff = new JMenuItem("Logg ut");
							logOffMenu = new JMenu("File");
							menuBar = new JMenuBar();
							menuBar.add(logOffMenu);
							logOffMenu.add(loggingOff);    
							loggingOff.addActionListener(this);
							mainFrame.setJMenuBar(menuBar);
							mainFrame.getContentPane().add(currentGUI);
							mainFrame.pack();
							mainFrame.setResizable(false);
							mainFrame.setVisible(true);
							centerFrame();
							user = db.getEmployee(username);
						}
						else{
							JOptionPane.showMessageDialog(null, "Feil brukernavn eller passord");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Du har ikke skrevet " +
							"inn brukernavn eller passord");
				}
			}
		}
		else if(currentGUI instanceof BrukerKalenderPanel){
			if(e.getActionCommand().equals("Lag avtale")){
				mainFrame.getContentPane().remove(currentGUI);
				currentGUI = new AvtalePanel(this);
				mainFrame.getContentPane().add(currentGUI);
				mainFrame.pack();
				mainFrame.setResizable(false);
				mainFrame.setVisible(true);
				centerFrame();
				if(e.getActionCommand().equals("Lagre")){

				}
			}
			else if(e.getActionCommand().equals("Lag mote")){
				System.out.println("DET KOMMER, SLAPP AV A");
			}
			//This is for the Mine Avtaler button
			else if (e.getActionCommand().equals("Mine avtaler")) {
				mainFrame.getContentPane().remove(currentGUI);
				currentGUI = new TabPanel(this);
				((TabPanel)currentGUI).setTab(0);
				mainFrame.getContentPane().add(currentGUI);
				mainFrame.pack();
				mainFrame.setResizable(false);
				mainFrame.setVisible(true);
				centerFrame();
			}
			//This is for the Mine M�ter button
			//getSource blir brukt fordi vi har problemer med �
			else if (e.getSource() == ((BrukerKalenderPanel)currentGUI).getMineMoterButton()) {
				mainFrame.getContentPane().remove(currentGUI);
				currentGUI = new TabPanel(this);
				((TabPanel)currentGUI).setTab(1);
				mainFrame.getContentPane().add(currentGUI);
				mainFrame.pack();
				mainFrame.setResizable(false);
				mainFrame.setVisible(true);
				centerFrame();
			}
			//This is for the inbox button
			else if(e.getActionCommand().equals("Innboks")){
				mainFrame.getContentPane().remove(currentGUI);
				currentGUI = new TabPanel(this);
				((TabPanel)currentGUI).setTab(2);
				mainFrame.getContentPane().add(currentGUI);
				mainFrame.pack();
				mainFrame.setResizable(false);
				mainFrame.setVisible(true);
				centerFrame();
			}
			//This is for the previous week button in BrukerKalenderPanel
			else if (e.getSource() == ((BrukerKalenderPanel) currentGUI).getForrigeUkeButton()) {
				if(((BrukerKalenderPanel)currentGUI).getCurrentWeek() == 1){
					((BrukerKalenderPanel)currentGUI).setThisYear(
							((BrukerKalenderPanel)currentGUI).getThisYear()-1);
					((BrukerKalenderPanel)currentGUI).setCurrentWeek(52);
				}
				else{
					((BrukerKalenderPanel)currentGUI).setCurrentWeek(
							((BrukerKalenderPanel)currentGUI).getCurrentWeek()-1);
				}
			}
			//This is for the next week button in BrukerKalenderPanel
			else if (e.getSource() == ((BrukerKalenderPanel)currentGUI).getNesteUkeButton()) {
				if(((BrukerKalenderPanel)currentGUI).getCurrentWeek() == 52){
					((BrukerKalenderPanel)currentGUI).setThisYear(
							((BrukerKalenderPanel)currentGUI).getThisYear()+1);
					((BrukerKalenderPanel)currentGUI).setCurrentWeek(1);
				}
				else{
					((BrukerKalenderPanel)currentGUI).setCurrentWeek(
							((BrukerKalenderPanel)currentGUI).getCurrentWeek()+1);
				}
			}
			//This is for the combobox to change months in BrukerKalenderPanel

			else if (e.getSource() == ((BrukerKalenderPanel)currentGUI).getManderComboBox()
					&& ((BrukerKalenderPanel)currentGUI).getChangeComboBox()) {
				((BrukerKalenderPanel)currentGUI).setCurrentMonth(((BrukerKalenderPanel)currentGUI).getManderComboBox().getSelectedIndex());
			}

		}
		else if(currentGUI instanceof AvtalePanel){
			if(e.getActionCommand().equals("Tilbake")){
				mainFrame.getContentPane().remove(currentGUI);
				currentGUI = new BrukerKalenderPanel(this);
				mainFrame.getContentPane().add(currentGUI);
				mainFrame.pack();
				mainFrame.setResizable(false);
				mainFrame.setVisible(true);
				centerFrame();
			}
			else if(e.getActionCommand().equals("Lagre")){
				Avtale avtale = new Avtale(((AvtalePanel) currentGUI).getBeskrivelse(),((AvtalePanel)currentGUI).getMoterom(),
						((AvtalePanel) currentGUI).getTittelField(),user.getAnsattID(),((AvtalePanel) currentGUI).getDay(),
						((AvtalePanel) currentGUI).getMonth(),((AvtalePanel) currentGUI).getYear(),
						((AvtalePanel) currentGUI).getStartTime(),((AvtalePanel) currentGUI).getStartMinutt(),
						((AvtalePanel) currentGUI).getSluttTimer(),((AvtalePanel) currentGUI).getSluttMinutter());  
				try {
					db.makeAppointment(avtale);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		else if(currentGUI instanceof MineAvtalerPanel){
			if(e.getActionCommand().equals("Fjern Avtale")){
				Avtale avtale = ((MineAvtalerPanel) currentGUI).removeSelectedAppointments();
				
			}
		}
		else if(currentGUI instanceof InboxPanel ) {
			if(e.getActionCommand().equals("Vis Melding")) {
				mainFrame.getContentPane().remove(currentGUI);
				//currentGUI = new VisMeldingPanel(this);
				mainFrame.getContentPane().add(currentGUI);
				mainFrame.pack();
				mainFrame.setResizable(false);
				mainFrame.setVisible(true);
				centerFrame();
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if(currentGUI instanceof BrukerKalenderPanel){
			System.out.println("LOL");
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int col;
		int row;
		if(currentGUI instanceof BrukerKalenderPanel){
			row = ((BrukerKalenderPanel) currentGUI).getSelectedRow();
			col = ((BrukerKalenderPanel) currentGUI).getSelectedCollum();
			System.out.println("col: " + col + " row: " + row);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Gets all the available roms.
	 * @return ArrayList<Rom>.
	 */
	public ArrayList<Rom> getMoteRom(){
		ArrayList<Rom> availableRooms = new ArrayList<Rom>();
		try {
			availableRooms = db.getAvailableRooms();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return availableRooms;	
	}


	/**
	 * Gets the current week as an integer
	 * @return
	 */
	public int getWeekNr(){
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	public int getYear(){
		return calendar.get(Calendar.YEAR);
	}

	public int getMonth(){
		return calendar.get(Calendar.MONTH);
	}

	public int getDateOfWeek(){
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * Returns an ArrayList of dates as Strings
	 * Example: [12/03/2012, 13/03/2012, 14/03/2012, 15/03/2012, 16/03/2012, 17/03/2012, 18/03/2012]
	 * @return
	 */
	public ArrayList<String> getDateNr(int thisWeekNr, int thisYear){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		ArrayList<String> datoer = new ArrayList<String>();
		for (int i = 1; i < 8; i++) {
			cal.set(Calendar.YEAR, thisYear);
			cal.set(Calendar.WEEK_OF_YEAR, thisWeekNr);        
			//i+1 and i starts at 1 to get Monday as first day of week
			cal.set(Calendar.DAY_OF_WEEK, i + 1);
			datoer.add(sdf.format(cal.getTime()));
		}
		System.out.println(datoer);
		return datoer;
	}

	public ArrayList<Avtale> getAppointments(){
		try {
			return db.getMyAppointments(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Ansatt> getEmployees(){
		try {
			return db.getEmployees();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Ansatt> getEmployees(String text){
		try {
			return db.getEmplyoees(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void keyPressed(KeyEvent e) {


	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("YOYO");
		if(currentGUI instanceof BrukerKalenderPanel){
			if(e.getSource() == ((BrukerKalenderPanel) currentGUI).getLeggTilPersonComboBox().getEditor().getEditorComponent()){
				System.out.println("LOL");
				JComboBox tempCombo = ((BrukerKalenderPanel) currentGUI).getLeggTilPersonComboBox();
				String text = (String) tempCombo.getEditor().getItem();
				((BrukerKalenderPanel) currentGUI).addEmployeesToAddPersCombobox(getEmployees(text));
				tempCombo.setSelectedItem(text);
				tempCombo.setPopupVisible(true);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
