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
import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;

public class LayoutMitarbeiter extends JPanel implements View{

	private static final long serialVersionUID = -5374763696510559443L;
	protected JTextField T_Vorname;
	protected JTextField T_Nachname;
	protected JTextField T_Benutzername;
	protected static JLabel L_Bereich;
	protected static JLabel L_Arbeitsgruppe;

	/**
	 * Create the panel.
	 */
	public LayoutMitarbeiter() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 655));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//L_Vorname
		JLabel L_Vorname = new JLabel("Vorname:");
		L_Vorname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Vorname.setBounds(30, 100, 150, 30);
		add(L_Vorname);
		
		//L_Nachname
		JLabel L_Nachname = new JLabel("Nachname:");
		L_Nachname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Nachname.setBounds(30, 150, 150, 30);
		add(L_Nachname);
		
		//L_Benutzername
		JLabel L_Benutzername = new JLabel("Benutzername:");
		L_Benutzername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Benutzername.setBounds(30, 200, 150, 30);
		add(L_Benutzername);
		
		//L_Bereich
		L_Bereich = new JLabel("Bereich:");
		L_Bereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Bereich.setBounds(30, 300, 150, 30);
		add(L_Bereich);
		
		//L_Rolle
		JLabel L_Rolle = new JLabel("Rolle:");
		L_Rolle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Rolle.setBounds(30, 250, 150, 30);
		add(L_Rolle);
		
		//L_Arbeitsgruppe
		L_Arbeitsgruppe = new JLabel("Arbeitsgruppe:");
		L_Arbeitsgruppe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Arbeitsgruppe.setBounds(30, 300, 150, 30);
		add(L_Arbeitsgruppe);
		
		//T_Vorname
		T_Vorname = new JTextField();
		T_Vorname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Vorname.setBounds(200, 100, 390, 30);
		add(T_Vorname);
		T_Vorname.setColumns(10);
		
		//T_Nachname
		T_Nachname = new JTextField();
		T_Nachname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Nachname.setColumns(10);
		T_Nachname.setBounds(200, 150, 390, 30);
		add(T_Nachname);
		
		//T_Benutzername
		T_Benutzername = new JTextField();
		T_Benutzername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Benutzername.setColumns(10);
		T_Benutzername.setBounds(200, 200, 390, 30);
		add(T_Benutzername);
		
	}
	
	public void setVorname(String vorname)
	{
		T_Vorname.setText(vorname);
	}
	
	public void setNachname(String nachname)
	{
		T_Nachname.setText(nachname);
	}
	public void setBenutzername(String benutzername)
	{
		T_Benutzername.setText(benutzername);
	}
	
	public String getVorname(){
		return T_Vorname.getText();
	}
	public String getNachname(){
		return T_Nachname.getText();
	}
	public String getBenutzername(){
		return T_Benutzername.getText();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setController(Controller c) {
		
	}
}
