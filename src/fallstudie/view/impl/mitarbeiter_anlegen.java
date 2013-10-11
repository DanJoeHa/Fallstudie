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

public class mitarbeiter_anlegen extends JPanel {
	private JPasswordField P_Passwort;
	private JTextField T_Arbeitsgruppe;

	/**
	 * Create the panel.
	 */
	public mitarbeiter_anlegen() {
		setPreferredSize(new Dimension(600, 700));
		setMinimumSize(new Dimension(600, 700));
		setMaximumSize(new Dimension(600, 700));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 600, 700));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//L_Ueberschrift
		JLabel L_Ueberschrift = new JLabel("Mitarbeiter anlegen");
		L_Ueberschrift.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Ueberschrift.setBounds(10, 11, 580, 17);
		add(L_Ueberschrift);
		
		//L_Passwort
		JLabel L_Passwort = new JLabel("Passwort:");
		L_Passwort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Passwort.setBounds(10, 150, 150, 17);
		add(L_Passwort);
		
		//P_Passwort
		P_Passwort = new JPasswordField();
		P_Passwort.setText("");
		P_Passwort.setBounds(170, 145, 181, 20);
		add(P_Passwort);
		
		//B_Passwort_Generieren
		JButton B_Passwort_Generieren = new JButton("generieren");
		B_Passwort_Generieren.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Passwort_Generieren.setBounds(361, 144, 134, 23);
		add(B_Passwort_Generieren);
		
		//C_Rolle
		JComboBox C_Rolle = new JComboBox();
		C_Rolle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		C_Rolle.setBounds(170, 176, 181, 20);
		add(C_Rolle);
				
		//C_Bereich
		JComboBox C_Bereich = new JComboBox();
		C_Bereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		C_Bereich.setBounds(170, 208, 181, 20);
		add(C_Bereich);
			
		//T_Arbeitsgruppe
		T_Arbeitsgruppe = new JTextField();
		T_Arbeitsgruppe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Arbeitsgruppe.setColumns(10);
		T_Arbeitsgruppe.setBounds(170, 239, 181, 20);
		add(T_Arbeitsgruppe);

		//B_SucheArbeitshgruppe mit Lupe
		JButton B_SucheArbeitshgruppe = new JButton("suchen");
		B_SucheArbeitshgruppe.setIcon(null);
		B_SucheArbeitshgruppe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_SucheArbeitshgruppe.setBounds(361, 238, 106, 23);
		add(B_SucheArbeitshgruppe);
		
		//B_Zurücksetzen
		JButton B_Zurücksetzen = new JButton("Zur\u00FCcksetzten");
		B_Zurücksetzen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Zurücksetzen.setBounds(70, 382, 150, 23);
		add(B_Zurücksetzen);
		
		//B_Speichern
		JButton B_Speichern = new JButton("Speichern");
		B_Speichern.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Speichern.setBounds(305, 384, 150, 23);
		add(B_Speichern);
		
	}
}
