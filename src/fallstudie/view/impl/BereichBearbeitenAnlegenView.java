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
import javax.swing.JButton;
import javax.swing.JTextField;

public class BereichBearbeitenAnlegenView extends JPanel {
	private JTextField T_BreichsleiterSuchbegriff;

	/**
	 * Create the panel.
	 */
	public BereichBearbeitenAnlegenView() {
		setPreferredSize(new Dimension(620, 660));
		setMinimumSize(new Dimension(620, 660));
		setMaximumSize(new Dimension(620, 660));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 660));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//L_Bereichsleiter
		JLabel L_Bereichsleiter = new JLabel("Bereichsleiter:");
		L_Bereichsleiter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Bereichsleiter.setBounds(30, 200, 150, 30);
		add(L_Bereichsleiter);
		
		//T_Bereichsleiter_Suchbegriff
		T_BreichsleiterSuchbegriff = new JTextField();
		T_BreichsleiterSuchbegriff.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_BreichsleiterSuchbegriff.setBounds(200, 200, 220, 30);
		add(T_BreichsleiterSuchbegriff);
		T_BreichsleiterSuchbegriff.setColumns(10);
		
		//B_Bereichsleiter_Suche
		JButton B_BereichsleiterSuche = new JButton("Suchen");
		B_BereichsleiterSuche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_BereichsleiterSuche.setBounds(440, 200, 150, 30);
		add(B_BereichsleiterSuche);

	}
}
