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
import javax.swing.JPasswordField;

public class MitarbeiterAnlegenView extends JPanel {
	private JTextField T_Arbeitsgruppe;
	private JTextField T_Passwort1;

	/**
	 * Create the panel.
	 */
	public MitarbeiterAnlegenView() {
		setPreferredSize(new Dimension(620, 660));
		setMinimumSize(new Dimension(620, 660));
		setMaximumSize(new Dimension(620, 660));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 660));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//L_Passwort1
		JLabel L_Passwort1 = new JLabel("Passwort:");
		L_Passwort1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Passwort1.setBounds(30, 400, 150, 30);
		add(L_Passwort1);
		
		//T_Passwort1
		T_Passwort1 = new JTextField();
		T_Passwort1.setBounds(200, 400, 220, 30);
		add(T_Passwort1);
		T_Passwort1.setColumns(10);
		
		//B_PasswortGenerieren
		JButton B_PasswortGenerieren = new JButton("Generieren");
		B_PasswortGenerieren.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_PasswortGenerieren.setBounds(440, 400, 150, 30);
		add(B_PasswortGenerieren);
		
		//C_Rolle
		JComboBox C_Rolle = new JComboBox();
		C_Rolle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		C_Rolle.setBounds(200, 250, 300, 30);
		add(C_Rolle);
				
		//C_Bereich
		JComboBox C_Bereich = new JComboBox();
		C_Bereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		C_Bereich.setBounds(200, 300, 300, 30);
		add(C_Bereich);
			
		//T_Arbeitsgruppe
		T_Arbeitsgruppe = new JTextField();
		T_Arbeitsgruppe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Arbeitsgruppe.setColumns(10);
		T_Arbeitsgruppe.setBounds(200, 350, 220, 30);
		add(T_Arbeitsgruppe);

		//B_SucheArbeitsgruppe
		JButton B_SucheArbeitsgruppe = new JButton("Suchen");
		B_SucheArbeitsgruppe.setIcon(null);
		B_SucheArbeitsgruppe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_SucheArbeitsgruppe.setBounds(440, 350, 150, 30);
		add(B_SucheArbeitsgruppe);
		
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
