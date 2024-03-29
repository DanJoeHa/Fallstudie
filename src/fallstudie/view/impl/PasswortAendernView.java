package fallstudie.view.impl;

import javax.swing.JPanel;

import java.awt.SystemColor;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;

import javax.swing.JLabel;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPasswordField;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * View zum Ändern von Passwörtern
 * @author Marc
 *
 */
public class PasswortAendernView extends JPanel implements View {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -5269473857849047666L;
	/**
	 * Altes Passwort Passwortfeld
	 */
	private JPasswordField P_AltesPasswort;
	/**
	 * Wiederholtes Passwort Passwortfeld
	 */
	private JPasswordField P_WdhPasswort;
	/**
	 * Neues Passwort Passwortfeld
	 */
	private JPasswordField P_NeuesPasswort;
	/**
	 * Speichern Button
	 */
	private JButton B_Speichern;

	/**
	 * Sicht erstellen.
	 * 
	 * @author Natalja
	 * @version 1.0
	 */
	public PasswortAendernView() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 655));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//L_Hinweis
		JLabel L_Hinweis = new JLabel("Bitte geben Sie Ihren Benutzernamen und Ihr Passwort ein.");
		L_Hinweis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Hinweis.setBounds(30, 30, 560, 30);
		add(L_Hinweis);
		
		//L_AltesPasswort
		JLabel L_AltesPasswort = new JLabel("Altes Passwort:");
		L_AltesPasswort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_AltesPasswort.setBounds(30, 80, 200, 30);
		add(L_AltesPasswort);
		
		//L_NeuesPasswort
		JLabel L_NeuesPasswort = new JLabel("Neues Passwort:");
		L_NeuesPasswort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_NeuesPasswort.setBounds(30, 130, 200, 30);
		add(L_NeuesPasswort);
		
		//L_WdhPasswort
		JLabel L_WdhPasswort = new JLabel("Neues Passwort wiederholen:");
		L_WdhPasswort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_WdhPasswort.setBounds(30, 180, 200, 30);
		add(L_WdhPasswort);

		//P_AltesPasswort
		P_AltesPasswort = new JPasswordField();
		P_AltesPasswort.setText("");
		P_AltesPasswort.setBounds(250, 80, 340, 30);
		add(P_AltesPasswort);
		
		//P_NeuesPasswort
		P_NeuesPasswort = new JPasswordField();
		P_NeuesPasswort.setBounds(250, 130, 340, 30);
		add(P_NeuesPasswort);
		
		//P_WdhPasswort
		P_WdhPasswort = new JPasswordField();
		P_WdhPasswort.setText("");
		P_WdhPasswort.setBounds(250, 180, 340, 30);
		add(P_WdhPasswort);

		//B_Speichern
		B_Speichern = new JButton("Speichern");
		B_Speichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		B_Speichern.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Speichern.setBounds(440, 600, 150, 30);
		add(B_Speichern);
		
	}

	/**
	 * Liest die Eingabe aus dem Textfeld "altesPasswort" aus und gibt diese zurück
	 * 
	 * @author Johannes
	 * @version 1.0
	 * @return (String) altes Passwort
	 */
	public String getAltesPasswort(){
		return String.valueOf( this.P_AltesPasswort.getPassword());
	}
	
	/**
	 * Liest die Eingabe aus dem Textfeld "neuesPasswort" aus und gibt diese zurück
	 * 
	 * @author Johannes
	 * @version 1.0
	 * @return (String) Passwortwiederholung
	 */
	public String getWdhPasswort(){
		return String.valueOf( this.P_WdhPasswort.getPassword());
	}
	
	/**
	 * Liest die Eingabe aus dem Textfeld "WdhPasswort" aus und gibt diese zurück
	 * 
	 * @author Johannes
	 * @version 1.0
	 * @return (String) Passwort
	 */
	public String getNeuesPasswort(){
		return String.valueOf( this.P_NeuesPasswort.getPassword());
	}
	
	/**
	 * ActionListener auf die Buttons setzen
	 * 
	 * @author Johannes
	 * @version 1.0
	 * @param controller
	 */
	@Override
	public void setController(Controller c) {
		this.B_Speichern.addActionListener(c);	
		this.B_Speichern.addKeyListener(c);
		this.P_WdhPasswort.addKeyListener(c);
	}

	/**
	 * Textfelder auf Maske leeren
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	@Override
	public void reset() {
		this.P_AltesPasswort.setText("");
		this.P_NeuesPasswort.setText("");
		this.P_WdhPasswort.setText("");
	}
}
