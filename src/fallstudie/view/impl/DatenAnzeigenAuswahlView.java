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
import javax.swing.JTextArea;

public class DatenAnzeigenAuswahlView extends JPanel {
	private JTextField T_Jahr;
	private JTextField T_Woche;

	/**
	 * Create the panel.
	 */
	public DatenAnzeigenAuswahlView() {
		setPreferredSize(new Dimension(620, 660));
		setMinimumSize(new Dimension(620, 660));
		setMaximumSize(new Dimension(620, 660));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 660));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		JButton B_Weiter = new JButton("Weiter");
		B_Weiter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Weiter.setBounds(440, 600, 150, 30);
		add(B_Weiter);
		
		JLabel L_Jahr = new JLabel("Kalenderjahr:");
		L_Jahr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Jahr.setBounds(30, 100, 150, 30);
		add(L_Jahr);
		
		JLabel L_Woche = new JLabel("Kalenderwoche:");
		L_Woche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Woche.setBounds(30, 150, 150, 30);
		add(L_Woche);
		
		T_Jahr = new JTextField();
		T_Jahr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Jahr.setBounds(200, 100, 70, 30);
		add(T_Jahr);
		T_Jahr.setColumns(10);
		
		T_Woche = new JTextField();
		T_Woche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		T_Woche.setBounds(200, 150, 70, 30);
		add(T_Woche);
		T_Woche.setColumns(10);
		
		JTextArea txtrWennSieDie = new JTextArea();
		txtrWennSieDie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrWennSieDie.setText("Wenn Sie die Daten eines ganzen Kalenderjahres sehen wollen, lassen Sie das Feld\r\n\"Kalenderwoche\" leer.");
		txtrWennSieDie.setBounds(30, 200, 520, 60);
		add(txtrWennSieDie);

	}
}