package fallstudie.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import fallstudie.controller.impl.HauptController;
import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;
/**
 * View zum Auswählen der Jahres-/Monatsübersicht
 * @author Marc
 *
 */
public class DatenAnzeigenAuswahlView extends JPanel implements View{
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 8946261889602533900L;
	/**
	 * Jahr Textfeld
	 */
	private JTextField T_Jahr;
	/**
	 * Wochen Textfeld
	 */
	private JTextField T_Woche;
	/**
	 * Weiter Button
	 */
	private JButton B_Weiter;
	/**
	 * Hinweis Text
	 */
	private JTextArea L_Hinweis;

	/**
	 * Create the panel.
	 */
	public DatenAnzeigenAuswahlView() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 655));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		B_Weiter = new JButton("Weiter");
		B_Weiter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Weiter.setBounds(440, 600, 150, 30);
		add(B_Weiter);
		
		JLabel L_Jahr = new JLabel("Kalenderjahr:");
		L_Jahr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Jahr.setBounds(30, 30, 150, 30);
		add(L_Jahr);
		
		JLabel L_Woche = new JLabel("Kalenderwoche:");
		L_Woche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Woche.setBounds(30, 80, 150, 30);
		add(L_Woche);
		
		T_Jahr = new JTextField();
		T_Jahr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Jahr.setBounds(200, 30, 70, 30);
		add(T_Jahr);
		T_Jahr.setColumns(10);
		T_Jahr.addKeyListener(new KeyListener() {
			int zahl=0;
			//Keine Fuktion
			@Override
			public void keyTyped(KeyEvent e) {}
			
			//Abfragen des Textfeldinhalts und reagieren auf diesen.
			@Override
			public void keyReleased(KeyEvent e) {
				zahl=0;
				//Liest nur Wert aus, wenn auch etwas drin steht.
				if(T_Jahr.getText().isEmpty()==false){
					try{
						zahl = Integer.parseInt( T_Jahr.getText() );
					}catch(NumberFormatException exp){
						T_Jahr.setText("");
					}
				}
				//Ausgelesene Zahl kleiner gleich 0 und Feld nicht leer.
				if(zahl<=0 && T_Jahr.getText().isEmpty()==false){
					T_Jahr.setText("");
					HauptController.hauptfenster.setInfoBox("Bitte ein Jahr eingeben!");
				}
				//Feld leer bzw. es steht nichts drin.
				if(T_Jahr.getText().isEmpty() || T_Jahr.equals("")){
					HauptController.hauptfenster.setInfoBox("Bitte ein Jahr eingeben!");
				}
				//Zahl größer als 1000
				if(zahl>3000){
					T_Jahr.setText("");
					HauptController.hauptfenster.setInfoBox("Das Jahr darf nicht größer als 3000 sein!");
				}
				//Eingabe passt, Zahl zwischen 1 und 1000.
				if(zahl>=1 && zahl<=3000){
					HauptController.hauptfenster.setInfoBox("");
				}
				revalidate();
			}
			
			//keine Funktion
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		T_Woche = new JTextField();
		T_Woche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Woche.setBounds(200, 80, 70, 30);
		add(T_Woche);
		T_Woche.setColumns(10);
		T_Woche.addKeyListener(new KeyListener() {
			int zahl=0;
			//Keine Fuktion
			@Override
			public void keyTyped(KeyEvent e) {}
			
			//Abfragen des Textfeldinhalts und reagieren auf diesen.
			@Override
			public void keyReleased(KeyEvent e) {
				zahl=0;
				//Liest nur Wert aus, wenn auch etwas drin steht.
				if(T_Woche.getText().isEmpty()==false){
					try{
						zahl = Integer.parseInt( T_Woche.getText() );
					}catch(NumberFormatException exp){
						T_Woche.setText("");
					}
				}
				//Ausgelesene Zahl kleiner gleich 0 und Feld nicht leer.
				if(zahl<=0 && T_Woche.getText().isEmpty()==false){
					T_Woche.setText("");
					HauptController.hauptfenster.setInfoBox("Bitte eine Kalenderwoche eingeben!");
				}
				//Feld leer bzw. es steht nichts drin.
				if(T_Woche.getText().isEmpty() || T_Woche.equals("")){
					HauptController.hauptfenster.setInfoBox("Bitte eine Kalenderwoche eingeben!");
				}
				//Zahl größer als 1000
				if(zahl>54){
					T_Woche.setText("");
					HauptController.hauptfenster.setInfoBox("Die Kalenderwoche darf nicht größer als 54 sein!");
				}
				//Eingabe passt, Zahl zwischen 1 und 1000.
				if(zahl>=1 && zahl<=54){
					HauptController.hauptfenster.setInfoBox("");
				}
				revalidate();
			}
			
			//keine Funktion
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		L_Hinweis = new JTextArea();
		L_Hinweis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Hinweis.setText("Wenn Sie die Daten eines ganzen Kalenderjahres sehen wollen, lassen Sie das Feld\r\n\"Kalenderwoche\" leer.");
		L_Hinweis.setBounds(30, 130, 560, 60);
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
		this.T_Jahr.addKeyListener(c);
		this.T_Woche.addKeyListener(c);
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