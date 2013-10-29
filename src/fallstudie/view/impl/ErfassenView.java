package fallstudie.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

import fallstudie.controller.impl.HauptController;
import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;
/**
 * View zum Erfassen von Einträgen
 * @author Marc
 *
 */
public class ErfassenView extends JPanel implements View {
	/**
	 * Serial
	 */
	private static final long serialVersionUID = -4191603780481912694L;
	/**
	 * Kalenderjahr Textfeld
	 */
	private JTextField T_Kalenderjahr;
	/**
	 * Anzahl Textfeld
	 */
	
	private JTextField T_AnzahlErfassen;
	/**
	 * Zurücksetzen und Anlegen Button
	 */
	private JButton B_Zuruecksetzen, B_Anlegen;
	/**
	 * Kalenderwochen RadioBox zur Auswahl
	 */
	private JRadioButton R_KalenderwocheDavor, R_KalenderwocheAktuell;
	/**
	 * Art AuswahlBox
	 */
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
		//Abfangen das nur positive Zahlen und keine anderen Zeichen eingetragen werden.
		T_AnzahlErfassen.addKeyListener(new KeyListener() {
			int zahl=0;
			//Keine Fuktion
			@Override
			public void keyTyped(KeyEvent e) {}
			
			//Abfragen des Textfeldinhalts und reagieren auf diesen.
			@Override
			public void keyReleased(KeyEvent e) {
				zahl=0;
				//Liest nur Wert aus, wenn auch etwas drin steht.
				if(T_AnzahlErfassen.getText().isEmpty()==false){
					try{
						zahl = Integer.parseInt( T_AnzahlErfassen.getText() );
					}catch(NumberFormatException exp){
						T_AnzahlErfassen.setText("");
					}
				}
				//Ausgelesene Zahl kleiner gleich 0 und Feeld nicht leer.
				if(zahl<=0 && T_AnzahlErfassen.getText().isEmpty()==false){
					T_AnzahlErfassen.setText("");
					HauptController.hauptfenster.setInfoBox("Es sind nur Zahlen größer als 0 zulässig!");
				}
				//Feld leer bzw. es steht nichts drin.
				if(T_AnzahlErfassen.getText().isEmpty() || T_AnzahlErfassen.equals("")){
					HauptController.hauptfenster.setInfoBox("Es sind nur Zahlen größer als 0 bzw. kleiner als 1000 zulässig!");
				}
				//Zahl größer als 1000
				if(zahl>1000){
					T_AnzahlErfassen.setText("");
					HauptController.hauptfenster.setInfoBox("Eingegebene Zahl darf nicht größer als 1000 sein!");
				}
				//Eingabe passt, Zahl zwischen 1 und 1000.
				if(zahl>=1 && zahl<=1000){
					HauptController.hauptfenster.setInfoBox("");
				}
				revalidate();
			}
			
			//keine Funktion
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
	    //B_Plus
	    JButton B_Plus = new JButton("+");
	    B_Plus.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    B_Plus.setBounds(450, 180, 70, 30);
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
				HauptController.hauptfenster.setInfoBox("");
			}
	    	
	    });
	    
	    //Combo_Art_waehlen
	    C_Art = new JComboBox<String>();
	    C_Art.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    C_Art.setBounds(200, 130, 320, 30);
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
		
		JButton B_Minus = new JButton("-");
		B_Minus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Minus.setBounds(370, 180, 70, 30);
		add(B_Minus);
		
		B_Minus.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int derzeitigerWert;
				try
				{
					derzeitigerWert =  Integer.parseInt(T_AnzahlErfassen.getText());
				}
				catch (Exception ex)
				{
					derzeitigerWert = 0;
				}
				int neuerWert = 0;
				if( derzeitigerWert > 0 ) neuerWert = derzeitigerWert - 1;
				if(derzeitigerWert<=0){
					neuerWert=0;
					HauptController.hauptfenster.setInfoBox("Nur Zahlen größer als 0 zulässig!");
				}
				T_AnzahlErfassen.setText(""+neuerWert);
			}
	    	
	    });
		
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
	/**
	 * Gibt ausgewählte Art aus AuswahlBox zurück
	 * @return Art
	 */
	public String getArt(){
		return this.C_Art.getSelectedItem().toString();
	}
	/**
	 * Füllt AuswahlBox mit Arten
	 * @param arten
	 */
	public void setArten(String[] arten){
		for( int i = 0; i < arten.length; i++){
			this.C_Art.addItem( arten[i] );
		}
	}
	/**
	 * Setzt Controller
	 */
	@Override
	public void setController(Controller c) {
		this.B_Anlegen.addActionListener(c);
		this.B_Zuruecksetzen.addActionListener(c);
		this.T_AnzahlErfassen.addKeyListener(c);
		this.B_Anlegen.addKeyListener(c);
	}

	/**
	 * Setzt Textfeld Anzahl auf 0 zruück
	 */
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
