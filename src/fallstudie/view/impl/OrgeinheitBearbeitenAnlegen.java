package fallstudie.view.impl;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class OrgeinheitBearbeitenAnlegen extends JPanel {
	private JTextField T_Kurzbezeichnung;
	private JTextField T_Bezeichnung;
	/**
	 * Create the panel.
	 */
	public OrgeinheitBearbeitenAnlegen() {
		setPreferredSize(new Dimension(620, 660));
		setMinimumSize(new Dimension(620, 660));
		setMaximumSize(new Dimension(620, 660));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 660));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
				
		//L_Kurzbezeichnung
		JLabel L_Kurzbezeichnung = new JLabel("Kurzbezeichnung:");
		L_Kurzbezeichnung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Kurzbezeichnung.setBounds(30, 100, 150, 30);
		add(L_Kurzbezeichnung);
				
		//L_Bezeichnung
		JLabel L_Bezeichnung = new JLabel("Bezeichnung:");
		L_Bezeichnung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Bezeichnung.setBounds(30, 150, 150, 30);
		add(L_Bezeichnung);
				
		//T_Kurzbezeichnung
		T_Kurzbezeichnung = new JTextField();
		T_Kurzbezeichnung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Kurzbezeichnung.setBounds(200, 100, 300, 30);
		add(T_Kurzbezeichnung);
		T_Kurzbezeichnung.setColumns(10);
				
		//T_Bezeichnung
		T_Bezeichnung = new JTextField();
		T_Bezeichnung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Bezeichnung.setColumns(10);
		T_Bezeichnung.setBounds(200, 150, 300, 30);
		add(T_Bezeichnung);
				
		//B_Zuruecksetzen
		JButton B_Zuruecksetzen = new JButton("Zur\u00FCcksetzten");
		B_Zuruecksetzen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Zuruecksetzen.setBounds(30, 600, 150, 30);
		add(B_Zuruecksetzen);
				
		//B_Speichern
		JButton B_Speichern = new JButton("Speichern");
		B_Speichern.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Speichern.setBounds(440, 600, 150, 30);
		add(B_Speichern);	

	}
}
