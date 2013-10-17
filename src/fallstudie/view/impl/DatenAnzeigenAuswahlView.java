package fallstudie.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;

public class DatenAnzeigenAuswahlView extends JPanel implements View{

	private static final long serialVersionUID = 8946261889602533900L;
	private JTextField T_Jahr;
	private JTextField T_Woche;
	private JButton B_Weiter;
	private JTextArea L_Hinweis;

	/**
	 * Create the panel.
	 */
	public DatenAnzeigenAuswahlView() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 660));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		B_Weiter = new JButton("Weiter");
		B_Weiter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Weiter.setBounds(440, 600, 150, 30);
		add(B_Weiter);
		
		JLabel L_Jahr = new JLabel("Kalenderjahr:");
		L_Jahr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Jahr.setBounds(30, 11, 150, 30);
		add(L_Jahr);
		
		JLabel L_Woche = new JLabel("Kalenderwoche:");
		L_Woche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Woche.setBounds(30, 53, 150, 30);
		add(L_Woche);
		
		T_Jahr = new JTextField();
		T_Jahr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Jahr.setBounds(190, 12, 70, 30);
		add(T_Jahr);
		T_Jahr.setColumns(10);
		
		T_Woche = new JTextField();
		T_Woche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Woche.setBounds(190, 54, 70, 30);
		add(T_Woche);
		T_Woche.setColumns(10);
		
		L_Hinweis = new JTextArea();
		L_Hinweis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Hinweis.setText("Wenn Sie die Daten eines ganzen Kalenderjahres sehen wollen, lassen Sie das Feld\r\n\"Kalenderwoche\" leer.");
		L_Hinweis.setBounds(30, 104, 520, 60);
		L_Hinweis.setEditable(false);
		add(L_Hinweis);

	}
	
	/**
	 * Liefert die eingegebene Kalenderwoche zurück
	 * 
	 * @author Johannes
	 * @version 1.0
	 * @return (int) Kalenderwoche
	 */
	public int getWoche() throws Exception {
		String wo = this.T_Woche.getText();
		
		if( wo.isEmpty() ) return 0;
		return Integer.parseInt( this.T_Woche.getText() );
		
	}
	
	/**
	 * Liefert das eingegebene Kalenderjahr zurück
	 * 
	 * @author Johannes
	 * @version 1.0
	 * @return (int) Jahr
	 */
	public int getJahr()  throws Exception{
		return Integer.parseInt( this.T_Jahr.getText() );
	}
	
	/**
	 * Setzt den Controller auf den Button "weiter"
	 * 
	 * @author Johannes
	 * @version 1.0
	 * @param c
	 */
	@Override
	public void setController(Controller c) {
		this.B_Weiter.addActionListener(c);
	}
	
	/**
	 * Setzt die Maske wieder zurück und leert alle Felder
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	@Override
	public void reset() {
		this.T_Jahr.setText("");
		this.T_Woche.setText("");
	}
}