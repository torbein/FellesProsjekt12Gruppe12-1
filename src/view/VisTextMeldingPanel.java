package view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;

import model.Melding;

/**
*
* @author Andreas
*/
public class VisTextMeldingPanel extends VisMeldingPanel{

	/**
     * Creates new form VisTextMeldingPanel
     */
	public VisTextMeldingPanel(Controller cont, Melding melding){
		
		super(cont, melding);
		svarKnapp = new JButton();
		videresendKnapp = new JButton();
		
		svarKnapp.setText("Svar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        add(svarKnapp, gridBagConstraints);
		
        videresendKnapp.setText("Videresend");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        add(videresendKnapp, gridBagConstraints);
	}
	
	//Variables declaration
	private JButton svarKnapp;
	private JButton videresendKnapp;
	//End of variables declaration
}
