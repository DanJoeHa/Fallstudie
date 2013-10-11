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

public class layout2_panel extends JPanel {

	/**
	 * Create the panel.
	 */
	public layout2_panel() {
		setPreferredSize(new Dimension(600, 700));
		setMinimumSize(new Dimension(600, 700));
		setMaximumSize(new Dimension(600, 700));
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(new Rectangle(0, 0, 600, 700));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.window);
		setLayout(null);
		
		JTextPane txtpnHeyLeuteVergesst = new JTextPane();
		txtpnHeyLeuteVergesst.setText("Hey Leute vergesst nicht: TAHOMA 14. Die Namen aus \"Visio-GUI_Schicht_neu.pdf\" (> Entwurfsphase > Visio-Gui_Schicht_neu.pdf) nehmen. Das Layout ist schon eingestellt (Absolut Layout). Versucht es einfach nach den Paint-Bildern nach anzuordnen (Also die Reihenfolge von oben nach unten) Und bitte vergesst nicht auch die Buttons, Labels usw. dementsprechend umzubenennen sonst sitze ich da doppelt und dreifach dran.");
		txtpnHeyLeuteVergesst.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnHeyLeuteVergesst.setBounds(105, 70, 391, 288);
		add(txtpnHeyLeuteVergesst);

	}
}
