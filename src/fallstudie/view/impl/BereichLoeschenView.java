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
import javax.swing.JComboBox;

public class BereichLoeschenView extends JPanel {

	/**
	 * Create the panel.
	 */
	public BereichLoeschenView() {
		setPreferredSize(new Dimension(620, 660));
		setMinimumSize(new Dimension(620, 660));
		setMaximumSize(new Dimension(620, 660));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 620, 660));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		JButton B_BearbeitenLoeschen = new JButton("L\u00F6schen");
		B_BearbeitenLoeschen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		B_BearbeitenLoeschen.setBounds(440, 600, 150, 30);
		add(B_BearbeitenLoeschen);
		
		JComboBox C_Bereich = new JComboBox();
		C_Bereich.setBounds(30, 300, 500, 30);
		add(C_Bereich);

	}
}