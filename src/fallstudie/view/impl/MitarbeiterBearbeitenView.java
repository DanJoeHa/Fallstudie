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
/**
 * View zum Bearbeiten von Mitarbeitern
 * erbt von MitarbeiterLayout die GUI Elemente
 * @author Marc
 *
 */
public class MitarbeiterBearbeitenView extends LayoutMitarbeiter {
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 2054079907794135044L;
	/**
	 * Textfeld Arbeitsgruppe
	 */
	private JTextField T_Arbeitsgruppe;
	/**
	 * Speichern, Abbrechen, Suchen Button
	 */
	private JButton B_Speichern, B_Abbrechen, B_SucheArbeitsgruppe;
	/**
	 * Bereich AuswahlBox
	 */
	private JComboBox<String> C_Bereich;
	/**
	 * Rolle AuswahlBox
	 */
	private JComboBox<String> C_Rolle;

	/**
	 * Create the panel.
	 */
	public MitarbeiterBearbeitenView() {
		T_Benutzername.setEditable(false);
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//C_Bereich
		C_Bereich = new JComboBox<String>();
		C_Bereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		C_Bereich.setBounds(200, 280, 390, 30);
		add(C_Bereich);
				
		//C_Rolle
		C_Rolle = new JComboBox<String>();
		C_Rolle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		C_Rolle.setBounds(200, 230, 390, 30);
		add(C_Rolle);
		C_Rolle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String wahl = C_Rolle.getSelectedItem().toString();
					if(wahl.matches("Sachbearbeiter") || wahl.matches("Gruppenleiter")){
						//Arbeitsgruppe anzeigen
						T_Arbeitsgruppe.setVisible(true);
						B_SucheArbeitsgruppe.setVisible(true);
						LayoutMitarbeiter.L_Arbeitsgruppe.setVisible(true);
						//Bereich ausblenden
						LayoutMitarbeiter.L_Bereich.setVisible(false);
						C_Bereich.setVisible(false);
						revalidate();
					}
					
					if(wahl.matches("Bereichsleiter")){
						//Arbeitsgruppe ausblenden
						T_Arbeitsgruppe.setVisible(false);
						B_SucheArbeitsgruppe.setVisible(false);
						LayoutMitarbeiter.L_Arbeitsgruppe.setVisible(false);
						//Bereich anzeigen
						LayoutMitarbeiter.L_Bereich.setVisible(true);
						C_Bereich.setVisible(true);
						revalidate();
					}
					
					if(wahl.matches("Fachbereichsorganisation") || wahl.matches("Zentralbereichsleiter")){
						T_Arbeitsgruppe.setVisible(false);
						B_SucheArbeitsgruppe.setVisible(false);
						L_Arbeitsgruppe.setVisible(false);
						L_Bereich.setVisible(false);
						C_Bereich.setVisible(false);
						revalidate();
					}
				}
		});
		
		//T_Arbeitsgruppe
		T_Arbeitsgruppe = new JTextField();
		T_Arbeitsgruppe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Arbeitsgruppe.setColumns(10);
		T_Arbeitsgruppe.setBounds(200, 280, 220, 30);
		add(T_Arbeitsgruppe);

		//B_SucheArbeitsgruppe 
		B_SucheArbeitsgruppe = new JButton("Suchen");
		B_SucheArbeitsgruppe.setIcon(null);
		B_SucheArbeitsgruppe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_SucheArbeitsgruppe.setBounds(440, 280, 150, 30);
		add(B_SucheArbeitsgruppe);

		//B_Abbrechen
		B_Abbrechen = new JButton("Abbrechen");
		B_Abbrechen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Abbrechen.setBounds(30, 600, 150, 30);
		add(B_Abbrechen);
		
		//B_Speichern
		B_Speichern = new JButton("Speichern");
		B_Speichern.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Speichern.setBounds(440, 600, 150, 30);
		add(B_Speichern);

	}
	/**
	 * Gibt den Button mit dem Focus zurück
	 * @return
	 */
	public String hatFocus(){
		String ausgabe;
		if(this.B_Speichern.isFocusOwner()||this.T_Passwort1.isFocusOwner()||this.C_Bereich.isFocusOwner()||this.T_Arbeitsgruppe.isFocusOwner()){
			ausgabe = "buttonSpeichern";
		}else{
			ausgabe = "nichts";
		}
		return ausgabe;
	}
	/**
	 * AuswahlBox mit Rollen füllen und vorausgewählte Rolle festlegen
	 * @param rollen
	 * @param aktuelleRolle
	 */
	public void setRolle(String[] rollen, String aktuelleRolle)
	{
		if(C_Rolle.getItemCount()==0){
			for( int i = 0; i < rollen.length; i++){
				this.C_Rolle.addItem( rollen[i] );
				if( rollen[i].equals(aktuelleRolle) ) {
					this.C_Rolle.setSelectedIndex(i);
				}
			}
		}
		if( aktuelleRolle.equals("Fachbereichsorganisation") || aktuelleRolle.equals("Zentralbereichsleiter") ){
			this.B_SucheArbeitsgruppe.setVisible(false);
			this.T_Arbeitsgruppe.setVisible(false);
			L_Arbeitsgruppe.setVisible(false);
			L_Bereich.setVisible(false);
			this.C_Bereich.setVisible(false);
		}
	}
	/**
	 * Auswahlbox mit Rollen füllen
	 * @param rollen
	 */
	public void setRolle( String[] rollen )
	{
		for( int i = 0; i < rollen.length; i++){
			this.C_Rolle.addItem( rollen[i] );
		}
	}
	/**
	 * Arbeitsgruppe in Textfeld setzen
	 * @param arbeitsgruppe
	 */
	public void setArbeitsgruppe(String arbeitsgruppe)
	{
		T_Arbeitsgruppe.setText(arbeitsgruppe);
	}
	/**
	 * AuswahlBox mit Bereichen füllen und vorausgewählter Bereich festlegen
	 * @param bereiche
	 * @param aktuellerBereich
	 */
	public void setBereich(String[] bereiche, String aktuellerBereich)
	{
		for( int i = 0; i < bereiche.length; i++){
			this.C_Bereich.addItem( bereiche[i] );
			if( bereiche[i].equals(aktuellerBereich) ) {
				this.C_Bereich.setSelectedIndex(i);
			}
		}
	}
	/**
	 * AuswahlBox mit Bereichen füllen
	 * @param bereiche
	 */
	public void setBereich(String[] bereiche)
	{
		for( int i = 0; i < bereiche.length; i++){
			this.C_Bereich.addItem( bereiche[i] );
		}
	}
	/**
	 * Gibt ausgewählte Rolle zurück
	 * @return ausgewählte Rolle
	 */
	public String getRolle()
	{
		return C_Rolle.getSelectedItem().toString();
	}
	/**
	 * Gibt ausgewählten Bereich zurück
	 * @return ausgewählter Bereich
	 */
	public String getBereich()
	{
		return C_Bereich.getSelectedItem().toString();
	}
	
	/**
	 * Setzt Controller
	 */
	public void setController(Controller c) {
		this.B_Speichern.addActionListener(c);
		this.B_Abbrechen.addActionListener(c);
		this.B_SucheArbeitsgruppe.addActionListener(c);
		this.B_Speichern.addKeyListener(c);
		this.T_Arbeitsgruppe.addKeyListener(c);
		this.T_Passwort1.addKeyListener(c);
		this.C_Rolle.addKeyListener(c);
	}
	/**
	 * Gibt Arbeitsgruppe aus Textfeld zurück
	 * @return Arbeitsgruppe
	 */
	public String getArbeitsgruppe(){
		return T_Arbeitsgruppe.getText();
	}
}
