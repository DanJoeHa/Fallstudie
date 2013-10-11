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

public class suchen_view extends JPanel {
	private JPasswordField P_Passwort;

	/**
	 * Create the panel.
	 */
	public suchen_view() {
		setPreferredSize(new Dimension(600, 700));
		setMinimumSize(new Dimension(600, 700));
		setMaximumSize(new Dimension(600, 700));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 600, 700));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//P_Passwort
		P_Passwort = new JPasswordField();
		P_Passwort.setText("");
		P_Passwort.setBounds(170, 145, 181, 20);
		add(P_Passwort);
		
		//B_Suchen
		JButton B_Suchen = new JButton("suchen");
		B_Suchen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Suchen.setBounds(361, 144, 134, 23);
		add(B_Suchen);
		

		
	}
}
