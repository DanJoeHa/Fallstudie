package fallstudie.view.impl;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;
import java.awt.Dimension;

public class WelcomeView extends JPanel implements View{

	private static final long serialVersionUID = 6282583046506172098L;
	private JTextPane Hinweis;

	/**
	 * Erstellt die WillkommensView, die nach dem Login des Users angezeigt wird.
	 * 
	 * @author Natalja
	 * @version 0.1
	 */
	public WelcomeView() {
		setPreferredSize(new Dimension(620, 655));
		setMinimumSize(new Dimension(620, 655));
		setMaximumSize(new Dimension(620, 655));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 660));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//L_Hinweis
		Hinweis = new JTextPane();
		Hinweis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Hinweis.setBounds(30, 11, 560, 100);
		Hinweis.setEditable(false);
		this.add(Hinweis);
		
	}
	
	/**
	 * Setzt Willkommensnachricht auf dem ersten Screen nach dem Login.
	 * 
	 * @author Johannes
	 * @version 1.0
	 * @param (String) Willkommensnachricht
	 */
	public void setHinweis(String Willkommensnachricht){
		this.Hinweis.setText(Willkommensnachricht);
	}
	
	/**
	 * Methode ist in dieser View nicht relevant, da keine Aktionen ausgeführt werden können.
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	@Override
	public void setController(Controller c) {}
	
	/**
	 * Methode ist in dieser View nicht relevant, da keine User-Eingaben erfolgen.
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	@Override
	public void reset() {}
}
