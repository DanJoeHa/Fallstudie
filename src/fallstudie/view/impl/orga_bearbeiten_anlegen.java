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

public class orga_bearbeiten_anlegen extends JPanel {
	private JTextField T_Kurzbezeichnung;
	private JTextField T_Bezeichnung;
	/**
	 * Create the panel.
	 */
	public orga_bearbeiten_anlegen() {
		setPreferredSize(new Dimension(600, 700));
		setMinimumSize(new Dimension(600, 700));
		setMaximumSize(new Dimension(600, 700));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 600, 700));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
				
		//L_Kurzbezeichnung
		JLabel L_Kurzbezeichnung = new JLabel("Kurzbezeichnung:");
		L_Kurzbezeichnung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Kurzbezeichnung.setBounds(10, 107, 210, 17);
		add(L_Kurzbezeichnung);
				
		//L_Bezeichnung
		JLabel L_Bezeichnung = new JLabel("Bezeichnung:");
		L_Bezeichnung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Bezeichnung.setBounds(10, 148, 210, 17);
		add(L_Bezeichnung);
				
		//T_Kurzbezeichnung
		T_Kurzbezeichnung = new JTextField();
		T_Kurzbezeichnung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Kurzbezeichnung.setBounds(175, 107, 181, 20);
		add(T_Kurzbezeichnung);
		T_Kurzbezeichnung.setColumns(10);
				
		//T_Bezeichnung
		T_Bezeichnung = new JTextField();
		T_Bezeichnung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Bezeichnung.setColumns(10);
		T_Bezeichnung.setBounds(175, 148, 181, 20);
		add(T_Bezeichnung);
				
		//B_Zurücksetzen
		JButton B_Zurücksetzen = new JButton("Zur\u00FCcksetzten");
		B_Zurücksetzen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Zurücksetzen.setBounds(70, 382, 150, 23);
		add(B_Zurücksetzen);
				
		//B_Anlegen entspricht Speichern
		JButton B_Anlegen = new JButton("Speichern");
		B_Anlegen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Anlegen.setBounds(305, 384, 150, 23);
		add(B_Anlegen);		

	}
}
