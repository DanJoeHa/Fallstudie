package fallstudie.view.impl;

import java.awt.SystemColor;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;

import javax.swing.JLabel;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;

public class ArbeitsgruppeBearbeitenAnlegenView extends OrgeinheitBearbeitenAnlegen implements View{

	private static final long serialVersionUID = -2078487615780247796L;
	private JTextField T_AGLeiterSuchbegriff;
	private JComboBox<String> C_Bereich;
	private JButton B_AGLeiterSuche;

	/**
	 * Create the panel.
	 */
	public ArbeitsgruppeBearbeitenAnlegenView() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
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
		C_Bereich = new JComboBox<String>();
		C_Bereich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		C_Bereich.setBounds(200, 250, 300, 30);
		add(C_Bereich);
		
		//B_AG_Leiter_Suche mit Lupe
		B_AGLeiterSuche = new JButton("Suchen");
		B_AGLeiterSuche.setIcon(null);
		B_AGLeiterSuche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_AGLeiterSuche.setBounds(440, 200, 150, 30);
		add(B_AGLeiterSuche);
		

	}
	public void setBereich(String[] bereiche){
		for( int i = 0; i < bereiche.length; i++){
			this.C_Bereich.addItem( bereiche[i] );
		}
		
	}
	
	public void setBereich(String[] bereiche, String aktuellerBereich){
		this.setBereich(bereiche);
		for( int i = 0; i < bereiche.length; i++){
			if( bereiche[i].equals(aktuellerBereich) ) {
				this.C_Bereich.setSelectedIndex(i);
				break;
			}
		}
	}
	
	public String getBereich(){
		return this.C_Bereich.getSelectedItem().toString();
	}
	
	public void setAGLeiter(String benutzerid){
		this.T_AGLeiterSuchbegriff.setText(benutzerid);
	}
	
	public String getAGLeiter(){
		return this.T_AGLeiterSuchbegriff.getText();
	}
	
	@Override
	public void setController(Controller c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
