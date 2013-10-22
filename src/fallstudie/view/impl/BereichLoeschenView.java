package fallstudie.view.impl;

import javax.swing.JPanel;

import java.awt.SystemColor;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;

import javax.swing.JLabel;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;

public class BereichLoeschenView extends JPanel implements View{

	private static final long serialVersionUID = 5718285763271323948L;
	private JButton B_BearbeitenLoeschen;
	private JComboBox<String> C_Bereich;
	private JLabel L_Hinweis;
	/**
	 * Create the panel.
	 */
	public BereichLoeschenView() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 655));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		B_BearbeitenLoeschen = new JButton("Löschen");
		B_BearbeitenLoeschen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_BearbeitenLoeschen.setBounds(440, 600, 150, 30);
		add(B_BearbeitenLoeschen);
		
		C_Bereich = new JComboBox<String>();
		C_Bereich.setBounds(350, 30, 240, 30);
		add(C_Bereich);
		
		L_Hinweis = new JLabel("Bitte den zu löschenden Bereich auswählen");
		L_Hinweis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Hinweis.setBounds(30, 30, 300, 30);
		add(L_Hinweis);

	}

	@Override
	public void setController(Controller c) {
		this.B_BearbeitenLoeschen.addActionListener(c);
	}

	@Override
	public void reset() {
		
	}
	
	public void setBereiche(String[] Bereiche){
		for( int i = 0; i < Bereiche.length; i++){
			this.C_Bereich.addItem( Bereiche[i] );
		}
	}
	
	public String getBereich(){
		return this.C_Bereich.getSelectedItem().toString();
	}
	
	public void setButtonName(String ButtonName)
	{
		B_BearbeitenLoeschen.setText(ButtonName);
	}
	public void setHinweis(String Hinweis)
	{
		L_Hinweis.setText(Hinweis);
	}
}