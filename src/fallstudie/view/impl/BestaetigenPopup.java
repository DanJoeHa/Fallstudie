package fallstudie.view.impl;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;

public class BestaetigenPopup extends JDialog implements View{

	private final JPanel contentPanel = new JPanel();
	private JButton B_speichern;
	private JButton B_abbrechen;
	private JTextArea txtrWollenSieWirklich;
	

	/**
	 * Create the dialog.
	 */
	public BestaetigenPopup() {
		setVisible(true);
		setEnabled(true);
		setModal(true);
		setTitle("Bestätigung");
		setResizable(false);
		setBounds(100, 100, 452, 167);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		txtrWollenSieWirklich = new JTextArea();
		txtrWollenSieWirklich.setBackground(SystemColor.menu);
		txtrWollenSieWirklich.setText("Wollen Sie wirklich speichern?");
		txtrWollenSieWirklich.setEditable(false);
		contentPanel.add(txtrWollenSieWirklich);
	
	
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
		B_abbrechen = new JButton("Nein");
		B_abbrechen.setActionCommand("Nein");
		buttonPane.add(B_abbrechen);
	
	
		B_speichern = new JButton("JA");
		//B_speichern.addActionListener(new ActionListener() {
		//	public void actionPerformed(ActionEvent arg0) {
		//	}
		//});
		B_speichern.setActionCommand("JA");
		buttonPane.add(B_speichern);
		getRootPane().setDefaultButton(B_speichern);
			
		
	}

	@Override
	public void setController(Controller c) {
		this.B_speichern.addActionListener(c);
		this.B_abbrechen.addActionListener(c);
	}

	@Override
	public void reset() {
		setTitle("");
	}

}
