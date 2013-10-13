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
	private JTextField T_AGLeiterSuchbegriff;

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
		JLabel L_AGLeiter = new JLabel("Gruppenleiter:");
		L_AGLeiter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_AGLeiter.setBounds(30, 200, 150, 30);
		add(L_AGLeiter);
		
		//L_Bereich
		JLabel L_Bereich = new JLabel("Bereich:");
		L_Bereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Bereich.setBounds(30, 250, 150, 30);
		add(L_Bereich);
		
		//T_AG_Leiter_Suchbegriff
		T_AGLeiterSuchbegriff = new JTextField();
		T_AGLeiterSuchbegriff.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_AGLeiterSuchbegriff.setColumns(10);
		T_AGLeiterSuchbegriff.setBounds(200, 200, 220, 30);
		add(T_AGLeiterSuchbegriff);
		
		//C_Bereich
		JComboBox C_Bereich = new JComboBox();
		C_Bereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		C_Bereich.setBounds(200, 250, 300, 30);
		add(C_Bereich);
		
		//B_AG_Leiter_Suche mit Lupe
		JButton B_AGLeiterSuche = new JButton("Suchen");
		B_AGLeiterSuche.setIcon(null);
		B_AGLeiterSuche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_AGLeiterSuche.setBounds(440, 200, 150, 30);
		add(B_AGLeiterSuche);
		

	}
}
