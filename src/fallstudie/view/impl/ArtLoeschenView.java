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

public class ArtLoeschenView extends JPanel implements View{

	private static final long serialVersionUID = -4927164539248244321L;
	private JButton B_Loeschen;
	private JComboBox<String> C_Art;
	/**
	 * Create the panel.
	 */
	public ArtLoeschenView() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 655));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//B_L�schen
		B_Loeschen = new JButton("Löschen");
		B_Loeschen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Loeschen.setBounds(440, 600, 150, 30);
		add(B_Loeschen);
		
		//L_Bezeichnung
		JLabel L_Bezeichnung = new JLabel("Bitte die zu löschende Art auswählen");
		L_Bezeichnung.setFont(new Font("Tahoma", Font.PLAIN, 14));
		L_Bezeichnung.setBounds(30, 30, 230, 30);
		add(L_Bezeichnung);
		
		//J_Combobox -> Zur Auswahl der Art, die man l�schen m�chte
		C_Art = new JComboBox<String>();
		C_Art.setFont(new Font("Tahoma", Font.PLAIN, 14));
		C_Art.setBounds(270, 30, 320, 30);
		add(C_Art);

	}

	/**
	 * Art löschen
	 * @return
	 */
	public String getArt(){
		
		
		return this.C_Art.getSelectedItem().toString();
	}
	
	/**
	 * Combobox befüllen
	 * @param Arten
	 */
	public void setArt(String[] Arten){
		for( int i = 0; i < Arten.length; i++){
			this.C_Art.addItem( Arten[i] );
		}
		
	}
	@Override
	public void setController(Controller c) {
		this.B_Loeschen.addActionListener(c);
		this.B_Loeschen.addKeyListener(c);
		this.C_Art.addKeyListener(c);
	}

	@Override
	public void reset() {
		
	}
}
