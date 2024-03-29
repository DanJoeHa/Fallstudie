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
/**
 * View zum Löschen von Bereichen
 * @author Marc
 *
 */
public class BereichLoeschenView extends JPanel implements View{
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 5718285763271323948L;
	/**
	 * Bearbeiten und Löschen Button
	 */
	private JButton B_BearbeitenLoeschen;
	/**
	 * Bereich AuswahlBox
	 */
	private JComboBox<String> C_Bereich;
	/**
	 * Hinsweis Label
	 */
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
		C_Bereich.requestFocus();
		add(C_Bereich);
		
		L_Hinweis = new JLabel("Bitte den zu löschenden Bereich auswählen");
		L_Hinweis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Hinweis.setBounds(30, 30, 300, 30);
		add(L_Hinweis);

	}
	/**
	 * Check auf welchem Button der Focus liegt
	 * @return
	 */
	public String hatFocus(){
		String ausgabe;
		if(this.B_BearbeitenLoeschen.isFocusOwner()){
			ausgabe = "buttonLoeschen";
		}
		else{
			ausgabe = "nichts";
		}
		return ausgabe;
	}
	/**
	 * setzt Focus auf Button
	 */
	public void setzeFocus(){
		this.B_BearbeitenLoeschen.requestFocusInWindow();
	}
	
	@Override
	/**
	 * setzt Controller
	 */
	public void setController(Controller c) {
		this.B_BearbeitenLoeschen.addActionListener(c);
		this.B_BearbeitenLoeschen.addKeyListener(c);
		this.C_Bereich.addKeyListener(c);
	}
	/**
	 * Keine Funktion
	 */
	@Override
	public void reset() {
		
	}
	/**
	 * Befüllt Combobox mit den Bereichen
	 * @param Bereiche
	 */
	public void setBereiche(String[] Bereiche){
		this.C_Bereich.removeAllItems();
		for( int i = 0; i < Bereiche.length; i++){
			this.C_Bereich.addItem( Bereiche[i] );
		}
	}
	/**
	 * Holt den ausgewählten Bereich
	 * @return
	 */
	public String getBereich(){
		return this.C_Bereich.getSelectedItem().toString();
	}
	/**
	 * Setzt Buttonname
	 * @param ButtonName
	 */
	public void setButtonName(String ButtonName)
	{
		B_BearbeitenLoeschen.setText(ButtonName);
	}
	/**
	 * Setzt den Text
	 * @param Hinweis
	 */
	public void setHinweis(String Hinweis)
	{
		L_Hinweis.setText(Hinweis);
	}
}