package fallstudie.view.impl;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;

public class LoeschenPopup extends JDialog implements View{

	private final JPanel contentPanel = new JPanel();
	private JButton B_loeschen;
	private JButton B_abbrechen;
	private JTextArea txtrWollenSieWirklich;
	

	/**
	 * Create the dialog.
	 */
	public LoeschenPopup() {
		setVisible(true);
		setEnabled(true);
		setModal(true);
		setTitle("Löschen");
		setResizable(false);
		setBounds(600, 300, 452, 167);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		txtrWollenSieWirklich = new JTextArea();
		txtrWollenSieWirklich.setBackground(SystemColor.menu);
		txtrWollenSieWirklich.setText("Wollen Sie wirklich löschen?");
		txtrWollenSieWirklich.setEditable(false);
		contentPanel.add(txtrWollenSieWirklich);
	
	
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		
			B_loeschen = new JButton("Ja");
			//B_speichern.addActionListener(new ActionListener() {
			//	public void actionPerformed(ActionEvent arg0) {
			//	}
			//});
			B_loeschen.setActionCommand("Ja");
			buttonPane.add(B_loeschen);
			getRootPane().setDefaultButton(B_loeschen);
			
		B_abbrechen = new JButton("Nein");
		B_abbrechen.setActionCommand("Nein");
		buttonPane.add(B_abbrechen);
			
		
	}

	@Override
	public void setController(Controller c) {
		this.B_loeschen.addActionListener(c);
		this.B_abbrechen.addActionListener(c);
	}

	@Override
	public void reset() {
		setTitle("");
	}

}
