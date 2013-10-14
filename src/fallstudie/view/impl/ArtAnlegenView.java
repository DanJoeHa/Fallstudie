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

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;

public class ArtAnlegenView extends JPanel implements View{
	private JTextField T_Art;
	private JButton B_Speichern;

	/**
	 * Create the panel.
	 */
	public ArtAnlegenView() {
		setPreferredSize(new Dimension(620, 660));
		setMinimumSize(new Dimension(620, 660));
		setMaximumSize(new Dimension(620, 660));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 660));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//B_Speichern
		B_Speichern = new JButton("Speichern");
		B_Speichern.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Speichern.setBounds(440, 600, 150, 30);
		add(B_Speichern);
		
		//L_Bezeichnung
		JLabel L_Bezeichnung = new JLabel("Bezeichnung:");
		L_Bezeichnung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Bezeichnung.setBounds(30, 300, 150, 30);
		add(L_Bezeichnung);
		
		
		//T_Art -> Name der neuen Art kann festgelegt werden
		T_Art = new JTextField();
		T_Art.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Art.setBounds(200, 300, 300, 30);
		add(T_Art);
		T_Art.setColumns(10);

	}
	
	/**
	 * neue Art anlegen
	 * @return
	 */
	public String getArt(){
		return this.T_Art.getText();
	}

	@Override
	public void setController(Controller c) {
		// TODO Auto-generated method stub
		this.B_Speichern.addActionListener(c);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		this.T_Art.setText("");
	}
}
