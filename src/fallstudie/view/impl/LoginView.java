package fallstudie.view.impl;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;

public class LoginView extends JPanel implements View {

	private static final long serialVersionUID = 6556759367243768953L;
	private JPasswordField P_Passwort;
	private JTextField T_Benutzername;
	private JButton B_Login;
	private JButton B_Passwort_vergessen;

	/**
	 * Sicht erstellen.
	 * 
	 * @author Natalja
	 * @version 1.0
	 */
	public LoginView() {
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
		B_Login = new JButton("Login");
		B_Login.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Login.setBounds(239, 209, 150, 23);
		add(B_Login);
		
		//B_Passwort_vergessen
		B_Passwort_vergessen = new JButton("Passwort vergessen?");
		B_Passwort_vergessen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Passwort_vergessen.setBounds(239, 243, 264, 23);
		add(B_Passwort_vergessen);

	}
	
	/**
	 * liest die Eingabe aus dem Textfeld "Benutzername" aus und gibt diese zurück
	 * 
	 * @author Johannes
	 * @version 1.0
	 * @return (String) Benutzername
	 */
	public String getBenutzername(){
		return this.T_Benutzername.getText();
	}
	
	/**
	 * liest die Eingabe aus dem Textfeld "Passwort" aus und gibt diese zur�ck
	 * 
	 * @author Johannes
	 * @version 1.0
	 * @return (String) Passwort
	 */
	public String getPasswort(){
		return String.valueOf( this.P_Passwort.getPassword());
	}
	
	/**
	 * ActionListener auf die Buttons setzen
	 * 
	 * @author Johannes
	 * @version 1.0
	 * @param controller
	 */
	@Override
	public void setController(Controller c) {
		this.B_Login.addActionListener(c);
		this.B_Passwort_vergessen.addActionListener(c);
		
	}
	
	/**
	 * Textfelder auf Maske leeren
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	@Override
	public void reset() {
		this.P_Passwort.setText("");
		this.T_Benutzername.setText("");
	}
}
