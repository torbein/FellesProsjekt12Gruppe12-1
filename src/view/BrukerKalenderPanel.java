/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.CellRendererPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Ansatt;

import controller.Controller;

/**
 *
 * @author Eier
 */
public class BrukerKalenderPanel extends javax.swing.JPanel {

	//Variables declaration 1
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tabelModel;
	private int row = 0, col = 0;
	private Controller cont;
	private int thisWeekNr;
	private int thisYear;
	private boolean changeComboBox;
	//End of variables declaration 1

	/**
	 * Sets the current month and updates the calendar
	 */
	public void setCurrentMonth(int monthNr){
		Calendar cal = Calendar.getInstance();
		cal.set(thisYear, monthNr, 1);
		thisWeekNr = cal.get(Calendar.WEEK_OF_YEAR);
		if(thisWeekNr == 52){
			thisYear--;
		}
		addDatesToTable();
		updateWeekLabel(thisWeekNr);
	}
	/**
	 * Sets the current week of this panel to the int in the input
	 */
	public void setCurrentWeek(int weekNr){
		thisWeekNr = weekNr;
		addDatesToTable();
		updateWeekLabel(weekNr);
	}
	
	/**
	 * Method for setting the correct month
	 */
	public void findMonth(){
		ArrayList<String> tempDatoer = cont.getDateNr(thisWeekNr, thisYear);
		String s = tempDatoer.get(0);
		String month = "";
		for (int i = 3; i <= 4; i++) {
			month += s.charAt(i);
		}

		int monthNr = Integer.parseInt(month);
		//Since combobox is 0-indexed and monthnr isnt.
		manderComboBox.setSelectedIndex(monthNr-1);
	}
	/**
	 * Method for adding the dates for each week in the table
	 */
	public void addDatesToTable(){    	
		ArrayList<String> tempDato = cont.getDateNr(thisWeekNr, thisYear);
		System.out.println(tempDato);
		for (int i = 0; i < tempDato.size(); i++) {
			tabelModel.setValueAt(tempDato.get(i), 0, i+1);
		}
		changeComboBox = false;
		findMonth();
		changeComboBox = true;
	}
	public void addEmployeesToAddPersCombobox(){
		ArrayList<Ansatt> employees = new ArrayList<Ansatt>();
		employees = cont.getEmployees();
		for (int i = 0; i < employees.size(); i++) {
			leggTilPersonComboBox.addItem(employees.get(i).toString());
		}
	}
	public void addEmployeesToAddPersCombobox(ArrayList<Ansatt> empl){
		ArrayList<Ansatt> employees = empl;
		leggTilPersonComboBox.removeAllItems();
		for (int i = 0; i < employees.size(); i++) {
			leggTilPersonComboBox.addItem(employees.get(i).toString());
		}
	}
	
	
	/**
	 * Adding all the months of the year and returning an ArrayList<String> 
	 */
	public ArrayList<String> addMotnhs(){
		ArrayList<String> maanderListe = new ArrayList<String>();
		maanderListe.add("Januar");
		maanderListe.add("Februar");
		maanderListe.add("Mars");
		maanderListe.add("April");
		maanderListe.add("Mai");
		maanderListe.add("Juni");
		maanderListe.add("Juli");
		maanderListe.add("August");
		maanderListe.add("September");
		maanderListe.add("Oktober");
		maanderListe.add("November");
		maanderListe.add("Desember");
		return maanderListe;
	}
	
	//Getters and setters for variables
	public int getCurrentWeek(){
		return thisWeekNr;
	}
	public boolean getChangeComboBox(){
		return changeComboBox;
	}
	public void setThisYear(int thisYear) {
		this.thisYear = thisYear;
	}
	
	public int getThisYear() {
		return thisYear;
	}
	//End of getters and setters for variables


	public void updateWeekLabel(int week){
		ukeNrLabel.setText("Uke: " + week);
	}

	//Getters for components
	public JButton getForrigeUkeButton(){
		return forrigeUkeButton;
	}
	public JButton getNesteUkeButton(){
		return nesteUkeButton;
	}
	public JComboBox getManderComboBox(){
		return manderComboBox;
	}
	public JButton getMineMoterButton(){
		return mineMoterKnapp;
	}
	public JComboBox getLeggTilPersonComboBox(){
		return leggTilPersonComboBox;
	}
	public JComboBox getFjernPersonComboBox(){
		return fjernPersonComboBox;
	}
	//End of getters for components

	
	/**
	 * Creates new form calTest
	 */
	public BrukerKalenderPanel(Controller cont) {
		changeComboBox = true;
		this.cont = cont;
		thisWeekNr = cont.getWeekNr();
		setThisYear(cont.getYear());
		addMotnhs();
		initComponents();
		addDatesToTable(); 
		addEmployeesToAddPersCombobox();
	}

	public int getSelectedRow(){
		return row;
	}
	public int getSelectedCollum(){
		return col;
	}



	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jScrollPane1 = new javax.swing.JScrollPane();
		kalender = new javax.swing.JTable();
		menyPanel = new javax.swing.JPanel();
		lagAvtaleKnapp = new javax.swing.JButton();
		lagMoteKnapp = new javax.swing.JButton();
		mineAvtalerKnapp = new javax.swing.JButton();
		mineMoterKnapp = new javax.swing.JButton();
		innboksKnapp = new javax.swing.JButton();
		leggTilPersonLabel = new javax.swing.JLabel();
		leggTilPersonComboBox = new javax.swing.JComboBox();
		fjernPersonLabel = new javax.swing.JLabel();
		fjernPersonComboBox = new javax.swing.JComboBox();
		leggTilPersKnapp = new javax.swing.JButton();
		spmLeggTilPersKnapp = new javax.swing.JButton();
		fjernPersKnapp = new javax.swing.JButton();
		spmFjernPersKnapp = new javax.swing.JButton();
		forrigeUkeButton = new javax.swing.JButton();
		nesteUkeButton = new javax.swing.JButton();
		manedukePanel = new javax.swing.JPanel();
		ukeNrLabel = new javax.swing.JLabel();
		manedLabel = new javax.swing.JLabel();
		manderComboBox = new javax.swing.JComboBox();
		ulesteMeldingerKnapp = new javax.swing.JButton();

		setMaximumSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setLayout(new java.awt.GridBagLayout());

		tabelModel = new javax.swing.table.DefaultTableModel(
				new Object [][] {
						{"", null, null, null, null, null, null, null},
						{"00:00", null, null, null, null, null, null, null},
						{"01:00", null, null, null, null, null, null, null},
						{"02:00", null, null, null, null, null, null, null},
						{"03:00", null, null, null, null, null, null, null},
						{"04:00", null, null, null, null, null, null, null},
						{"05:00", null, null, null, null, null, null, null},
						{"06:00", null, null, null, null, null, null, null},
						{"07:00", null, null, null, null, null, null, null},
						{"08:00", null, null, null, null, null, null, null},
						{"09:00", null, null, null, null, null, null, null},
						{"10:00", null, null, null, null, null, null, null},
						{"11:00", null, null, null, null, null, null, null},
						{"12:00", null, null, null, null, null, null, null},
						{"13:00", null, null, null, null, null, null, null},
						{"14:00", null, null, null, null, null, null, null},
						{"15:00", null, null, null, null, null, null, null},
						{"16:00", null, null, null, null, null, null, null},
						{"17:00", null, null, null, null, null, null, null},
						{"18:00", null, null, null, null, null, null, null},
						{"19:00", null, null, null, null, null, null, null},
						{"20:00", null, null, null, null, null, null, null},
						{"21:00", null, null, null, null, null, null, null},
						{"22:00", null, null, null, null, null, null, null},
						{"23:00", null, null, null, null, null, null, null}
				},
				new String [] {
						"kl", "Mandag", "Tirsdag", "Onsdag", "Torsdag", "Fredag", "Lordag", "Sondag"
				}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean [] {
					false, false, false, false, false, false, false, false
			};

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit [columnIndex];
			}
		};
		kalender.setModel(tabelModel);
		kalender.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i = 1; i <= 7; i++) {
			kalender.getColumnModel().getColumn(i).setWidth(45);
		}
		kalender.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				row = kalender.getSelectedRow();
				col = kalender.getSelectedColumn();
				cont.mousePressed(arg0);

			}			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stubt
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		//387 før, 650
		jScrollPane1.setPreferredSize(new Dimension(600, 450));
		kalender.setRowSelectionAllowed(false);
		kalender.getTableHeader().setReorderingAllowed(false);
		jScrollPane1.setViewportView(kalender);
		kalender.getColumnModel().getColumn(0).setResizable(false);
		kalender.getColumnModel().getColumn(1).setResizable(false);
		kalender.getColumnModel().getColumn(2).setResizable(false);
		kalender.getColumnModel().getColumn(3).setResizable(false);
		kalender.getColumnModel().getColumn(4).setResizable(false);
		kalender.getColumnModel().getColumn(5).setResizable(false);
		kalender.getColumnModel().getColumn(6).setResizable(false);
		kalender.getColumnModel().getColumn(7).setResizable(false);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		add(jScrollPane1, gridBagConstraints);

		menyPanel.setLayout(new java.awt.GridBagLayout());

		lagAvtaleKnapp.setText("Lag avtale");
		lagAvtaleKnapp.addActionListener(cont);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		menyPanel.add(lagAvtaleKnapp, gridBagConstraints);

		lagMoteKnapp.setText("Lag mote");
		lagMoteKnapp.addActionListener(cont);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		menyPanel.add(lagMoteKnapp, gridBagConstraints);

		mineAvtalerKnapp.setText("Mine avtaler");
		mineAvtalerKnapp.addActionListener(cont);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		menyPanel.add(mineAvtalerKnapp, gridBagConstraints);

		mineMoterKnapp.setText("Mine moter");
		mineMoterKnapp.addActionListener(cont);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		menyPanel.add(mineMoterKnapp, gridBagConstraints);

		innboksKnapp.setText("Innboks");
		innboksKnapp.addActionListener(cont);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		menyPanel.add(innboksKnapp, gridBagConstraints);

		leggTilPersonLabel.setText("Legg tilperson");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		menyPanel.add(leggTilPersonLabel, gridBagConstraints);

		leggTilPersonComboBox.setEditable(true);
		leggTilPersonComboBox.getEditor().getEditorComponent().addKeyListener(cont);
		leggTilPersonComboBox.setModel(new javax.swing.DefaultComboBoxModel());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		menyPanel.add(leggTilPersonComboBox, gridBagConstraints);

		fjernPersonLabel.setText("Fjern person");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 8;
		menyPanel.add(fjernPersonLabel, gridBagConstraints);

		fjernPersonComboBox.setEditable(true);
		fjernPersonComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 9;
		menyPanel.add(fjernPersonComboBox, gridBagConstraints);

		leggTilPersKnapp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bilder/plus16-green-icon.png"))); // NOI18N
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
		menyPanel.add(leggTilPersKnapp, gridBagConstraints);

		spmLeggTilPersKnapp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bilder/question16-blue-icon.png"))); // NOI18N
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
		menyPanel.add(spmLeggTilPersKnapp, gridBagConstraints);

		fjernPersKnapp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bilder/minus16-red-icon.png"))); // NOI18N
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 10;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
		menyPanel.add(fjernPersKnapp, gridBagConstraints);

		spmFjernPersKnapp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bilder/question16-blue-icon.png"))); // NOI18N
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 10;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
		menyPanel.add(spmFjernPersKnapp, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 24;
		gridBagConstraints.weighty = 0.5;
		gridBagConstraints.insets = new java.awt.Insets(3, 0, 156, 0);
		add(menyPanel, gridBagConstraints);

		forrigeUkeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bilder/arrow22-left.png"))); // NOI18N
		forrigeUkeButton.addActionListener(cont);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.weighty = 0.5;
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 48, 0);
		add(forrigeUkeButton, gridBagConstraints);

		nesteUkeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bilder/arrow22-right.png"))); // NOI18N
		nesteUkeButton.addActionListener(cont);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 48, 0);
		add(nesteUkeButton, gridBagConstraints);

		manedukePanel.setLayout(new java.awt.GridBagLayout());

		ukeNrLabel.setText("Uke: " + thisWeekNr);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 6);
		manedukePanel.add(ukeNrLabel, gridBagConstraints);

		manedLabel.setText("Maned:");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 6;
		manedukePanel.add(manedLabel, gridBagConstraints);
		
		//la til mander med arraylist
		manderComboBox.setModel(new javax.swing.DefaultComboBoxModel(addMotnhs().toArray()));
		manderComboBox.addActionListener(cont);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 37;
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 169);
		manedukePanel.add(manderComboBox, gridBagConstraints);

		ulesteMeldingerKnapp.setText("Uleste Meldinger");
		ulesteMeldingerKnapp.addActionListener(cont);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 0;
		manedukePanel.add(ulesteMeldingerKnapp, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		add(manedukePanel, gridBagConstraints);
	}// </editor-fold>


	// Variables declaration 2- do not modify
	private javax.swing.JButton fjernPersKnapp;
	private javax.swing.JComboBox fjernPersonComboBox;
	private javax.swing.JLabel fjernPersonLabel;
	private javax.swing.JButton forrigeUkeButton;
	private javax.swing.JButton innboksKnapp;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable kalender;
	private javax.swing.JButton lagAvtaleKnapp;
	private javax.swing.JButton lagMoteKnapp;
	private javax.swing.JButton leggTilPersKnapp;
	private javax.swing.JComboBox leggTilPersonComboBox;
	private javax.swing.JLabel leggTilPersonLabel;
	private javax.swing.JComboBox manderComboBox;
	private javax.swing.JLabel manedLabel;
	private javax.swing.JPanel manedukePanel;
	private javax.swing.JButton ulesteMeldingerKnapp;
	private javax.swing.JPanel menyPanel;
	private javax.swing.JButton mineAvtalerKnapp;
	private javax.swing.JButton mineMoterKnapp;
	private javax.swing.JButton nesteUkeButton;
	private javax.swing.JButton spmFjernPersKnapp;
	private javax.swing.JButton spmLeggTilPersKnapp;
	private javax.swing.JLabel ukeNrLabel;
	// End of variables declaration 2
}
