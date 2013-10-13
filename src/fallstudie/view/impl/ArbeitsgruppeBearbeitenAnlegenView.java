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

public class ArbeitsgruppeBearbeitenAnlegenView extends JPanel {
	private JTextField T_AG_Leiter_Suchbegriff;

	/**
	 * Create the panel.
	 */
	public ArbeitsgruppeBearbeitenAnlegenView() {
		setPreferredSize(new Dimension(620, 660));
		setMinimumSize(new Dimension(620, 660));
		setMaximumSize(new Dimension(620, 660));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 660));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//L_AG_Leiter
		JLabel L_AG_Leiter = new JLabel("Gruppenleiter:");
		L_AG_Leiter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_AG_Leiter.setBounds(30, 200, 150, 30);
		add(L_AG_Leiter);
		
		//L_Bereich
		JLabel L_Bereich = new JLabel("Bereich:");
		L_Bereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Bereich.setBounds(30, 250, 150, 30);
		add(L_Bereich);
		
		//T_AG_Leiter_Suchbegriff
		T_AG_Leiter_Suchbegriff = new JTextField();
		T_AG_Leiter_Suchbegriff.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_AG_Leiter_Suchbegriff.setColumns(10);
		T_AG_Leiter_Suchbegriff.setBounds(200, 200, 220, 30);
		add(T_AG_Leiter_Suchbegriff);
		
		//C_Bereich
		JComboBox C_Bereich = new JComboBox();
		C_Bereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		C_Bereich.setBounds(200, 250, 300, 30);
		add(C_Bereich);
		
		//B_AG_Leiter_Suche mit Lupe
		JButton B_AG_Leiter_Suche = new JButton("Suchen");
		B_AG_Leiter_Suche.setIcon(null);
		B_AG_Leiter_Suche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_AG_Leiter_Suche.setBounds(440, 200, 150, 30);
		add(B_AG_Leiter_Suche);
		

	}
}
