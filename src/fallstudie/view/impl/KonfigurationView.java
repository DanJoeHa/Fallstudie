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
import javax.swing.JTextField;
import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;
/**
 * View zum Konfigurieren der Anzahl der Monate bis zum Löschen der Einträge in der Datenbank
 * 
 * @author Marc
 *
 */
public class KonfigurationView extends JPanel implements View {
	/**
	 * Serial
	 */
	private static final long serialVersionUID = -8966052424552014399L;
	/**
	 * Anzahl Monate bis zum Löschen Textfeld
	 */
	private JTextField T_AnzahlMonate;
	/**
	 * Speichern Button
	 */
	private JButton B_Speichern;
	/**
	 * Create the panel.
	 */
	public KonfigurationView() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 655));
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
		L_AnzahlMonate.setBounds(30, 30, 390, 30);
		add(L_AnzahlMonate);
		
		//T_AnzahlMonate
		T_AnzahlMonate = new JTextField();
		T_AnzahlMonate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_AnzahlMonate.setBounds(440, 30, 70, 30);
		add(T_AnzahlMonate);
		T_AnzahlMonate.setColumns(10);

	}
	/**
	 * Anzahl Monate setzen
	 * @param monate
	 */
	public void setAnzahlMonate(String monate){
		this.T_AnzahlMonate.setText(monate);
	}
	
	/**
	 * Anzahl Monate auslesen, gibt 0 zurück, wenn Text eingegeben wurde
	 * @return Anzahl Monate 
	 */
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
	/**
	 * Setzt Controller
	 */
	@Override
	public void setController(Controller c) {
		this.B_Speichern.addActionListener(c);		
		this.T_AnzahlMonate.addKeyListener(c);
	}
	/**
	 * Setzt Textfeld zurück
	 */
	@Override
	public void reset() {
		this.T_AnzahlMonate.setText("");
	}
	
	
}
