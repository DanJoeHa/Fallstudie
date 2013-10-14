package fallstudie.view.impl;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;

public class SuchenView extends JPanel implements View {

	private static final long serialVersionUID = -931416725537402397L;
	private JTextField T_Suchfeld;
	private JButton B_Suchen;

	/**
	 * Create the panel.
	 */
	public SuchenView() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 660));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//T_Suchfeld
		T_Suchfeld = new JTextField("");
		T_Suchfeld.setBounds(29, 13, 300, 30);
		add(T_Suchfeld);
		
		//B_Suchen
		B_Suchen = new JButton("Suchen");
		B_Suchen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_Suchen.setBounds(341, 12, 150, 30);
		add(B_Suchen);
		

		
	}
	
	/**
	 * Liefert den Suchbegriff aus dem Suchfeld zurück.
	 * 
	 * @author Johannes
	 * @version 1.0
	 * @return (String) Suchbegriff
	 */
	public String getSuchbegriff(){
		return this.T_Suchfeld.getText();
	}
	
	/**
	 * Setzt den ActionListener auf den Button "Suchen"
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	@Override
	public void setController(Controller c) {
		this.B_Suchen.addActionListener(c);
	}
	
	/**
	 * Setzt das Textfeld mit dem Suchbegriff zurück.
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	@Override
	public void reset() {
		this.T_Suchfeld.setText("");
	}
}
