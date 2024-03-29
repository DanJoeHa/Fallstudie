package fallstudie.view.impl;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;

import java.awt.SystemColor;
import java.awt.event.KeyListener;
/**
 * Popup für die Hilfe
 * @author Marc
 *
 */
public class SchliessenPopup extends JDialog implements View{
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 8389558871657416016L;
	
	/**
	 * Inhalt Panel
	 */
	private final JPanel contentPanel = new JPanel();
	/**
	 * Ok Button
	 */
	private JButton B_ok;
	/**
	 * Hinweis Text
	 */
	private JTextPane T_Hinweis;

	/**
	 * Create the dialog.
	 */
	public SchliessenPopup() {
		setModal(false);
		setTitle("");
		//setEnabled(true);
		setResizable(false);
		this.setSize(500, 250);
		this.setLocationRelativeTo(null);	
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		T_Hinweis = new JTextPane();
		T_Hinweis.setBackground(SystemColor.menu);
		T_Hinweis.setText("");
		contentPanel.add(T_Hinweis);
		T_Hinweis.setEditable(false);
		T_Hinweis.setPreferredSize(new Dimension(450, 200));
		T_Hinweis.revalidate();
	
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		contentPanel.setFocusable(true);
		contentPanel.setRequestFocusEnabled(true);
		contentPanel.grabFocus();
		
		B_ok = new JButton("Schließen");
		B_ok.setActionCommand("Schließen");
		B_ok.setEnabled(true);
		buttonPane.add(B_ok);
		B_ok.setDefaultCapable(true);
		getRootPane().setDefaultButton(B_ok);
		this.setVisible(false);
		
			
	
	}
	
	/**
	 * Hinweis setzen
	 * @param Hinweistext
	 */
	public void setHinweis(String Hinweis){
		T_Hinweis.setText(Hinweis);
	}
	/**
	 * Setzt Controller als Action- & KeyListener
	 */
	@Override
	public void setController(Controller c) {
		this.B_ok.addActionListener(c);
		this.B_ok.addKeyListener((KeyListener) c);
	}
	/**
	 * Zurücksetzen des Hinweistextes und des Titels des Popups
	 */
	@Override
	public void reset() {
		T_Hinweis.setText("");
		setTitle("");
		
	}

}
