package fallstudie.view.impl;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.SystemColor;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;
/**
 * Stellt für Mitarbeiter bearbeiten und anlegen grafische GUI Elemente, die vererbt werden, zur Verfügung
 * @author Marc
 *
 */
public class LayoutMitarbeiter extends JPanel implements View{

	private static final long serialVersionUID = -5374763696510559443L;
	protected JTextField T_Vorname;
	protected JTextField T_Nachname;
	protected JTextField T_Benutzername;
	protected JTextField T_Passwort1;
	protected static JLabel L_Bereich;
	protected static JLabel L_Arbeitsgruppe;

	/**
	 * Create the panel.
	 */
	public LayoutMitarbeiter() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 655));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//L_Vorname
		JLabel L_Vorname = new JLabel("Vorname:");
		L_Vorname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Vorname.setBounds(30, 30, 150, 30);
		add(L_Vorname);
		
		//L_Nachname
		JLabel L_Nachname = new JLabel("Nachname:");
		L_Nachname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Nachname.setBounds(30, 80, 150, 30);
		add(L_Nachname);
		
		//L_Benutzername
		JLabel L_Benutzername = new JLabel("Benutzername:");
		L_Benutzername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Benutzername.setBounds(30, 130, 150, 30);
		add(L_Benutzername);
		
		//L_Bereich
		L_Bereich = new JLabel("Bereich:");
		L_Bereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Bereich.setBounds(30, 280, 150, 30);
		add(L_Bereich);
		
		//L_Rolle
		JLabel L_Rolle = new JLabel("Rolle:");
		L_Rolle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Rolle.setBounds(30, 230, 150, 30);
		add(L_Rolle);
		
		//L_Arbeitsgruppe
		L_Arbeitsgruppe = new JLabel("Arbeitsgruppe:");
		L_Arbeitsgruppe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Arbeitsgruppe.setBounds(30, 280, 150, 30);
		add(L_Arbeitsgruppe);
		
		//T_Vorname
		T_Vorname = new JTextField();
		T_Vorname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Vorname.setBounds(200, 30, 390, 30);
		add(T_Vorname);
		T_Vorname.setColumns(10);
		
		//T_Nachname
		T_Nachname = new JTextField();
		T_Nachname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Nachname.setColumns(10);
		T_Nachname.setBounds(200, 80, 390, 30);
		add(T_Nachname);
		
		//T_Benutzername
		T_Benutzername = new JTextField();
		T_Benutzername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Benutzername.setColumns(10);
		T_Benutzername.setBounds(200, 130, 390, 30);
		add(T_Benutzername);
		
		//L_Passwort1 280
		JLabel L_Passwort1 = new JLabel("Passwort:");
		L_Passwort1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Passwort1.setBounds(30, 180, 150, 30);
		add(L_Passwort1);
		
		//T_Passwort1
		T_Passwort1 = new JTextField();
		T_Passwort1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Passwort1.setBounds(200, 180, 220, 30);
		add(T_Passwort1);
		T_Passwort1.setColumns(10);
		
		//B_PasswortGenerieren
		JButton B_PasswortGenerieren = new JButton("Generieren");
		B_PasswortGenerieren.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_PasswortGenerieren.setBounds(440, 180, 150, 30);
		add(B_PasswortGenerieren);
		
		B_PasswortGenerieren.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder zufallsPasswort = new StringBuilder("xxxxxxxx");
		        java.util.Random rGen=new java.util.Random();
		        char[] passArray=new char[8];
		        
		        for(int i=0;i<8;i++) {
		            passArray[i]=(char)(rGen.nextInt(26)+97);
		            zufallsPasswort.setCharAt(i, passArray[i]);
		        }
		        
				T_Passwort1.setText(zufallsPasswort.toString());
			}
	    	
	    });
		
	}
	/**
	 * Textfeld Vorname füllen
	 * @param vorname
	 */
	public void setVorname(String vorname)
	{
		T_Vorname.setText(vorname);
	}
	/**
	 * Textfeld Nachname füllen
	 * @param nachname
	 */
	public void setNachname(String nachname)
	{
		T_Nachname.setText(nachname);
	}
	/**
	 * Textfeld Benutzername füllen
	 * @param benutzername
	 */
	public void setBenutzername(String benutzername)
	{
		T_Benutzername.setText(benutzername);
	}
	/**
	 * Gibt Inhalt des Textfeld Vorname zurück
	 * @return Vorname
	 */
	public String getVorname(){
		return T_Vorname.getText();
	}
	/**
	 * Gibt Inhalt des Textfeld Nachname zurück
	 * @return Nachname
	 */
	public String getNachname(){
		return T_Nachname.getText();
	}
	/**
	 * Gibt Inhalt des Textfeld Benutzername zurück
	 * @return Benutzername
	 */
	public String getBenutzername(){
		return T_Benutzername.getText();
	}
	/**
	 * Gibt Inhalt des Textfeld Passwort zurück
	 * @return Passwort
	 */
	public String getPasswort(){
		return T_Passwort1.getText();
	}
	
	@Override
	public void reset() {
		
	}

	@Override
	public void setController(Controller c) {
		
	}
}
