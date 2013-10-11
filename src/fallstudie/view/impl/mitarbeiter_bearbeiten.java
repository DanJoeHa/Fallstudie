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
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class mitarbeiter_bearbeiten {

private JFrame frame;
private JTextField textField;
private JTextField textField_1;
private JTextField textField_2;
private JPasswordField passwordField_1;

/**
* Launch the application.
*/
public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
public void run() {
try {
	mitarbeiter_bearbeiten window = new mitarbeiter_bearbeiten();
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
public mitarbeiter_bearbeiten() {
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
			DefaultMutableTreeNode node_1;
			node_1 = new DefaultMutableTreeNode("Art");
				node_1.add(new DefaultMutableTreeNode("Anlegen"));
				node_1.add(new DefaultMutableTreeNode("L\u00F6schen"));
			add(node_1);
			node_1 = new DefaultMutableTreeNode("Mitarbeiter");
				node_1.add(new DefaultMutableTreeNode("Anlegen"));
				node_1.add(new DefaultMutableTreeNode("Bearbeiten"));
				node_1.add(new DefaultMutableTreeNode("L\u00F6schen"));
			add(node_1);
			node_1 = new DefaultMutableTreeNode("Gruppe");
				node_1.add(new DefaultMutableTreeNode("Anlegen"));
				node_1.add(new DefaultMutableTreeNode("Bearbeiten"));
				node_1.add(new DefaultMutableTreeNode("L\u00F6schen"));
			add(node_1);
			node_1 = new DefaultMutableTreeNode("Bereich");
				node_1.add(new DefaultMutableTreeNode("Anlegen"));
				node_1.add(new DefaultMutableTreeNode("Bearbeiten"));
				node_1.add(new DefaultMutableTreeNode("L\u00F6schen"));
			add(node_1);
			add(new DefaultMutableTreeNode("Daten anzeigen"));
			add(new DefaultMutableTreeNode("Konfiguration"));
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

JLabel lblEineberschrift = new JLabel("Mitarbeiter bearbeiten");
lblEineberschrift.setBounds(10, 11, 185, 14);
arbeitsbereich.add(lblEineberschrift);
JLabel lblNewLabel = new JLabel("Vorname");
lblNewLabel.setBounds(10, 82, 90, 14);
arbeitsbereich.add(lblNewLabel);
JLabel lblNachname = new JLabel("Nachname");
lblNachname.setBounds(10, 107, 90, 14);
arbeitsbereich.add(lblNachname);
JLabel lblBenutzername = new JLabel("Benutzername");
lblBenutzername.setBounds(10, 132, 90, 14);
arbeitsbereich.add(lblBenutzername);
JLabel lblBereich = new JLabel("Rolle");
lblBereich.setBounds(10, 182, 90, 14);
arbeitsbereich.add(lblBereich);
JLabel lblArbeitsgruppe = new JLabel("Bereich");
lblArbeitsgruppe.setBounds(10, 207, 90, 14);
arbeitsbereich.add(lblArbeitsgruppe);
JLabel lblArbeitsgruppe_1 = new JLabel("Arbeitsgruppe");
lblArbeitsgruppe_1.setBounds(10, 232, 90, 14);
arbeitsbereich.add(lblArbeitsgruppe_1);
textField = new JTextField();
textField.setBounds(110, 79, 130, 20);
arbeitsbereich.add(textField);
textField.setColumns(10);
textField_1 = new JTextField();
textField_1.setColumns(10);
textField_1.setBounds(110, 104, 130, 20);
arbeitsbereich.add(textField_1);
textField_2 = new JTextField();
textField_2.setColumns(10);
textField_2.setBounds(110, 129, 130, 20);
arbeitsbereich.add(textField_2);
JComboBox comboBox = new JComboBox();
comboBox.setBounds(110, 204, 130, 20);
arbeitsbereich.add(comboBox);
JComboBox comboBox_1 = new JComboBox();
comboBox_1.setBounds(110, 179, 130, 20);
arbeitsbereich.add(comboBox_1);
passwordField_1 = new JPasswordField();
passwordField_1.setBounds(110, 229, 130, 20);
arbeitsbereich.add(passwordField_1);
JButton btnLupe = new JButton("");
btnLupe.setIcon(new ImageIcon("../Fallstudie/src/fallstudie/view/images/lupe3.jpg"));
btnLupe.setBackground(Color.WHITE);
btnLupe.setBounds(250, 228, 25, 26);
arbeitsbereich.add(btnLupe);
JButton btnSpeichern = new JButton("Speichern");
btnSpeichern.setBounds(220, 414, 132, 23);
arbeitsbereich.add(btnSpeichern);
JButton btnAbbrechen = new JButton("Abbrechen");
btnAbbrechen.setBounds(63, 414, 132, 23);
arbeitsbereich.add(btnAbbrechen);

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