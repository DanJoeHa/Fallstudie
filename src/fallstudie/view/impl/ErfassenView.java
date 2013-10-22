package fallstudie.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;

public class ErfassenView extends JPanel implements View {

	private static final long serialVersionUID = -4191603780481912694L;
	private JTextField T_Kalenderjahr;
	private JTextField T_AnzahlErfassen;
	private JButton B_Zuruecksetzen, B_Anlegen;
	private JRadioButton R_KalenderwocheDavor, R_KalenderwocheAktuell;
	private JComboBox<String> C_Art;

	/**
	 * Create the panel.
	 */
	public ErfassenView() {
					
		//Styling
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 655));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//L_Kalenderjahr
		JLabel L_Kalenderjahr = new JLabel("Kalenderjahr:");
		L_Kalenderjahr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Kalenderjahr.setBounds(30, 30, 150, 30);
		add(L_Kalenderjahr);
		
		//L_Kalenderwoche
		JLabel L_Kalenderwoche = new JLabel("Kalenderwoche:");
		L_Kalenderwoche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Kalenderwoche.setBounds(30, 80, 150, 30);
		add(L_Kalenderwoche);
		
		//L_Erfassen statt L_Erstattungen und L_Schriftwechsel
		JLabel L_Art = new JLabel("Art:");
		L_Art.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Art.setBounds(30, 130, 150, 30);
		add(L_Art);
		
		JLabel L_Anzahl = new JLabel("Anzahl:");
		L_Anzahl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Anzahl.setBounds(30, 180, 150, 30);
		add(L_Anzahl);
		
		//Datumswerte bestimmen
		GregorianCalendar heute = new GregorianCalendar();
		Calendar letztesJahr = new GregorianCalendar(Calendar.YEAR-1,Calendar.DECEMBER,10);
		
		heute.setTime( new Date() );
		
		final int jahr = heute.get(Calendar.YEAR);
		final int max_week = letztesJahr.getMaximum(Calendar.WEEK_OF_YEAR);
		int aktKW = heute.get(GregorianCalendar.WEEK_OF_YEAR);
		int vorKW = aktKW - 1;
		int vorKW_checked=0;
		if(vorKW==0)
		{
			vorKW_checked= max_week;
		}
		else
		{
			vorKW_checked=vorKW;
		}
		//T_Kalenderjahr
		T_Kalenderjahr = new JTextField();
		T_Kalenderjahr.setText(""+jahr);
		T_Kalenderjahr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Kalenderjahr.setColumns(10);
		T_Kalenderjahr.setBounds(200, 30, 150, 30);
		T_Kalenderjahr.setEditable(false);
		add(T_Kalenderjahr);
		
		//R_KalenderwocheDavor
		R_KalenderwocheDavor = new JRadioButton(""+vorKW_checked);
		R_KalenderwocheDavor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		R_KalenderwocheDavor.setBounds(200, 80, 70, 30);
		add(R_KalenderwocheDavor);
		R_KalenderwocheDavor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int jahr_alt = jahr-1;
				if(getKalenderwoche()==max_week) T_Kalenderjahr.setText(""+jahr_alt);
			}
		});
		
		
		//R_KalenderwocheAktuell
		R_KalenderwocheAktuell = new JRadioButton(""+aktKW);
		R_KalenderwocheAktuell.setFont(new Font("Tahoma", Font.PLAIN, 14));
		R_KalenderwocheAktuell.setBounds(290, 80, 70, 30);
		add(R_KalenderwocheAktuell);
		R_KalenderwocheAktuell.setSelected(true);
		R_KalenderwocheAktuell.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(getKalenderwoche()>=1) T_Kalenderjahr.setText(""+jahr);
			}
		});
		
		//Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(R_KalenderwocheDavor);
	    group.add(R_KalenderwocheAktuell);
	   
	    //T_Anzahl_Erfassen statt die einzelnen Erfassen
	    T_AnzahlErfassen = new JTextField();
	    T_AnzahlErfassen.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    T_AnzahlErfassen.setColumns(10);
	    T_AnzahlErfassen.setBounds(200, 180, 150, 30);
	    add(T_AnzahlErfassen);
		//Intialwert Erfassen 0
		T_AnzahlErfassen.setText("0");
	    
	    //B_Plus
	    JButton B_Plus = new JButton("+");
	    B_Plus.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    B_Plus.setBounds(370, 180, 70, 30);
	    add(B_Plus);
	    
	    //Increment-Logik
	    B_Plus.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int derzeitigerWert;
				try
				{
					derzeitigerWert =  Integer.parseInt( T_AnzahlErfassen.getText() );
				}
				catch (Exception ex)
				{
					derzeitigerWert = 0;
				}
				int neuerWert = derzeitigerWert + 1;
				T_AnzahlErfassen.setText(""+neuerWert);
			}
	    	
	    });
	    
	    //Combo_Art_waehlen
	    C_Art = new JComboBox<String>();
	    C_Art.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    C_Art.setBounds(200, 130, 390, 30);
	    add(C_Art);
	    
		//B_Zurücksetzen
		B_Zuruecksetzen = new JButton("Zurücksetzen");
		B_Zuruecksetzen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Zuruecksetzen.setBounds(30, 600, 150, 30);
		add(B_Zuruecksetzen);
		
		//B_Anlegen entspricht Speichern
		B_Anlegen = new JButton("Speichern");
		B_Anlegen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Anlegen.setBounds(440, 600, 150, 30);
		add(B_Anlegen);
		
	}
	
	/**
	 * Liefert die gewählte Kalenderwoche zurück.
	 * 
	 * @author Johannes
	 * @version 1.0
	 * @return (Int) gewählte Kalenderwoche
	 */
	public int getKalenderwoche(){
		Object[] selected;
		if( this.R_KalenderwocheDavor.isSelected() ){
			selected = this.R_KalenderwocheDavor.getSelectedObjects();
		}else{
			selected = this.R_KalenderwocheAktuell.getSelectedObjects();
		}
		return Integer.parseInt( selected[0].toString() );
	}
	
	/**
	 * Liefert das angegebene Kalenderjahr
	 * 
	 * @author Johannes
	 * @version 1.0
	 * @return (int) Kalenderjahr
	 */
	public int getKalenderjahr(){
		try{
				int kalenderjahr = Integer.parseInt( this.T_Kalenderjahr.getText() );
				return kalenderjahr;
		}
		catch (Exception ex){
				return 0;
		}
	}
	
	public String getArt(){
		return this.C_Art.getSelectedItem().toString();
	}
	
	public void setArten(String[] arten){
		for( int i = 0; i < arten.length; i++){
			this.C_Art.addItem( arten[i] );
		}
	}
	
	@Override
	public void setController(Controller c) {
		this.B_Anlegen.addActionListener(c);
		this.B_Zuruecksetzen.addActionListener(c);
	}

	@Override
	public void reset() {
		this.T_AnzahlErfassen.setText("0");
	}
	
	/**
	 * Liefert die Anzahl
	 * 
	 * @author Marc
	 * @version 1.0
	 * @return (int) Anzahl
	 */
	public int getAnzahl(){
		try
		{
			return Integer.parseInt(T_AnzahlErfassen.getText());
		}
		catch(Exception ex)
		{
			return 0;
		}
	}
}
