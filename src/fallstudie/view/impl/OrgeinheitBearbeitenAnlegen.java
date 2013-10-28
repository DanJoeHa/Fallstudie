package fallstudie.view.impl;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
/**
 *  Stellt für Organisationseinheiten(Bereiche und Arbeitsgruppe) bearbeiten und anlegen grafische GUI Elemente, die vererbt werden, zur Verfügung
 * @author Marc
 *
 */
public abstract class OrgeinheitBearbeitenAnlegen extends JPanel{
	/**
	 * Serial
	 */
	private static final long serialVersionUID = -3209996671642895324L;
	/**
	 * Kurzbezeichnung Textfeld
	 */
	protected JTextField T_Kurzbezeichnung;
	/**
	 * Bezeichnung Textfeld
	 */
	protected JTextField T_Bezeichnung;
	/**
	 * Zurücksetzen und Speichern Button
	 */
	protected JButton B_Zuruecksetzen, B_Speichern;
	/**
	 * Create the panel.
	 */
	public OrgeinheitBearbeitenAnlegen() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 655));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
				
		//L_Kurzbezeichnung
		JLabel L_Kurzbezeichnung = new JLabel("Kurzbezeichnung:");
		L_Kurzbezeichnung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Kurzbezeichnung.setBounds(30, 30, 150, 30);
		add(L_Kurzbezeichnung);
				
		//L_Bezeichnung
		JLabel L_Bezeichnung = new JLabel("Beschreibung:");
		L_Bezeichnung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Bezeichnung.setBounds(30, 80, 150, 30);
		add(L_Bezeichnung);
				
		//T_Kurzbezeichnung
		T_Kurzbezeichnung = new JTextField();
		T_Kurzbezeichnung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Kurzbezeichnung.setBounds(200, 30, 390, 30);
		add(T_Kurzbezeichnung);
		T_Kurzbezeichnung.setColumns(10);
				
		//T_Bezeichnung
		T_Bezeichnung = new JTextField();
		T_Bezeichnung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Bezeichnung.setColumns(10);
		T_Bezeichnung.setBounds(200, 80, 390, 30);
		add(T_Bezeichnung);
				
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
	/**
	 * Gibt Kurzbezeichnung zurück
	 * @return Kurzbezeichnung
	 */
	public String getKurzbezeichnung(){
		return this.T_Kurzbezeichnung.getText();
	}
	
	/**
	 * Kurzbezeichnung in Textfeld setzen
	 * @param kurzbezeichnung
	 */
	public void setKurzbezeichnung(String kurzbezeichnung){
		this.T_Kurzbezeichnung.setText(kurzbezeichnung);
	}
	/**
	 * Gibt Bezeichnung zurück
	 * @return Bezeichnung
	 */
	public String getBezeichnung(){
		return this.T_Bezeichnung.getText();
	}
	/**
	 * Bezeichnung in Textfeld setzen
	 * @param bezeichnung
	 */
	public void setBezeichnung(String bezeichnung){
		this.T_Bezeichnung.setText(bezeichnung);
	}
	/**
	 * Name des Abbrechen Button setzen
	 * @param name
	 */
	public void setButtonAbbrechenName(String name){
		this.B_Zuruecksetzen.setText(name);
		this.B_Zuruecksetzen.setActionCommand(name);
	}
}
