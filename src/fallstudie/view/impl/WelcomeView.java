package fallstudie.view.impl;

import javax.swing.JPanel;
import javax.swing.JTextArea;
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
	private JTextArea Hinweis;

	/**
	 * Erstellt die WillkommensView, die nach dem Login des Users angezeigt wird.
	 * 
	 * @author Natalja
	 * @version 0.1
	 */
	public WelcomeView() {
		setPreferredSize(new Dimension(600, 700));
		setMinimumSize(new Dimension(600, 700));
		setMaximumSize(new Dimension(600, 700));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 600, 700));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		//L_Hinweis
		Hinweis = new JTextArea();
		Hinweis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Hinweis.setBounds(10, 39, 580, 17);
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
