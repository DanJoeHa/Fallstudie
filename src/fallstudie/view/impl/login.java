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

public class login extends JPanel {
	private JPasswordField P_Passwort;
	private JTextField T_Benutzername;

	/**
	 * Create the panel.
	 */
	public login() {
		setPreferredSize(new Dimension(600, 700));
		setMinimumSize(new Dimension(600, 700));
		setMaximumSize(new Dimension(600, 700));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 600, 700));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//L_Ueberschrift
		JLabel L_Ueberschrift = new JLabel("Anmelden");
		L_Ueberschrift.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Ueberschrift.setBounds(10, 11, 580, 17);
		add(L_Ueberschrift);
		
		//L_Benutzername
		JLabel L_Benutzername = new JLabel("Benutzername:");
		L_Benutzername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Benutzername.setBounds(10, 107, 210, 17);
		add(L_Benutzername);
		
		//L_Passwort
		JLabel L_Passwort = new JLabel("Passwort:");
		L_Passwort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Passwort.setBounds(10, 148, 210, 17);
		add(L_Passwort);

		//P_Passwort
		P_Passwort = new JPasswordField();
		P_Passwort.setBounds(151, 148, 181, 20);
		add(P_Passwort);
		
		//T_Benutzername
		T_Benutzername = new JTextField();
		T_Benutzername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Benutzername.setColumns(10);
		T_Benutzername.setBounds(151, 105, 181, 20);
		add(T_Benutzername);

		//B_Login
		JButton B_Login = new JButton("Login");
		B_Login.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Login.setBounds(239, 209, 150, 23);
		add(B_Login);
		
		//B_Passwort_vergessen
		JButton B_Passwort_vergessen = new JButton("Passwort vergessen?");
		B_Passwort_vergessen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Passwort_vergessen.setBounds(239, 243, 264, 23);
		add(B_Passwort_vergessen);

	}
}
