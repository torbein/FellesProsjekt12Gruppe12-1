package view;

/**
 *
 * @author Andreas
 */
public class SendMeldingPanel extends javax.swing.JPanel {

    /**
     * Creates new form SendMeldingPanel
     */
    public SendMeldingPanel() {
        initComponents();
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

        tilLabel = new javax.swing.JLabel();
        tilTextField = new javax.swing.JTextField();
        beskjedScrollPane = new javax.swing.JScrollPane();
        beskjedTextArea = new javax.swing.JTextArea();
        avbrytKnapp = new javax.swing.JButton();
        sendKnapp = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(320, 240));
        setLayout(new java.awt.GridBagLayout());

        tilLabel.setText("Til:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        add(tilLabel, gridBagConstraints);

        tilTextField.setPreferredSize(new java.awt.Dimension(200, 20));
        tilTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tilTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        add(tilTextField, gridBagConstraints);

        beskjedScrollPane.setPreferredSize(new java.awt.Dimension(253, 100));

        beskjedTextArea.setColumns(20);
        beskjedTextArea.setRows(5);
        beskjedScrollPane.setViewportView(beskjedTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        add(beskjedScrollPane, gridBagConstraints);

        avbrytKnapp.setText("Avbryt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        add(avbrytKnapp, gridBagConstraints);

        sendKnapp.setText("Send");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        add(sendKnapp, gridBagConstraints);
    }// </editor-fold>

    private void tilTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    // Variables declaration - do not modify
    private javax.swing.JButton avbrytKnapp;
    private javax.swing.JScrollPane beskjedScrollPane;
    private javax.swing.JTextArea beskjedTextArea;
    private javax.swing.JButton sendKnapp;
    private javax.swing.JLabel tilLabel;
    private javax.swing.JTextField tilTextField;
    // End of variables declaration
}
