package fallstudie.view.impl;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JRadioButton;

public class erfassen_gruppenleiter {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					erfassen_gruppenleiter window = new erfassen_gruppenleiter();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public erfassen_gruppenleiter() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(new Dimension(800, 600));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel navigationsbereich = new JPanel();
		navigationsbereich.setPreferredSize(new Dimension(190, 550));
		navigationsbereich.setMinimumSize(new Dimension(190, 550));
		navigationsbereich.setMaximumSize(new Dimension(190, 550));
		navigationsbereich.setSize(new Dimension(190, 550));
		navigationsbereich.setBackground(SystemColor.inactiveCaption);
		navigationsbereich.setBorder(new LineBorder(new Color(0, 0, 0)));
		navigationsbereich.setBounds(10, 11, 190, 550);
		frame.getContentPane().add(navigationsbereich);
		
		JTree navigationsbaum = new JTree();
		navigationsbaum.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Aktionen") {
				{
					add(new DefaultMutableTreeNode("Eintr\u00E4ge erfassen"));
					add(new DefaultMutableTreeNode("Gruppendaten einsehen"));
					add(new DefaultMutableTreeNode("Passwort \u00E4ndern"));
				}
			}
		));
		navigationsbereich.add(navigationsbaum);
		
		JPanel arbeitsbereich = new JPanel();
		arbeitsbereich.setPreferredSize(new Dimension(400, 550));
		arbeitsbereich.setMinimumSize(new Dimension(400, 550));
		arbeitsbereich.setMaximumSize(new Dimension(400, 550));
		arbeitsbereich.setBackground(SystemColor.window);
		arbeitsbereich.setBorder(new LineBorder(new Color(0, 0, 0)));
		arbeitsbereich.setBounds(210, 11, 400, 550);
		frame.getContentPane().add(arbeitsbereich);
		arbeitsbereich.setLayout(null);
		
		JLabel lblEineberschrift = new JLabel("Eintrag erfassen");
		lblEineberschrift.setBounds(10, 11, 380, 14);
		arbeitsbereich.add(lblEineberschrift);
		
		JLabel lblBitteGebenSie = new JLabel("Bitte geben Sie Ihre Daten ein.");
		lblBitteGebenSie.setBounds(20, 36, 370, 14);
		arbeitsbereich.add(lblBitteGebenSie);
		
		JLabel lblKalenderjahr = new JLabel("Kalenderjahr:");
		lblKalenderjahr.setBounds(10, 170, 100, 14);
		arbeitsbereich.add(lblKalenderjahr);
		
		JLabel lblKalenderwoche = new JLabel("Kalenderwoche:");
		lblKalenderwoche.setBounds(10, 205, 100, 14);
		arbeitsbereich.add(lblKalenderwoche);
		
		JLabel lblBearbeiteteErstattungen = new JLabel("Bearbeitete Erstattungen:");
		lblBearbeiteteErstattungen.setBounds(10, 241, 181, 14);
		arbeitsbereich.add(lblBearbeiteteErstattungen);
		
		JLabel lblBearbeiteteSchriftwechsel = new JLabel("Bearbeitete Schriftwechsel:");
		lblBearbeiteteSchriftwechsel.setBounds(10, 278, 181, 14);
		arbeitsbereich.add(lblBearbeiteteSchriftwechsel);
		
		JButton btnZurcksetzen = new JButton("Zur\u00FCcksetzen");
		btnZurcksetzen.setBounds(10, 400, 150, 23);
		arbeitsbereich.add(btnZurcksetzen);
		
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBounds(220, 400, 150, 23);
		arbeitsbereich.add(btnSpeichern);
		
		textField = new JTextField();
		textField.setText("2013");
		textField.setBounds(200, 167, 86, 20);
		arbeitsbereich.add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnWoche = new JRadioButton("Woche1");
		rdbtnWoche.setBackground(SystemColor.window);
		rdbtnWoche.setBounds(177, 201, 109, 23);
		arbeitsbereich.add(rdbtnWoche);
		
		JRadioButton rdbtnWocheJetzt = new JRadioButton("Woche jetzt");
		rdbtnWocheJetzt.setBackground(SystemColor.window);
		rdbtnWocheJetzt.setBounds(285, 201, 109, 23);
		arbeitsbereich.add(rdbtnWocheJetzt);
		
		textField_1 = new JTextField();
		textField_1.setBounds(200, 238, 86, 20);
		arbeitsbereich.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(200, 275, 86, 20);
		arbeitsbereich.add(textField_2);
		textField_2.setColumns(10);
		
		JButton button = new JButton("+");
		button.setBounds(301, 237, 89, 23);
		arbeitsbereich.add(button);
		
		JButton button_1 = new JButton("+");
		button_1.setBounds(301, 274, 89, 23);
		arbeitsbereich.add(button_1);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setPreferredSize(new Dimension(150, 20));
		btnLogout.setBounds(620, 11, 150, 20);
		frame.getContentPane().add(btnLogout);
		
		JButton btnHilfe = new JButton("Hilfe?");
		btnHilfe.setBounds(620, 42, 150, 20);
		frame.getContentPane().add(btnHilfe);
		
		JTextArea txtNachrichtenfeld = new JTextArea();
		txtNachrichtenfeld.setEditable(false);
		txtNachrichtenfeld.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNachrichtenfeld.setSize(new Dimension(150, 488));
		txtNachrichtenfeld.setMinimumSize(new Dimension(150, 488));
		txtNachrichtenfeld.setMaximumSize(new Dimension(150, 488));
		txtNachrichtenfeld.setBackground(SystemColor.control);
		txtNachrichtenfeld.setBounds(620, 73, 150, 488);
		frame.getContentPane().add(txtNachrichtenfeld);
		txtNachrichtenfeld.setText("Nachrichtenfeld\r\n");
	}
}
