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
import javax.swing.JComboBox;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;

public class ArtLoeschenView extends JPanel implements View{

	/**
	 * Create the panel.
	 */
	public ArtLoeschenView() {
		setPreferredSize(new Dimension(620, 660));
		setMinimumSize(new Dimension(620, 660));
		setMaximumSize(new Dimension(620, 660));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 660));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//B_L�schen
		JButton B_Loeschen = new JButton("L\u00F6schen");
		B_Loeschen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Loeschen.setBounds(440, 600, 150, 30);
		add(B_Loeschen);
		
		//L_Bezeichnung
		JLabel L_Bezeichnung = new JLabel("Bezeichnung:");
		L_Bezeichnung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Bezeichnung.setBounds(30, 300, 150, 30);
		add(L_Bezeichnung);
		
		//J_Combobox -> Zur Auswahl der Art, die man l�schen m�chte
		JComboBox C_Art = new JComboBox();
		C_Art.setFont(new Font("Tahoma", Font.PLAIN, 14));
		C_Art.setBounds(200, 300, 300, 30);
		add(C_Art);

	}

	
	public String getArt(){
		return null;
	}
	
	public void setArt(String[] Arten){
		
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
