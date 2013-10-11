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
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.JComboBox;

public class AG_anlegen {

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
	AG_anlegen window = new AG_anlegen();
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
public AG_anlegen() {
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

JLabel lblEineberschrift = new JLabel("Arbeitsgruppe anlegen");
lblEineberschrift.setBounds(10, 11, 380, 14);
arbeitsbereich.add(lblEineberschrift);
JLabel lblNewLabel = new JLabel("Kurzbezeichnung:");
lblNewLabel.setBounds(10, 119, 120, 14);
arbeitsbereich.add(lblNewLabel);
JLabel lblBezeichnung = new JLabel("Bezeichnung:");
lblBezeichnung.setBounds(10, 155, 120, 14);
arbeitsbereich.add(lblBezeichnung);
JLabel lblGruppenleiter = new JLabel("Gruppenleiter:");
lblGruppenleiter.setBounds(10, 190, 120, 14);
arbeitsbereich.add(lblGruppenleiter);
JLabel lblBereich = new JLabel("Bereich:");
lblBereich.setBounds(10, 228, 120, 14);
arbeitsbereich.add(lblBereich);
textField = new JTextField();
textField.setBounds(180, 116, 140, 20);
arbeitsbereich.add(textField);
textField.setColumns(10);
textField_1 = new JTextField();
textField_1.setBounds(180, 152, 140, 20);
arbeitsbereich.add(textField_1);
textField_1.setColumns(10);
textField_2 = new JTextField();
textField_2.setBounds(180, 187, 140, 20);
arbeitsbereich.add(textField_2);
textField_2.setColumns(10);
JComboBox comboBox = new JComboBox();
comboBox.setBounds(180, 227, 140, 17);
arbeitsbereich.add(comboBox);
JButton btnAnlegen = new JButton("Speichern");
btnAnlegen.setBounds(220, 395, 150, 23);
arbeitsbereich.add(btnAnlegen);
JButton btnZurcksetzen = new JButton("Zur\u00FCcksetzen");
btnZurcksetzen.setBounds(29, 395, 150, 23);
arbeitsbereich.add(btnZurcksetzen);
JButton btnNewButton = new JButton("");
btnNewButton.setBackground(SystemColor.window);
btnNewButton.setBounds(322, 183, 25, 26);
btnNewButton.setIcon(new ImageIcon("C:\\Users\\Adri\\workspace\\Fallstudie_Fenster\\src\\login\\lupe3.jpg"));
arbeitsbereich.add(btnNewButton);

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