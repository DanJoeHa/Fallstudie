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
import javax.swing.JComboBox;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;

public class BereichLoeschenView extends JPanel implements View{

	
	JButton B_BearbeitenLoeschen;
	/**
	 * Create the panel.
	 */
	public BereichLoeschenView() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 660));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		B_BearbeitenLoeschen = new JButton("L\u00F6schen");
		B_BearbeitenLoeschen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_BearbeitenLoeschen.setBounds(440, 600, 150, 30);
		add(B_BearbeitenLoeschen);
		
		JComboBox C_Bereich = new JComboBox();
		C_Bereich.setBounds(293, 21, 297, 30);
		add(C_Bereich);
		
		JLabel L_Hinweis = new JLabel("Bitte den zu löschenden Bereich auswählen");
		L_Hinweis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Hinweis.setBounds(12, 27, 269, 16);
		add(L_Hinweis);

	}

	@Override
	public void setController(Controller c) {
		this.B_BearbeitenLoeschen.addActionListener(c);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}