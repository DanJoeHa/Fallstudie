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

public class KonfigurationView extends JPanel implements View {
	private JTextField T_AnzahlMonate;
	private JButton B_Speichern;
	/**
	 * Create the panel.
	 */
	public KonfigurationView() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
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
		
		//L_AnzahlMonate
		JLabel L_AnzahlMonate = new JLabel("Anzahl der Monate vor dem Löschen der Eintr\u00E4ge:");
		L_AnzahlMonate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_AnzahlMonate.setBounds(12, 13, 400, 30);
		add(L_AnzahlMonate);
		
		//T_AnzahlMonate
		T_AnzahlMonate = new JTextField();
		T_AnzahlMonate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_AnzahlMonate.setBounds(440, 14, 70, 30);
		add(T_AnzahlMonate);
		T_AnzahlMonate.setColumns(10);

	}
	
	public void setAnzahlMonate(String monate){
		this.T_AnzahlMonate.setText(monate);
	}
	
	public int getAnzahlMonate(){
		try
		{
			return Integer.parseInt(this.T_AnzahlMonate.getText());
		}
		catch(Exception ex)
		{
			return 0;
		}
		
	}
	
	@Override
	public void setController(Controller c) {
		this.B_Speichern.addActionListener(c);		
	}
	@Override
	public void reset() {
		this.T_AnzahlMonate.setText("");
	}
	
	
}
