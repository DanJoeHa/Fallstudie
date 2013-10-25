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
/**
 * Popup zum Bestätigen
 * @author Marc
 *
 */
public class BestaetigenPopup extends JDialog implements View{

	private static final long serialVersionUID = -8630370870519774622L;
	/**
	 * Panel für Inhalt
	 */
	private final JPanel contentPanel = new JPanel();
	/**
	 * Speichern Button
	 */
	private JButton B_speichern;
	/**
	 * Abbrechen Button
	 */
	private JButton B_abbrechen;
	/**
	 * Hinweis Text
	 */
	private JTextArea txtrWollenSieWirklich;
	
	

	/**
	 * Create the dialog.
	 */
	public BestaetigenPopup() {
		setVisible(true);
		setEnabled(true);
		setModal(true);
		setTitle("");
		setResizable(false);
		this.setSize(452, 167);
		this.setLocationRelativeTo(null);		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		txtrWollenSieWirklich = new JTextArea();
		txtrWollenSieWirklich.setBackground(SystemColor.menu);
		txtrWollenSieWirklich.setText("");
		txtrWollenSieWirklich.setEditable(false);
		contentPanel.add(txtrWollenSieWirklich);
	
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		contentPanel.setFocusable(true);
		contentPanel.setRequestFocusEnabled(true);
		contentPanel.grabFocus();
		
		B_speichern = new JButton("Ja");
		//B_speichern.addActionListener(new ActionListener() {
		//	public void actionPerformed(ActionEvent arg0) {
		//	}
		//});
		B_speichern.setActionCommand("Ja");
		buttonPane.add(B_speichern);
//		B_speichern.setDefaultCapable(true);
		B_speichern.requestFocusInWindow();
		//B_speichern.setFocusable(true);
			
		B_abbrechen = new JButton("Nein");
		B_abbrechen.setActionCommand("Nein");
		buttonPane.add(B_abbrechen);
		//B_abbrechen.setFocusable(true);
			
		
	}
	public String hatFocus(){
		if(this.B_abbrechen.isFocusOwner()== true){
			return "popupNein";
		}
		else{
			return "popupJa";
		}
	}
	/**
	 * Button Namen setzen
	 * @param ButtonJa
	 * @param ButtonNein
	 */
	public void setButtonName(String ButtonJa, String ButtonNein ){
		this.B_speichern.setText(ButtonJa);
		this.B_speichern.setActionCommand(ButtonJa);
		this.B_abbrechen.setText(ButtonNein);
		this.B_abbrechen.setActionCommand(ButtonNein);
		
	}
	/**
	 * Text setzen
	 * @param text
	 */
	public void setAusgabe(String text){
		txtrWollenSieWirklich.setText(text);
	}
	/**
	 * Setzt Controller
	 */
	@Override
	public void setController(Controller c) {
		this.B_speichern.addActionListener(c);
		this.B_abbrechen.addActionListener(c);
		this.B_speichern.addKeyListener(c);
		this.B_abbrechen.addKeyListener(c);
	}
/**
 * Setzt Titel des Popups zurück
 */
	@Override
	public void reset() {
		setTitle("");
	}

}
