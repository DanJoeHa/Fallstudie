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

public class PasswortAendernView extends JPanel implements View {


	private static final long serialVersionUID = -5269473857849047666L;
	private JPasswordField P_AltesPasswort;
	private JPasswordField P_WdhPasswort;
	private JPasswordField P_NeuesPasswort;

	/**
	 * Sicht erstellen.
	 * 
	 * @author Natalja
	 * @version 1.0
	 */
	public PasswortAendernView() {
		setPreferredSize(new Dimension(600, 700));
		setMinimumSize(new Dimension(600, 700));
		setMaximumSize(new Dimension(600, 700));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 600, 700));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//L_Ueberschrift
		JLabel L_Ueberschrift = new JLabel("Passwort \u00C4ndern");
		L_Ueberschrift.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Ueberschrift.setBounds(10, 11, 580, 17);
		add(L_Ueberschrift);
		
		//L_Hinweis
		JLabel L_Hinweis = new JLabel("Bitte geben Sie Ihren Benutzernamen und Ihr Passwort ein.");
		L_Hinweis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Hinweis.setBounds(10, 39, 580, 20);
		add(L_Hinweis);
		
		//L_AltesPasswort
		JLabel L_AltesPasswort = new JLabel("Altes Passwort:");
		L_AltesPasswort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_AltesPasswort.setBounds(10, 107, 210, 17);
		add(L_AltesPasswort);
		
		//L_NeuesPasswort
		JLabel L_NeuesPasswort = new JLabel("Neues Passwort:");
		L_NeuesPasswort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_NeuesPasswort.setBounds(10, 148, 210, 17);
		add(L_NeuesPasswort);
		
		//L_WdhPasswort
		JLabel L_WdhPasswort = new JLabel("Neues Passwort wiederholen:");
		L_WdhPasswort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_WdhPasswort.setBounds(10, 192, 210, 17);
		add(L_WdhPasswort);

		//P_AltesPasswort
		P_AltesPasswort = new JPasswordField();
		P_AltesPasswort.setText("");
		P_AltesPasswort.setBounds(258, 107, 141, 20);
		add(P_AltesPasswort);
		
		//P_NeuesPasswort
		P_NeuesPasswort = new JPasswordField();
		P_NeuesPasswort.setBounds(258, 148, 141, 20);
		add(P_NeuesPasswort);
		
		//P_WdhPasswort
		P_WdhPasswort = new JPasswordField();
		P_WdhPasswort.setText("");
		P_WdhPasswort.setBounds(258, 192, 141, 20);
		add(P_WdhPasswort);

		//B_Speichern
		JButton B_Speichern = new JButton("Speichern");
		B_Speichern.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Speichern.setBounds(306, 280, 150, 23);
		add(B_Speichern);
		
	}

	/**
	 * liest die Eingabe aus dem Textfeld "altesPasswort" aus und gibt diese zur�ck
	 * 
	 * @author Johannes
	 * @version 1.0
	 * @return (String) altes Passwort
	 */
	public String getAltesPasswort(){
		return String.valueOf( this.P_AltesPasswort.getPassword());
	}
	
	/**
	 * liest die Eingabe aus dem Textfeld "neuesPasswort" aus und gibt diese zur�ck
	 * 
	 * @author Johannes
	 * @version 1.0
	 * @return (String) Passwortwiederholung
	 */
	public String getWdhPasswort(){
		return String.valueOf( this.P_WdhPasswort.getPassword());
	}
	
	/**
	 * liest die Eingabe aus dem Textfeld "WdhPasswort" aus und gibt diese zur�ck
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
		this.P_AltesPasswort.addActionListener(c);
		this.P_NeuesPasswort.addActionListener(c);
		this.P_WdhPasswort.addActionListener(c);		
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
