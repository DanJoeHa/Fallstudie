package fallstudie.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;
/**
 * View zum Einloggen
 * @author Marc
 *
 */
public class LoginView extends JPanel implements View {

	private static final long serialVersionUID = 6556759367243768953L;
	/**
	 * Passwort Passwortfeld (Eingaben nicht erkennbar)
	 */
	private JPasswordField P_Passwort;
	/**
	 * Benutzername Textfeld
	 */
	private JTextField T_Benutzername;
	/**
	 * Login Button
	 */
	private JButton B_Login;

	/**
	 * Login View
	 */
	public LoginView() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 655));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//L_Benutzername
		JLabel L_Benutzername = new JLabel("Benutzername:");
		L_Benutzername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Benutzername.setBounds(30, 100, 150, 30);
		add(L_Benutzername);
		
		//L_Passwort
		JLabel L_Passwort = new JLabel("Passwort:");
		L_Passwort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Passwort.setBounds(30, 150, 150, 30);
		add(L_Passwort);
		
		//Hinweis, dass man Daten eingeben soll
		JLabel L_Hinweis = new JLabel("Bitte geben Sie Ihren Benutzernamen und Ihr Passwort ein");
		L_Hinweis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Hinweis.setBounds(30, 50, 560, 30);
		add(L_Hinweis);

		//P_Passwort
		P_Passwort = new JPasswordField();
		P_Passwort.setBounds(200, 150, 390, 30);
		add(P_Passwort);
		
		//T_Benutzername
		T_Benutzername = new JTextField();
		T_Benutzername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Benutzername.setColumns(10);
		T_Benutzername.setBounds(200, 100, 390, 30);
		add(T_Benutzername);

		//B_Login
		B_Login = new JButton("Login");
		B_Login.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Login.setBounds(440, 600, 150, 30);
		add(B_Login);

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
		this.T_Benutzername.addKeyListener(c);
		this.P_Passwort.addKeyListener(c);
		this.B_Login.addKeyListener(c);
		this.B_Login.addMouseListener(c);
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
