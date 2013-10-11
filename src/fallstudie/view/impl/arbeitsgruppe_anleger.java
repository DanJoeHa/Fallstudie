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

public class arbeitsgruppe_anleger extends JPanel {
	private JTextField T_AG_Leiter_Suchbegriff;

	/**
	 * Create the panel.
	 */
	public arbeitsgruppe_anleger() {
		setPreferredSize(new Dimension(600, 700));
		setMinimumSize(new Dimension(600, 700));
		setMaximumSize(new Dimension(600, 700));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 600, 700));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//L_Ueberschrift
		JLabel L_Ueberschrift = new JLabel("Arbeitsgruppe anlegen");
		L_Ueberschrift.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Ueberschrift.setBounds(10, 11, 580, 17);
		add(L_Ueberschrift);
		
		//L_AG_Leiter
		JLabel L_AG_Leiter = new JLabel("Gruppenleiter:");
		L_AG_Leiter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_AG_Leiter.setBounds(10, 192, 210, 17);
		add(L_AG_Leiter);
		
		//L_Bereich
		JLabel L_Bereich = new JLabel("Bereich:");
		L_Bereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Bereich.setBounds(10, 241, 210, 17);
		add(L_Bereich);
		
		//T_AG_Leiter_Suchbegriff
		T_AG_Leiter_Suchbegriff = new JTextField();
		T_AG_Leiter_Suchbegriff.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_AG_Leiter_Suchbegriff.setColumns(10);
		T_AG_Leiter_Suchbegriff.setBounds(175, 192, 181, 20);
		add(T_AG_Leiter_Suchbegriff);
		
		//C_Bereich
		JComboBox C_Bereich = new JComboBox();
		C_Bereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		C_Bereich.setBounds(175, 241, 181, 20);
		add(C_Bereich);
		
		//B_AG_Leiter_Suche mit Lupe
		JButton B_AG_Leiter_Suche = new JButton("suchen");
		B_AG_Leiter_Suche.setIcon(null);
		B_AG_Leiter_Suche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_AG_Leiter_Suche.setBounds(366, 191, 106, 23);
		add(B_AG_Leiter_Suche);
		

	}
}
