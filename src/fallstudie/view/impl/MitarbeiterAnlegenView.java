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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;

public class MitarbeiterAnlegenView extends LayoutMitarbeiter{
	private JTextField T_Arbeitsgruppe;
	private JTextField T_Passwort1;
	private JButton B_Speichern;
	private JComboBox<String> C_Rolle;
	private JComboBox<String> C_Bereich;
	private JButton B_SucheArbeitsgruppe ;

	/**
	 * Create the panel.
	 */
	public MitarbeiterAnlegenView() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 660));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//L_Passwort1
		JLabel L_Passwort1 = new JLabel("Passwort:");
		L_Passwort1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Passwort1.setBounds(30, 255, 150, 30);
		add(L_Passwort1);
		
		//T_Passwort1
		T_Passwort1 = new JTextField();
		T_Passwort1.setBounds(190, 257, 186, 30);
		add(T_Passwort1);
		T_Passwort1.setColumns(10);
		
		//B_PasswortGenerieren
		JButton B_PasswortGenerieren = new JButton("Generieren");
		B_PasswortGenerieren.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_PasswortGenerieren.setBounds(386, 257, 104, 30);
		add(B_PasswortGenerieren);
		
		B_PasswortGenerieren.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder zufallsPasswort = new StringBuilder("xxxxxxxx");
		        java.util.Random rGen=new java.util.Random();
		        char[] passArray=new char[8];
		        
		        for(int i=0;i<8;i++) {
		            passArray[i]=(char)(rGen.nextInt(26)+97);
		            zufallsPasswort.setCharAt(i, passArray[i]);
		        }
		        
				T_Passwort1.setText(zufallsPasswort.toString());
			}
	    	
	    });
		
		//C_Rolle
		C_Rolle = new JComboBox<String>();
		C_Rolle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		C_Rolle.setBounds(190, 135, 300, 30);
		add(C_Rolle);
				
		//C_Bereich
		C_Bereich = new JComboBox<String>();
		C_Bereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		C_Bereich.setBounds(190, 176, 300, 30);
		add(C_Bereich);
			
		//T_Arbeitsgruppe
		T_Arbeitsgruppe = new JTextField();
		T_Arbeitsgruppe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Arbeitsgruppe.setColumns(10);
		T_Arbeitsgruppe.setBounds(190, 217, 186, 30);
		add(T_Arbeitsgruppe);

		//B_SucheArbeitsgruppe
		B_SucheArbeitsgruppe = new JButton("Suchen");
		B_SucheArbeitsgruppe.setIcon(null);
		B_SucheArbeitsgruppe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_SucheArbeitsgruppe.setBounds(386, 217, 104, 30);
		add(B_SucheArbeitsgruppe);
		
		//B_Zuruecksetzen
		JButton B_Zuruecksetzen = new JButton("Zurücksetzten");
		B_Zuruecksetzen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Zuruecksetzen.setBounds(30, 600, 150, 30);
		add(B_Zuruecksetzen);
		
		//B_Speichern
		B_Speichern = new JButton("Speichern");
		B_Speichern.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Speichern.setBounds(440, 600, 150, 30);
		add(B_Speichern);
		
	}

	@Override
	public void setController(Controller c) {
		this.B_Speichern.addActionListener(c);
		this.B_SucheArbeitsgruppe.addActionListener(c);
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
	public void setRolle(String[] rollen){
		for( int i = 0; i < rollen.length; i++){
			this.C_Rolle.addItem( rollen[i] );
		}
	}
	
	public void setBereich(String[] bereiche){
		for( int i = 0; i < bereiche.length; i++){
			this.C_Bereich.addItem( bereiche[i] );
		}
	}
	
	public String getRolle(){
		return C_Rolle.getSelectedItem().toString();
	}
	
	public String getPasswort(){
		return T_Passwort1.getText();
	}
	
	public String getBereich(){
		return C_Bereich.getSelectedItem().toString();
	}
	
	public String getArbeitsgruppe(){
		return T_Arbeitsgruppe.getText();
	}
	public void setArbeitsgruppe(String arbeitsgruppe)
	{
		T_Arbeitsgruppe.setText(arbeitsgruppe);
	}
}
