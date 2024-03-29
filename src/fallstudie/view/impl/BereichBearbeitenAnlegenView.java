package fallstudie.view.impl;

import java.awt.SystemColor;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;

import javax.swing.JLabel;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JTextField;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;
/**
 * View zum Berarbeiten und Anlegen von Bereichen
 * @author Marc
 *
 */
public class BereichBearbeitenAnlegenView extends OrgeinheitBearbeitenAnlegen implements View {
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1610717377071294522L;
	/**
	 * Suchbegriff Textfeld
	 */
	private JTextField T_BereichsleiterSuchbegriff;
	/**
	 * Suchen Button
	 */
	private JButton B_BereichsleiterSuche;
	/**
	 * Create the panel.
	 */
	public BereichBearbeitenAnlegenView() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 655));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		
		//L_Bereichsleiter
		JLabel L_Bereichsleiter = new JLabel("Bereichsleiter:");
		L_Bereichsleiter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Bereichsleiter.setBounds(30, 130, 150, 30);
		add(L_Bereichsleiter);
		
		//T_Bereichsleiter_Suchbegriff
		T_BereichsleiterSuchbegriff = new JTextField();
		T_BereichsleiterSuchbegriff.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_BereichsleiterSuchbegriff.setBounds(200, 130, 220, 30);
		add(T_BereichsleiterSuchbegriff);
		T_BereichsleiterSuchbegriff.setColumns(10);
		
		//B_Bereichsleiter_Suche
		B_BereichsleiterSuche = new JButton("Suchen");
		B_BereichsleiterSuche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_BereichsleiterSuche.setBounds(440, 130, 150, 30);
		add(B_BereichsleiterSuche);
		
	}
	/**
	 * Check auf welchem Button der Focus liegt
	 * @return
	 */
	public String hatFocus(){
		String ausgabe;
		if(this.B_Speichern.isFocusOwner()){
			ausgabe = "buttonSpeichern";
		}
		else if(this.T_Kurzbezeichnung.isFocusOwner()){
			ausgabe =  "kurzbezeichnung";
		}
		else if(this.T_Bezeichnung.isFocusOwner()){
			ausgabe = "bezeichnung";
		}else{
			ausgabe = "nichts";
		}
		return ausgabe;
	}
	
	/**
	 * Leiter in Textfeld setzen
	 * @param benutzerid
	 */
	public void setLeiter(String benutzerid){
		this.T_BereichsleiterSuchbegriff.setText(benutzerid);
	}
	/**
	 * Leiter Textfeld auslesen
	 * @return Leiter
	 */
	public String getLeiter(){
		return this.T_BereichsleiterSuchbegriff.getText();
	}
	/**
	 * Setze Controller
	 */
	@Override
	public void setController(Controller c) {
		this.B_BereichsleiterSuche.addActionListener(c);
		this.B_Speichern.addActionListener(c);
		this.B_Zuruecksetzen.addActionListener(c);
		this.B_Speichern.addKeyListener(c);
//		this.T_Bezeichnung.addKeyListener(c);
//		this.T_Kurzbezeichnung.addKeyListener(c);
	}
/**
 * Textfelder zurücksetzen
 */
	@Override
	public void reset() {
		this.T_BereichsleiterSuchbegriff.setText("");
		this.T_Bezeichnung.setText("");
		this.T_Kurzbezeichnung.setText("");
	}
	
}
