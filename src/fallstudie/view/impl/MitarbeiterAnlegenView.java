package fallstudie.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import fallstudie.controller.interfaces.Controller;

public class MitarbeiterAnlegenView extends LayoutMitarbeiter{

	private static final long serialVersionUID = 1947090676451323070L;
	private JTextField T_Arbeitsgruppe;
	
	private JButton B_Speichern;
	private JComboBox<String> C_Rolle;
	private JComboBox<String> C_Bereich;
	private JButton B_SucheArbeitsgruppe, B_Zuruecksetzen;

	/**
	 * Create the panel.
	 */
	public MitarbeiterAnlegenView() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//Blende die Arbeitsgruppe nach default aus. Da Bereichsleiter als erstes angezeigt wird.		
		LayoutMitarbeiter.L_Arbeitsgruppe.setVisible(false);
		
		//C_Rolle
		C_Rolle = new JComboBox<String>();
		C_Rolle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		C_Rolle.setBounds(200, 230, 390, 30);
		add(C_Rolle);
		C_Rolle.addActionListener(new ActionListener() {
			//Hilfsvariable first_time
			int first_time=0;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String wahl = C_Rolle.getSelectedItem().toString();
				if(first_time!=0){
					if(wahl.matches("Sachbearbeiter")||wahl.matches("Gruppenleiter")){
						//Arbeitsgruppe anzeigen
						T_Arbeitsgruppe.setVisible(true);
						B_SucheArbeitsgruppe.setVisible(true);
						LayoutMitarbeiter.L_Arbeitsgruppe.setVisible(true);
						//Bereich ausblenden
						LayoutMitarbeiter.L_Bereich.setVisible(false);
						C_Bereich.setVisible(false);
						revalidate();
					} 
					else if(wahl.matches("Fachbereichsorganisation")||wahl.matches("Zentralbereichsleiter")){
						//Arbeitsgruppe ausblenden
						T_Arbeitsgruppe.setVisible(false);
						B_SucheArbeitsgruppe.setVisible(false);
						LayoutMitarbeiter.L_Arbeitsgruppe.setVisible(false);
						//Bereich ausblenden
						LayoutMitarbeiter.L_Bereich.setVisible(false);
						C_Bereich.setVisible(false);
						revalidate();
					}
					else{
						//Arbeitsgruppe ausblenden
						T_Arbeitsgruppe.setVisible(false);
						B_SucheArbeitsgruppe.setVisible(false);
						LayoutMitarbeiter.L_Arbeitsgruppe.setVisible(false);
						//Bereich anzeigen
						LayoutMitarbeiter.L_Bereich.setVisible(true);
						C_Bereich.setVisible(true);
						revalidate();
					}
				}
				first_time++;
			}
		});
				
		//C_Bereich
		C_Bereich = new JComboBox<String>();
		C_Bereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		C_Bereich.setBounds(200, 280, 390, 30);
		add(C_Bereich);
			
		//T_Arbeitsgruppe
		T_Arbeitsgruppe = new JTextField();
		T_Arbeitsgruppe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Arbeitsgruppe.setColumns(10);
		T_Arbeitsgruppe.setBounds(200, 280, 220, 30);
		T_Arbeitsgruppe.setVisible(false);
		add(T_Arbeitsgruppe);

		//B_SucheArbeitsgruppe
		B_SucheArbeitsgruppe = new JButton("Suchen");
		B_SucheArbeitsgruppe.setIcon(null);
		B_SucheArbeitsgruppe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_SucheArbeitsgruppe.setBounds(440, 280, 150, 30);
		B_SucheArbeitsgruppe.setVisible(false);
		add(B_SucheArbeitsgruppe);
		
		//B_Zuruecksetzen
		B_Zuruecksetzen = new JButton("Zurücksetzen");
		B_Zuruecksetzen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Zuruecksetzen.setBounds(30, 600, 150, 30);
		add(B_Zuruecksetzen);
		
		//B_Speichern
		B_Speichern = new JButton("Speichern");
		B_Speichern.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Speichern.setBounds(440, 600, 150, 30);
		add(B_Speichern);
		
	}
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
	 * Setzt Controller
	 */
	public void setController(Controller c) {
		this.B_Speichern.addActionListener(c);
		this.B_SucheArbeitsgruppe.addActionListener(c);
		this.B_Zuruecksetzen.addActionListener(c);
		this.B_Speichern.addKeyListener(c);
	}

	/**
	 * Setzt Textfelder zurück
	 */
	public void reset() {
		this.T_Arbeitsgruppe.setText("");
		this.T_Passwort1.setText("");
		this.T_Benutzername.setText("");
		this.T_Nachname.setText("");
		this.T_Vorname.setText("");
	}
	
	/**
	 * Füllt AuswahlBox mit Rollen
	 * @param rollen
	 */
	public void setRolle(String[] rollen){
		for( int i = 0; i < rollen.length; i++){
			this.C_Rolle.addItem( rollen[i] );
		}
	}
	/**
	 * Füllt AuswahlBox mit Bereichen
	 * @param bereiche
	 */
	public void setBereich(String[] bereiche){
		for( int i = 0; i < bereiche.length; i++){
			this.C_Bereich.addItem( bereiche[i] );
		}
	}
	
	/**
	 * Gibt ausgewählte Rolle aus der AuswahlBox zurück
	 * @return Rolle
	 */
	public String getRolle(){
		return C_Rolle.getSelectedItem().toString();
	}
	
	/**
	 * Gibt ausgewählten Bereich aus der AuswahlBox zurück
	 * @return Bereich
	 */
	public String getBereich(){
		return C_Bereich.getSelectedItem().toString();
	}
	/**
	 * Gibt Arbeitsgruppe zurück
	 * @return Arbeitsgruppe
	 */
	public String getArbeitsgruppe(){
		return T_Arbeitsgruppe.getText();
	}
	/**
	 * Füllt Textfeld der Arbeitsgruppe
	 * @param arbeitsgruppe
	 */
	public void setArbeitsgruppe(String arbeitsgruppe)
	{
		T_Arbeitsgruppe.setText(arbeitsgruppe);
	}
}
