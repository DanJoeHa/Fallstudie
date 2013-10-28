package fallstudie.view.impl;

import java.awt.SystemColor;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;

import javax.swing.JLabel;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;
/**
 * View zum Anlegen und Bearbeiten von Arbeitsgruppen
 * @author Marc
 *
 */
public class ArbeitsgruppeBearbeitenAnlegenView extends OrgeinheitBearbeitenAnlegen implements View{
	/**
	 * Serial
	 */
	private static final long serialVersionUID = -2078487615780247796L;
	/**
	 * TextFeld AGLeiter suchen
	 */
	private JTextField T_AGLeiterSuchbegriff;
	/**
	 * Combobox der Bereiche
	 */
	private JComboBox<String> C_Bereich;
	/**
	 * Button Suche
	 */
	private JButton B_AGLeiterSuche;

	/**
	 * Create the panel.
	 */
	public ArbeitsgruppeBearbeitenAnlegenView() {
		T_Bezeichnung.setLocation(200, 80);
		T_Kurzbezeichnung.setLocation(200, 30);
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 655));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//L_AG_Leiter
		JLabel L_AGLeiter = new JLabel("Gruppenleiter:");
		L_AGLeiter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_AGLeiter.setBounds(30, 130, 150, 30);
		add(L_AGLeiter);
		
		//L_Bereich
		JLabel L_Bereich = new JLabel("Bereich:");
		L_Bereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Bereich.setBounds(30, 180, 150, 30);
		add(L_Bereich);
		
		//T_AG_Leiter_Suchbegriff
		T_AGLeiterSuchbegriff = new JTextField();
		T_AGLeiterSuchbegriff.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_AGLeiterSuchbegriff.setColumns(10);
		T_AGLeiterSuchbegriff.setBounds(200, 130, 220, 30);
		add(T_AGLeiterSuchbegriff);
		
		//C_Bereich
		C_Bereich = new JComboBox<String>();
		C_Bereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		C_Bereich.setBounds(200, 180, 390, 30);
		add(C_Bereich);
		
		//B_AG_Leiter_Suche mit Lupe
		B_AGLeiterSuche = new JButton("Suchen");
		B_AGLeiterSuche.setIcon(null);
		B_AGLeiterSuche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_AGLeiterSuche.setBounds(440, 130, 150, 30);
		add(B_AGLeiterSuche);
		

	}
	/**
	 * Füllt die Auswahlbox mit den Bereichen
	 * @param bereiche
	 */
	public void setBereich(String[] bereiche){
		for( int i = 0; i < bereiche.length; i++){
			this.C_Bereich.addItem( bereiche[i] );
		}
		
	}
	
	/**
	 * Füllt die Auswahlbix nit den Bereich und legt zusätzlich den vorausgewählten Bereich fest
	 * @param bereiche
	 * @param aktuellerBereich
	 */
	public void setBereich(String[] bereiche, String aktuellerBereich){
		this.setBereich(bereiche);
		for( int i = 0; i < bereiche.length; i++){
			if( bereiche[i].equals(aktuellerBereich) ) {
				this.C_Bereich.setSelectedIndex(i);
				break;
			}
		}
	}
	/**
	 * Gibt den ausgewählten Bereich aus der AuswahlBox zurück
	 * @return Bezeichnung des Bereichs
	 */
	public String getBereich(){
		return this.C_Bereich.getSelectedItem().toString();
	}
	
	/**
	 * Arbeitsgruppenleiter Feld setzen
	 * @param Benutzername des Mitarbeiters
	 */
	public void setAGLeiter(String benutzerid){
		this.T_AGLeiterSuchbegriff.setText(benutzerid);
	}
	
	/**
	 * Gibt den Benutzernamen des Arbeitsgruppenleiters zurück
	 * @return Benutzername des Arbeitsgruppenleiters
	 */
	public String getAGLeiter(){
		return this.T_AGLeiterSuchbegriff.getText();
	}
	/**
	 * Setzt Controller
	 */
	@Override
	public void setController(Controller c) {
		this.B_Speichern.addActionListener(c);
		this.B_Zuruecksetzen.addActionListener(c);
		this.B_AGLeiterSuche.addActionListener(c);
		this.B_Speichern.addKeyListener(c);
		this.T_AGLeiterSuchbegriff.addKeyListener(c);
		this.T_Bezeichnung.addKeyListener(c);
		this.T_Kurzbezeichnung.addKeyListener(c);
		this.C_Bereich.addKeyListener(c);
	}
/**
 * Setzt Textfelder zurück
 */
	@Override
	public void reset() {
		this.T_AGLeiterSuchbegriff.setText("");
		this.T_Bezeichnung.setText("");
		this.T_Kurzbezeichnung.setText("");
		
	}
	
	/**
	 * Check auf welchem Button der Focus liegt
	 * @return
	 */
	public String hatFocus(){
		String ausgabe;
		if(this.B_Speichern.isFocusOwner()){
			ausgabe = "buttonSpeichern";
		}else{
			ausgabe = "nichts";
		}
		return ausgabe;
	}
	/**
	 * setzt Focus auf Button
	 */
	public void setzeFocus(){
		this.B_Speichern.requestFocusInWindow();
	}
}
