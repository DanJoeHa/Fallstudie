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

public class KonfigurationView extends JPanel {
	private JTextField T_AnzahlMonate;

	/**
	 * Create the panel.
	 */
	public KonfigurationView() {
		setPreferredSize(new Dimension(620, 660));
		setMinimumSize(new Dimension(620, 660));
		setMaximumSize(new Dimension(620, 660));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 660));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//B_Speichern
		JButton B_Speichern = new JButton("Speichern");
		B_Speichern.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Speichern.setBounds(440, 600, 150, 30);
		add(B_Speichern);
		
		//L_AnzahlMonate
		JLabel L_AnzahlMonate = new JLabel("Anzahl der Monate vor dem L\u00F6schen der Eintr\u00E4ge:");
		L_AnzahlMonate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_AnzahlMonate.setBounds(30, 300, 400, 30);
		add(L_AnzahlMonate);
		
		//T_AnzahlMonate
		T_AnzahlMonate = new JTextField();
		T_AnzahlMonate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_AnzahlMonate.setBounds(450, 300, 70, 30);
		add(T_AnzahlMonate);
		T_AnzahlMonate.setColumns(10);

	}
}