package view;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.Controller;

import model.Melding;

public class VisAutoMeldingPanel extends VisMeldingPanel {

	public VisAutoMeldingPanel(Controller cont, Melding meld){
		
		super(cont, meld);
		godtaKnapp = new JButton();
		avslaaKnapp = new JButton();
		
		godtaKnapp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bilder/plus16-green-icon.png")));
		godtaKnapp.setText("Godta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        add(godtaKnapp, gridBagConstraints);
        
        avslaaKnapp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bilder/minus16-red-icon.png")));
        avslaaKnapp.setText("Avsl�");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        add(avslaaKnapp, gridBagConstraints);
	}
	
	//Variables declaration
	private JButton godtaKnapp;
	private JButton avslaaKnapp;
	private Melding meld;
	private Controller cont;
	//End of variables declaration
}
