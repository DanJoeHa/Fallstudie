package fallstudie.view.impl;

import javax.swing.JPanel;

import java.awt.SystemColor;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class ErfassenView extends JPanel {
	private JTextField T_Kalenderjahr;
	private JTextField T_Anzahl_Erfassen;

	/**
	 * Create the panel.
	 */
	public ErfassenView() {
		setPreferredSize(new Dimension(600, 700));
		setMinimumSize(new Dimension(600, 700));
		setMaximumSize(new Dimension(600, 700));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 600, 700));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//L_Ueberschrift Eintag erfassen
		JLabel L_Ueberschrift = new JLabel("Eintrag erfassen");
		L_Ueberschrift.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Ueberschrift.setBounds(10, 11, 580, 17);
		add(L_Ueberschrift);
		
		//L_Kalenderjahr
		JLabel L_Kalenderjahr = new JLabel("Kalenderjahr:");
		L_Kalenderjahr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Kalenderjahr.setBounds(10, 96, 210, 17);
		add(L_Kalenderjahr);
		
		//L_Kalenderwoche
		JLabel L_Kalenderwoche = new JLabel("Kalenderwoche:");
		L_Kalenderwoche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Kalenderwoche.setBounds(10, 136, 210, 17);
		add(L_Kalenderwoche);
		
		//L_Erfassen statt L_Erstattungen und L_Schriftwechsel
		JLabel L_Erfassen = new JLabel("Erfassen:");
		L_Erfassen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Erfassen.setBounds(10, 182, 210, 17);
		add(L_Erfassen);
		
		//T_Kalenderjahr
		T_Kalenderjahr = new JTextField();
		T_Kalenderjahr.setText("jetziges Jahr");
		T_Kalenderjahr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Kalenderjahr.setColumns(10);
		T_Kalenderjahr.setBounds(155, 96, 181, 20);
		add(T_Kalenderjahr);
		
		//R_KalenderwocheDavor
		JRadioButton R_KalenderwocheDavor = new JRadioButton("letzte Woche");
		R_KalenderwocheDavor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		R_KalenderwocheDavor.setBounds(155, 135, 134, 23);
		add(R_KalenderwocheDavor);
		
		//R_KalenderwocheAktuell
		JRadioButton R_KalenderwocheAktuell = new JRadioButton("diese Woche");
		R_KalenderwocheAktuell.setFont(new Font("Tahoma", Font.PLAIN, 14));
		R_KalenderwocheAktuell.setBounds(319, 133, 134, 23);
		add(R_KalenderwocheAktuell);
		R_KalenderwocheAktuell.setSelected(true);
		
		//Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(R_KalenderwocheDavor);
	    group.add(R_KalenderwocheAktuell);
	    
	    //T_Anzahl_Erfassen statt die einzelnen Erfassen
	    T_Anzahl_Erfassen = new JTextField();
	    T_Anzahl_Erfassen.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    T_Anzahl_Erfassen.setColumns(10);
	    T_Anzahl_Erfassen.setBounds(155, 182, 134, 20);
	    add(T_Anzahl_Erfassen);
	    
	    //B_Plus
	    JButton B_Plus = new JButton("+");
	    B_Plus.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    B_Plus.setBounds(299, 181, 43, 23);
	    add(B_Plus);
	    
	    //Combo_Art_waehlen
	    JComboBox Combo_Art_waehlen = new JComboBox();
	    Combo_Art_waehlen.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    Combo_Art_waehlen.setBounds(352, 182, 150, 20);
	    add(Combo_Art_waehlen);
	    
		//B_Zur�cksetzen
		JButton B_Zuruecksetzen = new JButton("Zur\u00FCcksetzten");
		B_Zuruecksetzen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Zuruecksetzen.setBounds(70, 382, 150, 23);
		add(B_Zuruecksetzen);
		
		//B_Anlegen entspricht Speichern
		JButton B_Anlegen = new JButton("Speichern");
		B_Anlegen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Anlegen.setBounds(305, 384, 150, 23);
		add(B_Anlegen);
		
	}
}
